package com.lichi.springbootbase.response;

import com.lichi.springbootbase.response.enums.ApiResponseStatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 接口通用响应类型
 * @author: lichi
 * @date: 2022/12/17 18:33
 * @version: 1.0
 * @since: 2022/12/17
 */
@NoArgsConstructor
@Data
public class ApiResponse <T> {
    /**
     * 响应码
     */
    private int code;
    /**
     * 响应消息
     */
    private String message;
    /**
     * 响应数据
     */
    private T data;

    /**
     * 全参构造器
     * @param code 响应码
     * @param message 响应消息
     * @param data 响应数据
     */
    public ApiResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功响应构造
     * @param statusEnum 响应码枚举类
     * @param message 响应消息
     * @param data 响应数据
     * @return ApiResponse<T>
     */
    private static <T> ApiResponse<T> responseSuccess(ApiResponseStatusEnum statusEnum, String message, T data) {
        return new ApiResponse<>(statusEnum.getCode(), message, data);
    }

    /**
     * 失败响应构造
     * @param statusEnum 响应码枚举类
     * @param message 响应消息
     * @param data 响应数据
     * @return ApiResponse<T>
     * @param <T>
     */
    private static <T> ApiResponse<T> responseFail(ApiResponseStatusEnum statusEnum, String message, T data) {
        if (statusEnum.equals(ApiResponseStatusEnum.SUCCESS)) {
            throw new IllegalArgumentException("响应码不合法");
        }
        return new ApiResponse<>(statusEnum.getCode(), message, data);
    }

    /**
     * 成功响应
     * @param message 响应消息
     * @param data 响应数据
     * @return ApiResponse<T>
     */
    public static <T> ApiResponse<T> success(String message, T data) {
        return responseSuccess(ApiResponseStatusEnum.SUCCESS, message, data);
    }

    /**
     * 成功响应
     * @param data 响应数据
     * @return ApiResponse<T>
     */
    public static <T> ApiResponse<T> success(T data) {
        return responseSuccess(ApiResponseStatusEnum.SUCCESS, ApiResponseStatusEnum.SUCCESS.getMessage(), data);
    }

    /**
     * 成功响应
     * @param message 响应消息
     * @return ApiResponse<T>
     */
    public static <T> ApiResponse<T> success(String message) {
        return responseSuccess(ApiResponseStatusEnum.SUCCESS, message, null);
    }

    /**
     * 成功响应
     * @return ApiResponse<T>
     */
    public static <T> ApiResponse<T> success() {
        return responseSuccess(ApiResponseStatusEnum.SUCCESS, ApiResponseStatusEnum.SUCCESS.getMessage(), null);
    }

    /**
     * 失败响应
     * @param statusEnum 响应码
     * @param message 响应消息
     * @param data 响应数据
     * @return ApiResponse<T>
     */
    public static <T> ApiResponse<T> fail(ApiResponseStatusEnum statusEnum,String message, T data) {
        return responseFail(statusEnum, message, data);
    }

    /**
     * 失败响应
     * @param message
     * @param data
     * @return ApiResponse<T>
     * @param <T>
     */
    public static <T> ApiResponse<T> fail(String message, T data) {
        return responseFail(ApiResponseStatusEnum.FAIL, message, data);
    }

    /**
     * 失败响应
     * @param message 响应消息
     * @return ApiResponse<T>
     * @param <T>
     */
    public static <T> ApiResponse<T> fail(String message) {
        return responseFail(ApiResponseStatusEnum.FAIL, message, null);
    }

    /**
     * 失败响应
     * @return ApiResponse<T>
     * @param <T>
     */
    public static <T> ApiResponse<T> fail() {
        return responseFail(ApiResponseStatusEnum.FAIL, ApiResponseStatusEnum.FAIL.getMessage(), null);
    }

    /**
     * 错误响应
     * @param statusEnum 响应码
     * @param message 响应消息
     * @param data 响应数据
     * @return ApiResponse<T>
     * @param <T>
     */
    public static <T> ApiResponse<T> error(ApiResponseStatusEnum statusEnum, String message, T data) {
        return responseFail(statusEnum,message,data);
    }

    /**
     * 错误响应 默认服务器内部错误
     * @param message 响应消息
     * @param data  响应数据
     * @return ApiResponse<T>
     * @param <T>
     */
    public static <T> ApiResponse<T> error(String message, T data) {
        return responseFail(ApiResponseStatusEnum.INTERNAL_SERVER_ERROR,message,data);
    }

    /**
     * 错误响应 默认服务器内部错误
     * @param message 响应消息
     * @return ApiResponse<T>
     * @param <T>
     */
    public static <T> ApiResponse<T> error(String message) {
        return responseFail(ApiResponseStatusEnum.INTERNAL_SERVER_ERROR,message,null);
    }

    /**
     * 错误响应 默认服务器内部错误
     * @return ApiResponse<T>
     * @param <T>
     */
    public static <T> ApiResponse<T> error() {
        return responseFail(ApiResponseStatusEnum.INTERNAL_SERVER_ERROR,ApiResponseStatusEnum.INTERNAL_SERVER_ERROR.getMessage(),null);
    }
}
