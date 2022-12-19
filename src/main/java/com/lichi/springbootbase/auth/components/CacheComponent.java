package com.lichi.springbootbase.auth.components;

import com.lichi.springbootbase.auth.enums.CacheNameEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @description: 缓存服务组件
 * @author: lychee
 * @date: 2022/12/19 15:55
 * @version: 1.0
 * @since: 2022/12/19
 */
@Component
@Slf4j
public class CacheComponent {
    @Autowired
    private CacheManager cacheManager;

    /**
     * 获取缓存
     * @param cacheName 缓存名称
     * @param key 缓存key
     * @param clazz 缓存对象类型
     * @return T
     * @param <T>
     */
    public <T> T get(CacheNameEnum cacheName, String key, Class<T> clazz) {
        log.debug("{} get -> cacheName [{}], key [{}], class type [{}]", this.getClass().getName(), cacheName, key, clazz.getName());
        return Objects.requireNonNull(cacheManager.getCache(cacheName.name())).get(key, clazz);
    }

    /**
     * 设置缓存
     * @param cacheName 缓存名称
     * @param key 缓存key
     * @param value 缓存对象
     */
    public void put(CacheNameEnum cacheName, String key, Object value) {
        log.debug("{} put -> cacheName [{}], key [{}], value [{}]", this.getClass().getName(), cacheName, key, value);
        Objects.requireNonNull(cacheManager.getCache(cacheName.name())).put(key, value);
    }

    /**
     * 删除缓存
     * @param cacheName 缓存名称
     * @param key  缓存key
     */
    public void remove(CacheNameEnum cacheName, String key) {
        log.debug("{} remove -> cacheName [{}], key [{}]", this.getClass().getName(), cacheName, key);
        Objects.requireNonNull(cacheManager.getCache(cacheName.name())).evict(key);
    }
}
