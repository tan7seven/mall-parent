package com.mall.manage.controller.common;

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
        return RestResult.success(filePath);
    }



    @PostMapping(value = "/upload.do")
    protected RestResult upload(@RequestParam("picture") MultipartFile picture, HttpServletRequest request){
        //获取文件在服务器的储存位置
        String path = picPath;
        File filePath = new File(path);
        if (!filePath.exists() && !filePath.isDirectory()) {
            filePath.mkdir();
        }
        //获取原始文件名称(包含格式)
        String originalFileName = picture.getOriginalFilename();
        //获取文件类型，以最后一个`.`为标识
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        //获取文件名称（不包含格式）
        String name = originalFileName.substring(0, originalFileName.lastIndexOf("."));
        //设置文件新名称: 当前时间+文件名称（不包含格式）
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = sdf.format(d);
        String fileName = date + name + "." + type;
        //在指定路径下创建一个文件
        File targetFile = new File(path, fileName);
        //将文件保存到服务器指定位置
        try {
            picture.transferTo(targetFile);
            //将文件在服务器的存储路径返回
            return RestResult.success("image/"+fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return RestResult.failed();
        }
    }

    /**
     * 删除文件
     * @param picUrl
     * @return
     */
    @PostMapping(value = "/deletePic.do")
    protected Object deletePic(String picUrl){
        int lastIndexOf = picUrl.lastIndexOf("/");
        String sb = picUrl.substring(lastIndexOf + 1, picUrl.length());
        sb = picPath + sb;
        File file = new File(sb);
        if (file.exists()) {
            if (file.delete()) {
                return RestResult.success();
            } else {
                return RestResult.failed();
            }
        } else {
            return RestResult.failed();
        }
    }
}
