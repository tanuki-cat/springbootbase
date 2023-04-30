package com.lichi.springbootbase.auth.entity;

import com.google.j2objc.annotations.Property;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
/**
     * jwt签名
     */
    private  String secret;
    /**
     * jwt过期时间
     */
    private  Long expiration;
    /**
     * jwt请求头
     */
    private  String header;
    /**
     * jwt前缀
     */
    private  String prefix;
}
