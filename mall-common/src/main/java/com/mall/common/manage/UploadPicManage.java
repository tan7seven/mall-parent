package com.mall.common.manage;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.PutObjectResult;
import com.mall.common.config.AliyunConfig;
import com.mall.common.exception.BusinessException;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/1/7
 */
@Service
public class UploadPicManage {
    // 允许上传的格式
    private static final String[] IMAGE_TYPE = new String[]{".bmp", ".jpg", ".jpeg", ".gif", ".png"};

    @Autowired
    private OSS ossClient;

    @Autowired
    private AliyunConfig aliyunConfig;

    public String upload(MultipartFile multipartFile) {
        PutObjectResult putObjectResult = null;
        // 1. 对上传的图片进行校验: 这里简单校验后缀名
        // 另外可通过ImageIO读取图片的长宽来判断是否是图片,校验图片的大小等。
        boolean isLegal = false;
        for (String type : IMAGE_TYPE) {
            if (StringUtils.endsWithIgnoreCase(multipartFile.getOriginalFilename(), type)) {
                isLegal = true;
                break;
            }
        }
        // 格式错误, 返回与前端约定的error
        if (!isLegal) {
            throw new BusinessException("图片格式错误");
        }
        // 2. 准备上传API的参数
        String fileName = multipartFile.getOriginalFilename();
        String filePath = this.getFilePath(fileName);

        // 3. 上传至阿里OSS

        try {
            putObjectResult = ossClient.putObject(aliyunConfig.getBucketName(), filePath, new ByteArrayInputStream(multipartFile.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }

    /**
     * 上传的目录
     * 目录: 根据年月日归档
     * 文件名: 时间戳 + 随机数
     * @param fileName
     * @return
     */
    private String getFilePath(String fileName) {
        return "images/" + +System.currentTimeMillis() +
                RandomUtils.nextInt(1000, 9999) + "." + StringUtils.substringAfterLast(fileName, ".");
    }
}

