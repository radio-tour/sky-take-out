package com.sky.config;

import com.sky.properties.AliOssProperties;
import com.sky.properties.MinioProperties;
import com.sky.utils.AliOssUtil;
import com.sky.utils.MinioUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类，用于创建 AliOssUtil 对象
 */
@Configuration
@Slf4j
public class OssConfiguration {

//    @Bean
//    @ConditionalOnMissingBean
//    public AliOssUtil aliOssUtil(AliOssProperties aliOssProperties) {
//        log.info("开始创建阿里云文件上传工具类对象：{}", aliOssProperties);
//        return new AliOssUtil(
//                aliOssProperties.getEndpoint(),
//                aliOssProperties.getAccessKeyId(),
//                aliOssProperties.getAccessKeySecret(),
//                aliOssProperties.getBucketName());
//    }

    @Bean
    @ConditionalOnMissingBean
    public MinioUtil minioUtil(MinioProperties minioProperties) {
        log.info("开始创建 minio 文件上传工具类对象：{}", minioProperties);

        return new MinioUtil(
                minioProperties.getEndpoint(),
                minioProperties.getAccessKeyId(),
                minioProperties.getAccessKeySecret(),
                minioProperties.getBucketName()
        );
    }

}
