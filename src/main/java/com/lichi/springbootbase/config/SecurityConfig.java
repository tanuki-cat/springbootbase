package com.lichi.springbootbase.config;

import com.lichi.springbootbase.auth.components.AuthorAccessDeniedHandlerComponent;
import com.lichi.springbootbase.auth.components.AuthorFailedComponent;
import com.lichi.springbootbase.auth.components.JwtAuthenticationTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @description: SpringSecurityConfig 配置
 * @author: lichi
 * @date: 2022/12/19 16:25
 * @version: 1.0
 * @since: 2022/12/19
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig{

    /**
     * 配置 httpSecurity
     * @param httpSecurity httpSecurity
     * @return httpSecurity
     * @throws Exception exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/api/auth/login").permitAll()
                .antMatchers("/api/anon/**").permitAll()
                .anyRequest().authenticated()
                //自定义的处理器
                //.accessDecisionManager(accessDecisionManager())
                .and()
                //添加自定义异常处理
                .exceptionHandling()
                .accessDeniedHandler(authorAccessDeniedHandlerComponent())
                .authenticationEntryPoint(authorFailedComponent())
                .and()
                //jwt自定义的过滤器
                .addFilterBefore(new JwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                //打开跨站请求防护
                .cors()
                .and()
                //关闭csrf
                .csrf().disable()
                //关闭session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 自定义鉴权失败处理器
     * @return AuthorFailedComponent
     */
    @Bean
    public AuthorFailedComponent authorFailedComponent() {
        return new AuthorFailedComponent();
    }

    /**
     * 自定义权限不足处理器
     * @return AuthorAccessDeniedHandlerComponent
     */
    @Bean
    public AuthorAccessDeniedHandlerComponent authorAccessDeniedHandlerComponent() {
        return new AuthorAccessDeniedHandlerComponent();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }



}
