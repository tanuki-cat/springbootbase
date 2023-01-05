package com.lichi.springbootbase.auth.components;

import com.lichi.springbootbase.auth.enums.CacheNameEnum;
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
public class CacheComponent {

    private static CacheManager cacheManager;
    @Autowired
    private void setCacheManager(CacheManager cacheManager){
        CacheComponent.cacheManager = cacheManager;
    }

    /**
     * 获取缓存
     * @param cacheName 缓存名称
     * @param key 缓存key
     * @param clazz 缓存对象类型
     * @return T
     * @param <T>
     */
    public static  <T> T get(CacheNameEnum cacheName, String key, Class<T> clazz) {
        return Objects.requireNonNull(cacheManager.getCache(cacheName.name())).get(key, clazz);
    }

    /**
     * 设置缓存
     * @param cacheName 缓存名称
     * @param key 缓存key
     * @param value 缓存对象
     */
    public static void put(CacheNameEnum cacheName, String key, Object value) {
        Objects.requireNonNull(cacheManager.getCache(cacheName.name())).put(key, value);
    }

    /**
     * 删除缓存
     * @param cacheName 缓存名称
     * @param key  缓存key
     */
    public static void remove(CacheNameEnum cacheName, String key) {
        Objects.requireNonNull(cacheManager.getCache(cacheName.name())).evict(key);
    }
}
