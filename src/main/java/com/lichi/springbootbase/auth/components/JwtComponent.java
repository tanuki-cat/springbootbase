package com.lichi.springbootbase.auth.components;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.lichi.springbootbase.auth.entity.AccessToken;
import com.lichi.springbootbase.auth.entity.JwtProperties;
import com.lichi.springbootbase.auth.entity.UserDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @description: jwt工具组件
 * @author: lychee
 * @date: 2022/12/19 12:37
 * @version: 1.0
 * @since: 2022/12/19
 */
@Slf4j
@Component
public class JwtComponent {



    private static JwtProperties jwtProperties;
    @Autowired
    private void setJwtProperties(JwtProperties jwtProperties){
        JwtComponent.jwtProperties = jwtProperties;
    }

    /**
     * 获取jwt token
     * @param request 请求
     * @return String
     */
    public static String getToken(HttpServletRequest request) {
        return request.getHeader(jwtProperties.getHeader());

    }

    public static AccessToken createAccessToken(UserDetail userDetail) {
        return createToken(userDetail.getUsername());
    }

    /**
     * 创建jwt token
     * @param subject 主题
     * @return AccessToken
     */
    private static AccessToken createToken(String subject) {
        //token 创建时间
        final Date createdDate = new Date();
        //token 过期时间
        final Date expirationDate = new Date(createdDate.getTime() + jwtProperties.getExpiration() * 1000);
        String token = jwtProperties.getPrefix() + JWT.create()
                .withSubject(subject)
                .withIssuer(subject)
                .withIssuedAt(createdDate)
                .withExpiresAt(expirationDate)
                //自定义属性 可以用claim 放入
                //.withClaim("loginAccount", subject)
                .sign(Algorithm.HMAC512(jwtProperties.getSecret()));
        return  AccessToken.builder()
                .token(token)
                .expiration(expirationDate)
                .loginAccount(subject)
                .build();
    }

    /**
     * 验证token
     * @param token
     * @param userDetail
     * @return
     */
    public static Boolean validationToken(String token, UserDetail userDetail) {
        try {
            DecodedJWT decodedJwt = getDecodedJwt(token);
            //如果有自定义的字段，可以在这里获取
            //Map<String, Claim> claims = decodedJwt.getClaims();
            return decodedJwt.getSubject().equals(userDetail.getUsername())
                    && !isExpired(decodedJwt.getExpiresAt().getTime());
        } catch (Exception e) {
            log.error("token验证失败: {}", e.getMessage());
            return false;
        }
    }

    /**
     * 刷新token
     * @param token
     * @return
     */
    public static AccessToken refreshToken(String token) {
        DecodedJWT decodedJwt = getDecodedJwt(token);
        return createToken(decodedJwt.getSubject());
    }

    /**
     * 校验token是否过期
     * @param expiration 过期时间
     * @return boolean
     */
    private static Boolean isExpired(Long expiration) {
        return expiration < System.currentTimeMillis();
    }

    /**
     * 获取jwt签名信息
     * @param token token
     * @return DecodedJWT
     */
    private static DecodedJWT getDecodedJwt(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC512(jwtProperties.getSecret()))
                .build();
        return verifier.verify(token.substring(jwtProperties.getPrefix().length()));
    }

    /**
     * 获取token中的用户名
     * @param token token
     * @return String
     */
    public static String getUsernameFromToken(String token) {
        DecodedJWT decodedJwt = getDecodedJwt(token);
        return decodedJwt.getSubject();
    }
}
