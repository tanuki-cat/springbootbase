package com.lichi.springbootbase.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author lychee
 * @version 1.0
 * @description 登录信息dto
 * @date 2023/3/8 08:45
 * @since 2023/3/8
 */
@Data
@AllArgsConstructor
public class LoginDTO {
    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
    /**
     * 登录类型
     */
    private String type;
}
