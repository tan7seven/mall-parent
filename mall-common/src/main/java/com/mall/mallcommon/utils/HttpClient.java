package com.mall.mallcommon.utils;


import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2019/12/26
 */
public class HttpClient {

    public static final MediaType JSON = MediaType.parse("application/json;charset=utf-8");

    public static String httpGet(String url) throws IOException {
        OkHttpClient httpClient = new OkHttpClient();
        Request request = (new Request.Builder()).url(url).build();
        Response response = httpClient.newCall(request).execute();
        return response.body().string();
    }

    public static String httpPost(String url, String json) throws IOException {
        OkHttpClient httpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(JSON, json);
        Request request = (new Request.Builder()).url(url).post(requestBody).build();
        Response response = httpClient.newCall(request).execute();
        return response.body().string();
    }

    public static String httpPost(String url, HashMap<String, String> params) throws IOException {
        OkHttpClient httpClient = (new okhttp3.OkHttpClient.Builder()).build();
        okhttp3.FormBody.Builder builder = new okhttp3.FormBody.Builder();
        Iterator var4 = params.entrySet().iterator();

        while(var4.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry)var4.next();
            builder.add((String)entry.getKey(), (String)entry.getValue());
        }

        Request request = (new Request.Builder()).url(url).post(builder.build()).build();
        Response response = httpClient.newCall(request).execute();
        return response.body().string();
    }
}

