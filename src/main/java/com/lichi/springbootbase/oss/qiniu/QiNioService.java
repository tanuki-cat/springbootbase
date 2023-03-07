package com.lichi.springbootbase.oss.qiniu;

import com.lichi.springbootbase.response.ApiResponse;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author lychee
 * @version 1.0
 * @description 七牛对象存储服务
 * @date 2023/3/4 23:13
 * @since 2023/3/4
 */
@Service
@AllArgsConstructor
public class QiNioService extends AbstractQiNiuService{

    private final QiNioEntity qiNioEntity;
    @NotNull
    private UploadManager uploadManager() {
        return new UploadManager(new Configuration());
    }

    /**
     * 获取上传凭证
     * @param fileKey       文件名(key)
     * @param expireSeconds 过期时间
     * @param policy        上传策略
     * @param bucketName    存储桶名称
     * @return 上传凭证
     */
    @Override
    public String getAuthToken(String fileKey, Long expireSeconds,
                               StringMap policy,String bucketName) {
        return Auth.create(qiNioEntity.getAccessKey(), qiNioEntity.getSecretKey())
                .uploadToken(bucketName, fileKey, expireSeconds, policy);
    }

    /**
     * 上传文件
     * @param file          文件
     * @param fileKey       文件名(key)
     * @param expireSeconds 过期时间
     * @param policy        上传策略
     * @return              响应
     */
    @Override
    public ApiResponse<?> upload(MultipartFile file,
                                 String fileKey, Long expireSeconds,
                                 StringMap policy, String bucketName) {
        try {
            var expire = expireSeconds == null ? qiNioEntity.getExpireSeconds() : expireSeconds;
            var key = fileKey == null ? file.getOriginalFilename() : fileKey;
            var bucket = bucketName == null ? qiNioEntity.getBucketName() : bucketName;
            var response = uploadManager()
                    .put(file.getBytes(), key, getAuthToken(fileKey, expire, policy,bucket));
            return ApiResponse.success(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error(e.getMessage());
        }
    }


}
