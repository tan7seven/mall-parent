package com.mall.app.jwt;

import com.mall.dao.entity.user.UserEntity;
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

    public static final String CLAIM_KEY_USER = "userId";

    private static final String CLAIM_KEY_CREATED = "created";

    private static final Long EXPIRATION_TIME = 1 * 60 * 1000L;


    /**
     * 根据用户信息生成token
     */
    public static String generateToken(UserEntity userEntity) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USER, userEntity.getId());
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
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))//过期时间（秒）
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * @deprecation: 解析token,获得subject中的信息
     */
    public static String parseToken(String token) {
        return (String) getTokenBody(token).get(CLAIM_KEY_USER);
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
        UserEntity user = new UserEntity();
        user.setId(123L);
        String jwtToken = JwtTokenUtil.generateToken(user);
        System.out.println(jwtToken);
        Map<String,Object> userMsg = getClaims(jwtToken);
        System.out.println(userMsg);
    }
}