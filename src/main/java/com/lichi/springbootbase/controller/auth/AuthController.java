package com.lichi.springbootbase.controller.auth;

import com.lichi.springbootbase.auth.components.JwtComponent;
import com.lichi.springbootbase.auth.service.AuthService;
import com.lichi.springbootbase.response.ApiResponse;
import com.lichi.springbootbase.response.enums.ApiResponseStatusEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
    private final AuthService authService;


    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return ApiResponse
     */
    @PostMapping("/login")
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
     * @param request HttpServletRequest
     * @return ApiResponse
     */
    @PostMapping("/refresh")
    public ApiResponse<?> refreshToken(HttpServletRequest request) {
        try {
            return authService.refreshToken(JwtComponent.getToken(request));
        } catch (Exception e) {
            log.error("refreshToken error", e);
            return ApiResponse.error(e.getMessage());
        }
    }
}
