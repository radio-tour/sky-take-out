package com.sky.utils;

import io.minio.BucketExistsArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.util.UUID;

@Data
@AllArgsConstructor
@Slf4j
public class MinioUtil {
    
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    
    public String upload(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();

        if (originalFilename == null) {
            return "";
        }

        // 截取原始文件名的后缀
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String objectName = UUID.randomUUID() + extension;

        try (MinioClient minioClient = MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKeyId, accessKeySecret)
                .build())
        {
            ByteArrayInputStream in = new ByteArrayInputStream(file.getBytes());
            boolean exists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());

            if (exists) {
                minioClient.putObject(
                        PutObjectArgs.builder()
                                .bucket(bucketName)
                                .object(objectName)
                                .stream(in, in.available(), -1)
                                .contentType(file.getContentType())
                                .build()
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringBuilder stringBuilder = new StringBuilder(endpoint);
        stringBuilder
                .append("/")
                .append(bucketName)
                .append("/")
                .append(objectName);

        log.info("文件上传到:{}", stringBuilder.toString());

        return stringBuilder.toString();
    }
}
