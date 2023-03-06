package com.lichi.springbootbase.oss.minio;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description: minio对象
 * @author: lychee
 * @Date: 2023/1/30 22:59
 * @Version: 1.0
 * @Since: 2023/1/30
 */
@Data
@Component
@ConfigurationProperties(prefix = "minio")
public class MinioEntity {
    /**
     * minio的endpoint
     */
    private String endpoint;
    /**
     * minio的accessKey
     */
    private String accessKey;
    /**
     * minio的secretKey
     */
    private String secretKey;
    /**
     * minio的存储空间名称
     */
    private String bucketName;
}
