package com.lichi.springbootbase.auth.service.impl;

import com.alibaba.fastjson2.JSON;
import com.lichi.springbootbase.auth.components.AuthComponent;
import com.lichi.springbootbase.auth.components.CacheComponent;
import com.lichi.springbootbase.auth.components.JwtComponent;
import com.lichi.springbootbase.auth.entity.AccessToken;
import com.lichi.springbootbase.auth.entity.UserDetail;
import com.lichi.springbootbase.auth.enums.CacheNameEnum;
import com.lichi.springbootbase.auth.service.AuthService;
import com.lichi.springbootbase.response.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @Description: 鉴权service实现
 * @author: lychee
 * @Date: 2022/12/19 17:19
 * @Version: 1.0
 * @Since: 2022/12/19
 */
@Service
@Slf4j
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {


    private final AuthenticationManager authenticationManager;


    @Override
    public ApiResponse<?> login(String account, String password) {
        log.debug("this is in login method");
        // 构建token
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(account, password);
        // 认证token
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        //存放token
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //获取UserDetail
        UserDetail userDetail = (UserDetail) authentication.getPrincipal();
        // 生成自定义jwt token
        AccessToken accessToken = JwtComponent.createAccessToken(userDetail);
        //将jwt token 放入 userDetail
        userDetail.setToken(accessToken.getToken());
        //放入缓存
        CacheComponent.put(CacheNameEnum.USER,userDetail.getUsername(),userDetail);

        return ApiResponse.success(accessToken);
    }

    @Override
    public ApiResponse<?> logout() {
        // 清除缓存
        CacheComponent.remove(CacheNameEnum.USER, AuthComponent.getUserDetail().getUsername());
        SecurityContextHolder.clearContext();
        return ApiResponse.success();
    }

    @Override
    public ApiResponse<?> refreshToken(String token) {
        AccessToken accessToken = JwtComponent.refreshToken(token);
        UserDetail userDetail = JSON.to(UserDetail.class,CacheComponent.get(CacheNameEnum.USER,
                accessToken.getLoginAccount(), Object.class));
        // 将新的token放入userDetail
        userDetail.setToken(accessToken.getToken());
        CacheComponent.put(CacheNameEnum.USER, accessToken.getLoginAccount(), userDetail);
        return ApiResponse.success(accessToken);
    }
}
