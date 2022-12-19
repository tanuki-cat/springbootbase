package com.lichi.springbootbase.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 缓存配置
 * @author: lichi
 * @date: 2022/12/17 21:07
 * @version: 1.0
 * @since: 2022/12/17
 */
@EnableCaching
@Configuration
public class CacheConfig {
    @Value("${cache.default-expire}")
    private long extension;

    @Value("${spring.redis.host}")
    private String host;
}
