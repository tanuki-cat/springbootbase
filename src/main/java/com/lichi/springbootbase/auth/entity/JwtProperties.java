package com.lichi.springbootbase.auth.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @description: jwt对象
 * @author: lychee
 * @date: 2022/12/19 12:29
 * @version: 1.0
 * @since: 2022/12/19
 */
@Data
@Component
public class JwtProperties {
/**
     * jwt签名
     */
    private String secret = "JWT_SECRET_KEY";
    /**
     * jwt过期时间
     */
    private Long expiration = 1800L;
    /**
     * jwt请求头
     */
    private String header = "Authorization";
    /**
     * jwt前缀
     */
    private String prefix = "Bearer ";
}
