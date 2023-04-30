package com.lichi.springbootbase.auth.components;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.lichi.springbootbase.auth.entity.JwtProperties;
import com.lichi.springbootbase.auth.entity.UserDetail;
import com.lichi.springbootbase.auth.enums.CacheNameEnum;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

/**
 * @description: jwt过滤器组件
 * @author: lychee
 * @date: 2022/12/19 15:19
 * @version: 1.0
 * @since: 2022/12/19
 */
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {


    private static final String LOGIN2_URL = "/api/auth/login2";
    private static final String LOGIN_URL = "/api/auth/login";



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException, ServletException, ServletException {
        log.info("JWT过滤器通过校验请求头token进行自动登录...");
        if (LOGIN2_URL.equals(request.getRequestURI()) || LOGIN_URL.equals(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }
        JwtProperties jwtProperties = new JwtProperties();
        //获取请求头token
        String atToken = JwtComponent.getToken(request);
        if (ObjectUtils.isNotEmpty(atToken) && atToken.startsWith(jwtProperties.getPrefix())) {
            //获取token中的用户名
            String username = JwtComponent.getUsernameFromToken(atToken);
            if (ObjectUtils.isNotEmpty(username) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
                //从redis中获取用户信息
                UserDetail userDetail = JSON.to(UserDetail.class,CacheComponent.get(CacheNameEnum.USER, username, Object.class));
                if (ObjectUtils.isEmpty(userDetail) || !atToken.equals(userDetail.getToken())) {
                    filterChain.doFilter(request, response);
                }
                if (Boolean.TRUE.equals(JwtComponent.validationToken(atToken, userDetail))) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetail, userDetail, userDetail.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    log.info("JWT过滤器通过校验请求头token自动登录成功, user : {}", userDetail.getUsername());
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
