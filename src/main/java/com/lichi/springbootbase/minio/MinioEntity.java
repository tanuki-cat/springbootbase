package com.lichi.springbootbase.minio;

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
    private String endpoint;
    private String accessKey;
    private String secretKey;

    private String bucketName;
}
