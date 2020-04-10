package com.mall.manage.controller.common;

import com.mall.common.constant.CommonConstant;
import com.mall.common.manage.UploadPicManage;
import com.mall.common.model.vo.RestResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 通用
 */
@ApiIgnore
@Slf4j
@RestController
@RequestMapping(value = "/common")
public class CommonController {

    @Value("${pic.path:}")
    private String picPath;

    @Autowired
    private UploadPicManage uploadPicManage;

    /**
     * 上传图片到oss
     */
    @PostMapping(value = "/oss/pic-upload")
    protected RestResult ossUpload(@RequestParam("pictureUrl") MultipartFile pictureUrl, HttpServletRequest request){
        String filePath = uploadPicManage.upload(pictureUrl);
        if (null == filePath) {
            return RestResult.failed("图片上传失败");
        }
        return RestResult.success(CommonConstant.IMG_PRE+filePath);
    }
}
