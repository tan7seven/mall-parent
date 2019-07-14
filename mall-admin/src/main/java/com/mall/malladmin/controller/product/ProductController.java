package com.mall.malladmin.controller.product;


import com.github.pagehelper.PageInfo;
import com.mall.malladmin.service.product.ProductPropertyValueService;
import com.mall.malladmin.service.product.ProductService;
import com.mall.malladmin.vo.common.CommonResultVo;
import com.mall.malladmin.vo.product.ProductVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 商品信息
 */
@Slf4j
@RestController
@RequestMapping(value = "/productController")
public class ProductController {

    @Value("${pic.path}")
    private String picPath;

    @Resource(name = "productService")
    private ProductService productService;

    @Autowired
    private ProductPropertyValueService productPropertyValueService;
    /**
     * 获取商品分页信息
     * @param vo
     * @return
     */
    @GetMapping(value = "/getPage.do")
    protected CommonResultVo getPage(ProductVo vo){
        PageInfo<ProductVo> result = productService.findPage(vo);
        return new CommonResultVo().success(result);
    }

    /**
     * 新建商品信息
     * @param vo
     * @return
     */
    @PostMapping(value = "/create.do")
    protected CommonResultVo create(@RequestBody ProductVo vo){
        return productService.create(vo);
    }

    /**
     * 更新
     * @return
     */
    @PostMapping(value = "/update.do/{id}")
    protected CommonResultVo update(@PathVariable Integer id , @RequestBody ProductVo vo){
        if(null == vo.getProductId()){
            return new CommonResultVo().validateFailed("商品编号为空！");
        }
        return productService.update(id, vo);
    }
    /**
     * 根据商品名称获取商品列表
     * @return
     */
    @PostMapping(value = "/findProductByName.do")
    protected CommonResultVo findProductByName(String name){
        List<ProductVo> result = productService.findByName(name);
        return new CommonResultVo().success(result);
    }
    /**
     * 根据ID获取商品信息
     * @return
     */
    @GetMapping(value = "/getProduct.do/{id}")
    protected CommonResultVo getProduct(@PathVariable Integer id){
        if(null == id){
            return new CommonResultVo().validateFailed("id为空！");
        }
        ProductVo result = productService.findById(id);
        return new CommonResultVo().success(result);
    }

    /**
     * 删除
     * @param ids
     * @return
     */
    @PostMapping(value = "/delete.do")
    protected CommonResultVo delete(Integer... ids){
        CommonResultVo result = productService.deleteList(ids);
        return result;
    }

    /**
     * 修改是否上下架状态
     * @param isPutaway
     * @param ids
     * @return
     */
    @PostMapping(value = "/updateIsPutaway.do")
    protected CommonResultVo updateIsPutaway(String isPutaway, Integer...ids){
        CommonResultVo result = productService.updateIsPutAway(isPutaway, ids);
        return result;
    }
    @PostMapping(value = "/upload.do")
    protected  CommonResultVo upload(@RequestParam("picture") MultipartFile picture, HttpServletRequest request){
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
            return new CommonResultVo().success("image/"+fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return new CommonResultVo().failed();
        }
    }
    @PostMapping(value = "/deletePic.do")
    protected Object deletePic(String picUrl){
        int lastIndexOf = picUrl.lastIndexOf("/");
        String sb = picUrl.substring(lastIndexOf + 1, picUrl.length());
        sb = picPath + sb;
        File file = new File(sb);
        if (file.exists()) {
            if (file.delete()) {
                return new CommonResultVo().success();
            } else {
                return new CommonResultVo().failed();
            }
        } else {
            return new CommonResultVo().failed();
        }
    }
}
