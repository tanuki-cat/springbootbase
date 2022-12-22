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
    USER("USER",1),
    /**
     * 管理员
     */
    ADMIN("ADMIN",2);

    /**
     * 枚举值
     */
    private final String value;

    private final long code;

    public String getValue() {
        return value;
    }

    public long getCode() {
        return code;
    }

    public long roleCode(String value) {
        for (RoleEnum roleEnum : RoleEnum.values()) {
            if (roleEnum.getValue().equals(value)) {
                return roleEnum.getCode();
            }
        }
        // 默认返回用户
        return 1;
    }

}
