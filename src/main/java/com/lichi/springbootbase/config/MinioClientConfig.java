package com.lichi.springbootbase.config;

import com.lichi.springbootbase.oss.minio.MinioEntity;
import io.minio.MinioClient;
import jakarta.servlet.MultipartConfigElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

/**
 * @Description: minio客户端配置
 * @author: lychee
 * @Date: 2023/1/30 23:00
 * @Version: 1.0
 * @Since: 2023/1/30
 */
@Configuration
public class MinioClientConfig {

    @Autowired
    private MinioEntity minioEntity;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(minioEntity.getEndpoint())
                .credentials(minioEntity.getAccessKey(), minioEntity.getSecretKey())
                .build();
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.ofMegabytes(29));
        factory.setMaxRequestSize(DataSize.parse("200MB"));
        return factory.createMultipartConfig();
    }
}
