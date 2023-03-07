package com.lichi.springbootbase.oss.qiniu;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author lychee
 * @version 1.0
 * @description 七牛对象存储实体类
 * @date 2023/3/4 23:03
 * @since 2023/3/4
 */
@Data
@Component
@ConfigurationProperties(prefix = "qiniu")
public class QiNioEntity {
    /**
     * 七牛云的accessKey
     */
    private String accessKey;
    /**
     * 七牛云的secretKey
     */
    private String secretKey;
    /**
     * 七牛云的存储空间名称
     */
    private String bucketName;
    /**
     * 七牛云的域名
     */
    private String domain;
    /**
     * 上传文件的过期时间
     */
    private Long expireSeconds;
}
