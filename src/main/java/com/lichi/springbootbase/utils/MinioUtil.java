package com.lichi.springbootbase.utils;

import com.lichi.springbootbase.minio.MinioEntity;
import io.minio.*;
import io.minio.http.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public static Map<String, Object> upload(List<MultipartFile> file) {
        if (CollectionUtils.isEmpty(file)) {
            return null;
        }
        var fileNameList = new ArrayList<Map<String,String>>(file.size());
        var respMap = new HashMap<String,Object>(2);
        file.forEach(item -> {
            try {
                var fileName = item.getOriginalFilename();
                if (!bucketExists(minioEntity.getBucketName())) {
                    minioClient.makeBucket(MakeBucketArgs
                            .builder()
                            .bucket(minioEntity.getBucketName())
                            .build()
                    );
                }

                minioClient.putObject(PutObjectArgs
                        .builder()
                        .bucket(minioEntity.getBucketName())
                        .object(fileName)
                        .stream(item.getInputStream(), item.getSize(), -1)
                        .build()
                );
                var url = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs
                        .builder()
                        .bucket(minioEntity.getBucketName())
                        .method(Method.GET)
                        .object(fileName)
                        .expiry(7 * 24 * 60 * 60)
                        .build()
                );
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
