package com.mall.common.config;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/1/7
 */

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 用于Ali产品的配置 （OSS）
 * @author: Orcas
 * @version:1.0.0
 * @date: 2019/4/28
 */
@Data
@Configuration
public class AliyunConfig {
    // 地域节点
    @Value("${aliyun.oss.endpoint}")
    private String endpoint;
    @Value("${aliyun.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.accessKeySecret}")
    private String accessKeySecret;
    @Value("${aliyun.oss.bucketName}")
    private String bucketName;
    @Value("${aliyun.oss.urlPrefix}")
    private String urlPrefix;

    @Bean
    public OSS ossClient() {
        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }
}

