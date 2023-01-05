package com.lichi.springbootbase.controller.auth;

import com.lichi.springbootbase.annotations.WebLog;
import com.lichi.springbootbase.auth.entity.JwtProperties;
import com.lichi.springbootbase.auth.service.AuthService;
import com.lichi.springbootbase.response.ApiResponse;
import com.lichi.springbootbase.response.enums.ApiResponseStatusEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * @Description: 认证接口
 * @author: lychee
 * @Date: 2022/12/20 10:21
 * @Version: 1.0
 * @Since: 2022/12/20
 */
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@Slf4j
public class AuthController {
    private final JwtProperties jwtProperties;
    private final AuthService authService;


    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return ApiResponse
     */
    @PostMapping("/login")
    @WebLog(description = "登录")
    public ApiResponse<?> login(String username, String password) {
        try {
            return authService.login(username, password);
        } catch (Exception e) {
            log.error("login error", e);
            return ApiResponse.error(ApiResponseStatusEnum.UNAUTHORIZED,"用户名或密码错误", null);
        }
    }

    /**
     * 登出
     * @return ApiResponse
     */
    @WebLog(description = "登出")
    @PostMapping("/logout")
    public ApiResponse<?> logout() {
        try {
            return authService.logout();
        } catch (Exception e) {
            log.error("logout error", e);
            return ApiResponse.error(e.getMessage());
        }
    }

    /**
     * 刷新token
     * @param token String
     * @return ApiResponse
     */
    @WebLog(description = "刷新token")
    @PostMapping("/refresh")
    public ApiResponse<?> refreshToken(@RequestHeader("Authorization") String token) {
        try {
            if (!token.startsWith(jwtProperties.getPrefix())) {
               return ApiResponse.error("刷新token失败");
            }
            return authService.refreshToken(token);
        } catch (Exception e) {
            log.error("refreshToken error", e);
            return ApiResponse.error("刷新token失败");
        }
    }

    /**
     * 用户注册
     * @param username 用户名
     * @param password 密码
     * @param roleValue 角色值
     * @return ApiResponse
     */
    @WebLog(description = "用户注册接口")
    @PostMapping("/register")
    public ApiResponse<?> register(String username, String password, String roleValue) {
        try {
            return authService.register(username, password, roleValue);
        } catch (Exception e) {
            log.error("register error", e);
            return ApiResponse.error("注册失败");
        }
    }
}
