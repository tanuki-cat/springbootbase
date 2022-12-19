package com.lichi.springbootbase.auth.enums;

import lombok.AllArgsConstructor;

/**
 * @description: 角色枚举
 * @author: lichi
 * @date: 2022/12/19 10:30
 * @version: 1.0
 * @since: 2022/12/19
 */
@AllArgsConstructor
public enum RoleEnum {
    /**
     * 用户
     */
    USER("USER"),
    /**
     * 管理员
     */
    ADMIN("ADMIN");

    /**
     * 枚举值
     */
    private final String value;

    public String getValue() {
        return value;
    }

}
