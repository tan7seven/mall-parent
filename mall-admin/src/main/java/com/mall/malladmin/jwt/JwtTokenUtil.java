package com.mall.malladmin.jwt;

import com.mall.malladmin.security.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: jwt生成token
 */
@Slf4j
public class JwtTokenUtil {

    // 寻找证书文件
    private static InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("jwt.jks"); // 寻找证书文件
    private static PrivateKey privateKey = null;
    private static PublicKey publicKey = null;

    private static final String CLAIM_KEY_USERNAME = "sub";

    private static final String CLAIM_KEY_CREATED = "created";

    static { // 将证书文件里边的私钥公钥拿出来
        try {
            KeyStore keyStore = KeyStore.getInstance("JKS"); // java key store 固定常量
            keyStore.load(inputStream, "123456".toCharArray());
            privateKey = (PrivateKey) keyStore.getKey("jwt", "123456".toCharArray()); // jwt 为 命令生成整数文件时的别名
            publicKey = keyStore.getCertificate("jwt").getPublicKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据用户信息生成token
     */
    public static String generateToken(UserDetailsImpl userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }
    /**
     * 生成token
     * @param claims 自定义身份信息
     * @return
     */
    public static String generateToken( Map<String,Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + 604800*1000))//过期时间（秒）
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
    }

    /**
     * @deprecation: 解析token,获得subject中的信息
     */
    public static String parseToken(String token) {
        return getTokenBody(token).getSubject();
    }

    /**
     * 获取token自定义属性
     */
    public static Map<String,Object> getClaims(String token){
        return getTokenBody(token);
    }

    /**
     *  是否已过期
      */
    public static boolean isExpiration(String token){
        return getTokenBody(token).getExpiration().before(new Date());
    }

    private static Claims getTokenBody(String token){
        return Jwts.parser()
                .setSigningKey(publicKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public static void main(String[] args) {
        UserDetailsImpl user = new UserDetailsImpl();
        user.setUsername("123456");
        String jwtToken = JwtTokenUtil.generateToken(user);
        String result = parseToken(jwtToken);
        Claims result1 = getTokenBody(jwtToken);
        System.out.println(jwtToken);
        System.out.println(result);

    }
}