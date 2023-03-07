package com.lichi.springbootbase.oss.qiniu;

import com.lichi.springbootbase.response.ApiResponse;
import com.qiniu.util.StringMap;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author lychee
 * @version 1.0
 * @description 1
 * @date 2023/3/5 00:04
 * @since 2023/3/5
 */
public abstract class AbstractQiNiuService {


    /**
     * 获取签名
     * @param fileKey       文件名(key)
     * @param expireSeconds 过期时间
     * @param policy        上传策略
     * @param bucketName    存储桶名称
     * @return              签名
     */
    abstract String getAuthToken(String fileKey, Long expireSeconds,
                                 StringMap policy, String bucketName);

    /**
     * 上传文件
     * @param file          文件
     * @param fileKey       文件名(key)
     * @param expireSeconds 过期时间
     * @param policy        上传策略
     * @param bucketName    存储桶名称
     * @return              响应
     */
    abstract ApiResponse<?> upload(MultipartFile file,
                                   String fileKey, Long expireSeconds,
                                   StringMap policy, String bucketName);


}
