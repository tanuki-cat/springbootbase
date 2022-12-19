package com.lichi.springbootbase.response.enums;

import org.springframework.http.HttpStatus;

/**
 * @description: 请求响应状态枚举
 * @author: lichi
 * @date: 2022/12/17 18:45
 * @version: 1.0
 * @since: 2022/12/17
 */
public enum ApiResponseStatusEnum {

    /**
     * 请求成功
     */
    SUCCESS(HttpStatus.OK.value(), "请求成功"),
    /**
     * 请求失败
     */
    FAIL(HttpStatus.BAD_REQUEST.value(), "请求失败"),
    /**
     * 未认证（签名错误）
     */
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED.value(), "未认证（签名错误）"),
    /**
     * 接口不存在
     */
    NOT_FOUND(HttpStatus.NOT_FOUND.value(), "接口不存在"),
    /**
     * 服务器内部错误
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "服务器内部错误");

    /**
     * 响应码
     */
    private int code;
    /**
     * 响应消息
     */
    private String message;

    ApiResponseStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
