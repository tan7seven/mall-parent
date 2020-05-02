package com.mall.common.utils;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2019/12/26
 */
public class HttpUtil {
    public static final String SUN_X509 = "SunX509";
    public static final String JKS = "JKS";
    public static final String PKCS12 = "PKCS12";
    public static final String TLS = "TLS";

    public HttpUtil() {
    }

    public static HttpURLConnection getHttpURLConnection(String strUrl) throws IOException {
        URL url = new URL(strUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
        return httpURLConnection;
    }

    public static HttpsURLConnection getHttpsURLConnection(String strUrl) throws IOException {
        URL url = new URL(strUrl);
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection)url.openConnection();
        return httpsURLConnection;
    }

    public static String getURL(String strUrl) {
        if (null != strUrl) {
            int indexOf = strUrl.indexOf("?");
            return -1 != indexOf ? strUrl.substring(0, indexOf) : strUrl;
        } else {
            return strUrl;
        }
    }

    public static String getQueryString(String strUrl) {
        if (null != strUrl) {
            int indexOf = strUrl.indexOf("?");
            return -1 != indexOf ? strUrl.substring(indexOf + 1, strUrl.length()) : "";
        } else {
            return strUrl;
        }
    }

    public static Map queryString2Map(String queryString) {
        if (null != queryString && !"".equals(queryString)) {
            Map m = new HashMap();
            String[] strArray = queryString.split("&");

            for(int index = 0; index < strArray.length; ++index) {
                String pair = strArray[index];
                putMapByPair(pair, m);
            }

            return m;
        } else {
            return null;
        }
    }

    public static void putMapByPair(String pair, Map m) {
        if (null != pair && !"".equals(pair)) {
            int indexOf = pair.indexOf("=");
            if (-1 != indexOf) {
                String k = pair.substring(0, indexOf);
                String v = pair.substring(indexOf + 1, pair.length());
                if (null != k && !"".equals(k)) {
                    m.put(k, v);
                }
            } else {
                m.put(pair, "");
            }

        }
    }

    public static String bufferedReader2String(BufferedReader reader) throws IOException {
        StringBuffer buf = new StringBuffer();
        String line = null;

        while((line = reader.readLine()) != null) {
            buf.append(line);
            buf.append("\r\n");
        }

        return buf.toString();
    }

    public static void doOutput(OutputStream out, byte[] data, int len) throws IOException {
        int dataLen = data.length;

        for(int off = 0; off < data.length; out.flush()) {
            if (len >= dataLen) {
                out.write(data, off, dataLen);
                off += dataLen;
            } else {
                out.write(data, off, len);
                off += len;
                dataLen -= len;
            }
        }

    }

    public static SSLContext getSSLContext(FileInputStream trustFileInputStream, String trustPasswd, FileInputStream keyFileInputStream, String keyPasswd) throws NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException, UnrecoverableKeyException, KeyManagementException {
        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
        KeyStore trustKeyStore = KeyStore.getInstance("JKS");
        trustKeyStore.load(trustFileInputStream, str2CharArray(trustPasswd));
        tmf.init(trustKeyStore);
        char[] kp = str2CharArray(keyPasswd);
        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(keyFileInputStream, kp);
        kmf.init(ks, kp);
        SecureRandom rand = new SecureRandom();
        SSLContext ctx = SSLContext.getInstance("TLS");
        ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), rand);
        return ctx;
    }

    public static SSLContext getSSLContext(InputStream keyFileInputStream, String keyPasswd) throws NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException, UnrecoverableKeyException, KeyManagementException {
        char[] kp = str2CharArray(keyPasswd);
        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(keyFileInputStream, kp);
        kmf.init(ks, kp);
        SecureRandom rand = new SecureRandom();
        SSLContext ctx = SSLContext.getInstance("TLS");
        ctx.init(kmf.getKeyManagers(), (TrustManager[])null, rand);
        return ctx;
    }

    public static char[] str2CharArray(String str) {
        return null == str ? null : str.toCharArray();
    }

    public static InputStream string2InputStream(String str) {
        return new ByteArrayInputStream(str.getBytes());
    }

    public static byte[] inputStreamToByte(InputStream in) throws IOException {
        int bufferSize = 4096;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[bufferSize];
        boolean var4 = true;

        int count;
        while((count = in.read(data, 0, bufferSize)) != -1) {
            outStream.write(data, 0, count);
        }

        byte[] outByte = outStream.toByteArray();
        outStream.close();
        return outByte;
    }

    public static String inputStreamToString(InputStream in, String encoding) throws IOException {
        return new String(inputStreamToByte(in), encoding);
    }
}