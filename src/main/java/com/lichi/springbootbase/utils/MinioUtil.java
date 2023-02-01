package com.lichi.springbootbase.utils;

import com.lichi.springbootbase.minio.MinioEntity;
import io.minio.*;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description: minio工具类
 * @author: lychee
 * @Date: 2023/1/30 23:03
 * @Version: 1.0
 * @Since: 2023/1/30
 */
@Component
public class MinioUtil {

    private static MinioClient minioClient;
    @Autowired
    private void setMinioClient(MinioClient minioClient) {
        MinioUtil.minioClient = minioClient;
    }

    private static MinioEntity minioEntity;
    @Autowired
    private void setMinioEntity(MinioEntity minioEntity) {
        MinioUtil.minioEntity = minioEntity;
    }

    /**
     * 上传文件
     * @param file
     * @return
     */
    public static Map<String, Object> upload(List<MultipartFile> file, String dirPath) {
        if (CollectionUtils.isEmpty(file)) {
            return null;
        }
        if (Objects.isNull(dirPath)) {
            dirPath = new SimpleDateFormat("yyyyMMdd").format(new Date());
        }
        var fileNameList = new ArrayList<Map<String,String>>(file.size());
        var respMap = new HashMap<String,Object>(2);
        final var finalPath = dirPath;
        file.forEach(item -> {
            try {
                var fileName = UUID.randomUUID().toString()
                        .replace("-", "") +
                        item.getOriginalFilename()
                                .substring(item.getOriginalFilename().lastIndexOf("."));
                if (!bucketExists(minioEntity.getBucketName())) {
                    minioClient.makeBucket(MakeBucketArgs
                            .builder()
                            .bucket(minioEntity.getBucketName())
                            .build()
                    );
                }
                var path = getDir(finalPath);
                minioClient.putObject(PutObjectArgs
                        .builder()
                        .bucket(minioEntity.getBucketName())
                        .object(path + fileName)
                        .stream(item.getInputStream(), item.getSize(), -1)
                        .build()
                );
                var url = minioEntity.getEndpoint() + "/" + minioEntity.getBucketName() + "/" + path + fileName;
                fileNameList.add(Map.of("fileName", fileName, "url", url));
            } catch (Exception e) {
                throw new RuntimeException("上传文件失败");
            }
        });
        respMap.put("bucketName", minioEntity.getBucketName());
        respMap.put("fileName", fileNameList);

        return respMap;
    }

    /**
     * 创建文件夹
     * @param path 文件夹路径
     * @return String 文件夹路径
     */
    private static String getDir(String path) {
        if(ObjectUtils.isEmpty(path)) {
            path = new SimpleDateFormat("yyyyMMdd").format(new Date());
        }
        try {
            if (!objectExists(path)) {
                var finalPath = path;
                if (!path.endsWith("/")) {
                    finalPath += "/";
                }
                minioClient.putObject(PutObjectArgs
                        .builder()
                        .bucket(minioEntity.getBucketName())
                        .object(finalPath)
                        .stream(new ByteArrayInputStream(new byte[0]), 0, -1)
                        .build()
                );
            }

        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException |
                 InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException |
                 XmlParserException e) {
            e.printStackTrace();
            throw new RuntimeException("创建文件夹失败");
        }
        return path.endsWith("/") ? path : path + "/";
    }

    /**
     * 判断文件是否存在
     * @param objectName
     * @return
     */
    private static boolean objectExists(String objectName) {
        try {
            return minioClient
                    .statObject(StatObjectArgs
                            .builder()
                            .bucket(minioEntity.getBucketName())
                            .object(objectName)
                            .build()).object() != null;
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException |
                 InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException |
                 XmlParserException e) {
            return false;
        }
    }

    /**
     * 判断bucket是否存在
     * @param bucketName
     * @return
     */
    private static boolean bucketExists(String bucketName) {
        try {
           return minioClient
                     .bucketExists(BucketExistsArgs
                             .builder()
                             .bucket(bucketName)
                             .build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
