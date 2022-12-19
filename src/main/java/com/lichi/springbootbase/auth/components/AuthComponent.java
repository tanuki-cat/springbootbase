package com.lichi.springbootbase.auth.components;

import com.lichi.springbootbase.auth.entity.UserDetail;
import com.lichi.springbootbase.auth.entity.UserInfo;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

/**
 * @description: 获取鉴权信息组件
 * @author: lychee
 * @date: 2022/12/19 15:11
 * @version: 1.0
 * @since: 2022/12/19
 */

public class AuthComponent {

    /**
     * 从SecurityContextHolder获取UserDetail
     * @return UserDetail
     */
    public static UserDetail getUserDetail() {
        return (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /**
     * 获取用户信息
     * @return UserInfo
     */
    public static UserInfo getUserInfo() {
        return getUserDetail().getUserInfo();
    }

    /**
     * 获取用户id
     * @return 用户id
     */
    public static Long getUserId() {
        return getUserInfo().getId();
    }

    /**
     * 获取用户角色
     * @return 角色列表
     */
    public static List<String> getAuthorities() {
        return getUserDetail().getRoles();
    }
}
