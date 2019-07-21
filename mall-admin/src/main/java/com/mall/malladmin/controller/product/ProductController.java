package com.mall.malladmin.controller.product;


import com.github.pagehelper.PageInfo;
import com.mall.malladmin.service.product.ProductPropertyValueService;
import com.mall.malladmin.service.product.ProductService;
import com.mall.malladmin.dto.common.CommonResultDto;
import com.mall.malladmin.dto.product.ProductDto;
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
     * @param dto
     * @return
     */
    @GetMapping(value = "/getPage.do")
    protected CommonResultDto getPage(ProductDto dto){
        PageInfo<ProductDto> result = productService.findPage(dto);
        return new CommonResultDto().success(result);
    }

    /**
     * 新建商品信息
     * @param dto
     * @return
     */
    @PostMapping(value = "/create.do")
    protected CommonResultDto create(@RequestBody ProductDto dto){
        return productService.create(dto);
    }

    /**
     * 更新
     * @return
     */
    @PostMapping(value = "/update.do/{id}")
    protected CommonResultDto update(@PathVariable Integer id , @RequestBody ProductDto dto){
        if(null == dto.getProductId()){
            return new CommonResultDto().validateFailed("商品编号为空！");
        }
        return productService.update(id, dto);
    }
    /**
     * 根据商品名称获取商品列表
     * @return
     */
    @PostMapping(value = "/findProductByName.do")
    protected CommonResultDto findProductByName(String name){
        List<ProductDto> result = productService.findByName(name);
        return new CommonResultDto().success(result);
    }
    /**
     * 根据ID获取商品信息
     * @return
     */
    @GetMapping(value = "/getProduct.do/{id}")
    protected CommonResultDto getProduct(@PathVariable Integer id){
        if(null == id){
            return new CommonResultDto().validateFailed("id为空！");
        }
        ProductDto result = productService.findById(id);
        return new CommonResultDto().success(result);
    }

    /**
     * 删除
     * @param ids
     * @return
     */
    @PostMapping(value = "/delete.do")
    protected CommonResultDto delete(Integer... ids){
        CommonResultDto result = productService.deleteList(ids);
        return result;
    }

    /**
     * 修改是否上下架状态
     * @param isPutaway
     * @param ids
     * @return
     */
    @PostMapping(value = "/updateIsPutaway.do")
    protected CommonResultDto updateIsPutaway(String isPutaway, Integer...ids){
        CommonResultDto result = productService.updateIsPutAway(isPutaway, ids);
        return result;
    }
    @PostMapping(value = "/upload.do")
    protected CommonResultDto upload(@RequestParam("picture") MultipartFile picture, HttpServletRequest request){
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
            return new CommonResultDto().success("image/"+fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return new CommonResultDto().failed();
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
                return new CommonResultDto().success();
            } else {
                return new CommonResultDto().failed();
            }
        } else {
            return new CommonResultDto().failed();
        }
    }
}
