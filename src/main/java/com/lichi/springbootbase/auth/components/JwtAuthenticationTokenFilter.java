package com.lichi.springbootbase.auth.components;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.lichi.springbootbase.auth.entity.JwtProperties;
import com.lichi.springbootbase.auth.entity.UserDetail;
import com.lichi.springbootbase.auth.enums.CacheNameEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @description: jwt过滤器组件
 * @author: lychee
 * @date: 2022/12/19 15:19
 * @version: 1.0
 * @since: 2022/12/19
 */
@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtComponent jwtComponent;
    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private CacheComponent cacheComponent;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("JWT过滤器通过校验请求头token进行自动登录...");
        //获取请求头token
        String atToken = jwtComponent.getToken(request);
        if (ObjectUtils.isNotEmpty(atToken) && atToken.startsWith(jwtProperties.getPrefix())) {
            //获取token中的用户名
            String username = jwtComponent.getUsernameFromToken(atToken);
            if (ObjectUtils.isNotEmpty(username) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
                //从redis中获取用户信息
                UserDetail userDetail = cacheComponent.get(CacheNameEnum.USER, username, UserDetail.class);
                if (ObjectUtils.isNotEmpty(userDetail) && jwtComponent.validationToken(atToken, userDetail)) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetail, userDetail, userDetail.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    log.info("JWT过滤器通过校验请求头token自动登录成功, user : {}", userDetail.getUsername());
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
