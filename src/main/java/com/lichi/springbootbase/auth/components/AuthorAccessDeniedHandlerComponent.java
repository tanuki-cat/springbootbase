package com.lichi.springbootbase.auth.components;

import com.lichi.springbootbase.response.ApiResponse;
import com.lichi.springbootbase.response.enums.ApiResponseStatusEnum;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 权限不足组件
 * @author: lychee
 * @date: 2022/12/19 16:16
 * @version: 1.0
 * @since: 2022/12/19
 */

@Component
public class AuthorAccessDeniedHandlerComponent implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().println(ApiResponse.fail(ApiResponseStatusEnum.FORBIDDEN, ApiResponseStatusEnum.FORBIDDEN.getMessage(), null));
        response.getWriter().flush();
    }
}
