package com.lichi.springbootbase.auth.service.impl;

import com.lichi.springbootbase.auth.service.AuthService;
import com.lichi.springbootbase.response.ApiResponse;
import org.springframework.stereotype.Service;

/**
 * @Description: 鉴权service实现
 * @author: lychee
 * @Date: 2022/12/19 17:19
 * @Version: 1.0
 * @Since: 2022/12/19
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public ApiResponse<?> login(String account, String password) {
        return null;
    }

    @Override
    public ApiResponse<?> logout() {
        return null;
    }

    @Override
    public ApiResponse<?> refreshToken(String token) {
        return null;
    }
}
