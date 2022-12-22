package com.lichi.springbootbase.auth.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lichi.springbootbase.auth.components.AuthComponent;
import com.lichi.springbootbase.auth.components.CacheComponent;
import com.lichi.springbootbase.auth.components.JwtComponent;
import com.lichi.springbootbase.auth.entity.*;
import com.lichi.springbootbase.auth.enums.CacheNameEnum;
import com.lichi.springbootbase.auth.enums.RoleEnum;
import com.lichi.springbootbase.auth.service.AuthService;
import com.lichi.springbootbase.auth.service.RoleInfoService;
import com.lichi.springbootbase.auth.service.UserInfoService;
import com.lichi.springbootbase.auth.service.UserRoleLinkService;
import com.lichi.springbootbase.response.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

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

    private final UserInfoService userInfoService;

    private final UserRoleLinkService userRoleLinkService;


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
        CacheComponent.put(CacheNameEnum.USER, userDetail.getUsername(), userDetail);

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
        UserDetail userDetail = JSON.to(UserDetail.class, CacheComponent.get(CacheNameEnum.USER,
                accessToken.getLoginAccount(), Object.class));
        // 将新的token放入userDetail
        userDetail.setToken(accessToken.getToken());
        CacheComponent.put(CacheNameEnum.USER, accessToken.getLoginAccount(), userDetail);
        return ApiResponse.success(accessToken);
    }

    /**
     * 用户注册
     *
     * @param username  用户名
     * @param password  密码
     * @param roleValue 角色值
     * @return ApiResponse<?>
     */
    @Override
    public ApiResponse<?> register(String username, String password, String roleValue) {
        if (isUserNameExist(username)) {
            return ApiResponse.fail("用户名已存在");
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPassword(new BCryptPasswordEncoder().encode(password));
        userInfoService.save(userInfo);
        UserRoleLink userRoleLink = new UserRoleLink();
        userRoleLink.setUserId(userInfo.getId());
        // 默认注册用户为普通用户
        userRoleLink.setRoleId(RoleEnum.ADMIN.roleCode(roleValue));
        userRoleLinkService.save(userRoleLink);
        return ApiResponse.success("注册成功");
    }

    /**
     * 校验用户名是否存在
     * @param username 用户名
     * @return Boolean
     */
    private Boolean isUserNameExist(String username) {
        UserInfo userInfo = userInfoService.getOne(Wrappers.<UserInfo>query()
                .lambda().eq(UserInfo::getUsername, username));
        return ObjectUtils.isNotNull(userInfo);
    }
}
