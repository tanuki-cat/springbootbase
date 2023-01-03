package com.lichi.springbootbase.auth.enums;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 缓存名称
 * @author: lychee
 * @date: 2022/12/19 10:34
 * @version: 1.0
 * @since: 2022/12/19
 */
@AllArgsConstructor
public enum CacheNameEnum {
    /**
     * 用户信息缓存
     */
    USER("USER"),
    /**
     * 用户权限缓存
     */
    PERMISSION("PERMISSION");

    /**
     * 枚举值
     */
    private final String cacheName;

    /**
     * 获取所有枚举值
     * @return List<String>
     */
    public static List<String> getCacheNames() {
        List<String> cacheNameList = new ArrayList<>(CacheNameEnum.values().length);
        CacheNameEnum[] values = CacheNameEnum.values();
        for (int i = 0; i < CacheNameEnum.values().length; i++) {
            cacheNameList.add(values[i].cacheName);
        }
        return cacheNameList;
    }

    /**
     * 获取枚举值
     * @return
     */
    public String getCacheName() {
        return cacheName;
    }

}
