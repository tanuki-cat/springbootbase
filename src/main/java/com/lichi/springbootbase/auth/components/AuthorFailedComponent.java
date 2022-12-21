package com.lichi.springbootbase.auth.components;

import com.lichi.springbootbase.response.ApiResponse;
import com.lichi.springbootbase.response.enums.ApiResponseStatusEnum;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 鉴权失败401组件
 * @author: lychee
 * @date: 2022/12/19 16:11
 * @version: 1.0
 * @since: 2022/12/19
 */
public class AuthorFailedComponent implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().println(ApiResponse.fail(ApiResponseStatusEnum.UNAUTHORIZED, authException.getMessage(), null));
        response.getWriter().flush();
    }
}
