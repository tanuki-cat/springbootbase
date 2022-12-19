package com.lichi.springbootbase.auth.service;

import com.lichi.springbootbase.response.ApiResponse;

/**
 * @description: 权限校验相关service
 * @author: lichi
 * @date: 2022/12/17 20:15
 * @version: 1.0
 * @since: 2022/12/17
 */
public interface AuthService {
    /**
     * 登录校验
     * @param account 账号
     * @param password 密码
     * @return ApiResponse
     */
    ApiResponse<?> login(String account, String password);

    /**
     * 登出校验
     * @return ApiResponse
     */
    ApiResponse<?> logout();

    /**
     * 刷新token
     * @param token 当前的token
     * @return
     */
    ApiResponse<?> refreshToken(String token);
}
