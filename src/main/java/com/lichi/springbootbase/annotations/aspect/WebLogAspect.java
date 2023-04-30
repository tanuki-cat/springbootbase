package com.lichi.springbootbase.annotations.aspect;

import com.alibaba.fastjson2.JSON;
import com.lichi.springbootbase.annotations.WebLog;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.*;

/**
 * @Description: WebLog Aop切面
 * @author: lychee
 * @Date: 2022/12/21 10:14
 * @Version: 1.0
 * @Since: 2022/12/21
 */
@Component
@Aspect
//只在测试环境和开发环境开启
//@Profile({"dev", "test"})
public class WebLogAspect {

    /**
     * 日志实例
     */
    private final static Logger log = LoggerFactory.getLogger(WebLogAspect.class);
    /**
     * 换行符
     */
    private final static String LINE_SEPARATOR = System.lineSeparator();

    /**
     * 定义切点 以 @WebLog 注解为切点
     */
    @Pointcut("@annotation(com.lichi.springbootbase.annotations.WebLog)")
    public void webLog() {

    }

    /**
     * 切点之前
     * @param joinPoint JoinPoint
     * @throws Throwable
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = Optional.ofNullable(attributes)
                                        .map(ServletRequestAttributes::getRequest)
                                        .orElseThrow(() -> new RuntimeException("request is null"));

        String methodDescription = getAspectLogDescription(joinPoint);

        // 打印请求相关参数
        log.info("========================================== Start ==========================================");
        // 打印请求 url
        log.info("URL            : {}", getUrl());
        // 打印描述信息
        log.info("Description    : {}", methodDescription);
        // 打印 Http method
        log.info("HTTP Method    : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 打印请求的 IP
        log.info("IP             : {}", getIpAddress());
        // 打印请求入参名称
        var methodName = ((MethodSignature) joinPoint.getSignature()).getMethod().getName();
        log.info("Method Name    : {}", methodName);
        // 打印请求入参
        log.info("Request Args   : {}", getArgs(joinPoint));
    }

    /**
     * 切点之后
     * @param joinPoint JoinPoint
     */
    @After("webLog()")
    public void doAfter(JoinPoint joinPoint) {
        // TODO document why this method is empty
    }

    /**
     * 切点环绕
     * @param joinPoint ProceedingJoinPoint
     * @return Object
     * @throws Throwable`
     */
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        // 打印出参
        log.info("Response Args  : {}", JSON.toJSONString(result));
        // 执行耗时
        log.info("Time-Consuming : {} ms", System.currentTimeMillis() - startTime);
        log.info("=========================================== End ===========================================" + LINE_SEPARATOR);
        return result;
    }

    //从切点中获取IP地址
    private String getIpAddress() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Optional.ofNullable(attributes)
                .map(ServletRequestAttributes::getRequest)
                .orElseThrow(() -> new RuntimeException("request is null"));
        return request.getRemoteAddr();
    }
    //从切点中获取参数名称
    private List<String> getArgsName(JoinPoint joinPoint) {
        var signature = joinPoint.getSignature();
        var methodSignature = (MethodSignature) signature;
        var parameterNames = methodSignature.getParameterNames();
        return new ArrayList<>(Arrays.asList(parameterNames));
    }

    //从切点中获取url
    private String getUrl() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Optional.ofNullable(attributes)
                .map(ServletRequestAttributes::getRequest)
                .orElseThrow(() -> new RuntimeException("request is null"));
        return request.getRequestURL().toString();
    }
    //获取参数名称和参数值
    private Map<String, Object> getArgs(JoinPoint joinPoint) {
        var args = joinPoint.getArgs();
        var argNameList = getArgsName(joinPoint);
        var newArgs = new HashMap<String, Object>();
        for (var i = 0; i < argNameList.size(); i++) {
            newArgs.put(argNameList.get(i), args[i]);
        }
        return newArgs;
    }

    /**
     * 获取切点描述信息
     * @param joinPoint JoinPoint
     * @return String
     * @throws Exception
     */
    private String getAspectLogDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class<?> targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        StringBuilder description = new StringBuilder();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class<?>[] clazz = method.getParameterTypes();
                if (clazz.length == arguments.length) {
                    description.append(method.getAnnotation(WebLog.class).description());
                    break;
                }
            }
        }
        return description.toString();
    }

    /**
     * @deprecated  {since="2023/04/30" ,forRemoval=true, @link #getArgsName(JoinPoint)}
     * 获取参数名
     * @param clazz Class
     * @param methodName 方法名
     * @return List<String>
     */
    @Deprecated
    private List<String> getArgsName(Class<?> clazz, String methodName) {
        var argNames = new ArrayList<String>();
        try {
            var methods = clazz.getDeclaredMethods();
            for (var method : methods) {
                if (method.getName().equals(methodName)) {
                    var params = method.getParameters();
                    for (var param : params) {
                        argNames.add(param.getName());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取参数名失败 {}", e.getMessage());
        }
        return argNames;
    }
}
