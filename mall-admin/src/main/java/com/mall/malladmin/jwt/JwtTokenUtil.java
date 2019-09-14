package com.mall.malladmin.jwt;

import com.mall.malladmin.security.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: jwt生成token
 */
@Slf4j
public class JwtTokenUtil {
    private static final String secret = "wjz-mall";

    private static final String CLAIM_KEY_USERNAME = "sub";

    private static final String CLAIM_KEY_CREATED = "created";


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
                .signWith(SignatureAlgorithm.HS512, secret)
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
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public static void main(String[] args) {
        UserDetailsImpl user = new UserDetailsImpl();
        user.setUsername("123456");
        String jwtToken = JwtTokenUtil.generateToken(user);
        System.out.println(jwtToken);

    }
}