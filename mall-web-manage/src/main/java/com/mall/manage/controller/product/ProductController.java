package com.mall.manage.controller.product;


import com.github.pagehelper.PageInfo;
import com.mall.common.vo.RestResult;
import com.mall.dao.dto.product.ProductDTO;
import com.mall.manage.service.product.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Api(value = "商品信息", tags = "商品信息")
@Slf4j
@RestController
@RequestMapping(value = "/productController")
public class ProductController {

    @Value("${pic.path}")
    private String picPath;

    @Resource(name = "productService")
    private ProductService productService;

    @ApiOperation("分页查询")
    @GetMapping(value = "/getPage.do")
    protected RestResult getPage(ProductDTO dto){
        PageInfo<ProductDTO> result = productService.findPage(dto);
        return RestResult.success(result);
    }

    @ApiOperation("新建商品信息")
    @PreAuthorize(" hasAuthority('PMS:PRODUCT:CREATE') or hasRole('ADMIN')")
    @PostMapping(value = "/create.do")
    protected RestResult create(@RequestBody ProductDTO dto){
        return productService.create(dto);
    }


    @ApiOperation("更新")
    @PreAuthorize(" hasAuthority('PMS:PRODUCT:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "/update.do/{id}")
    protected RestResult update(@PathVariable Integer id , @RequestBody ProductDTO dto){
        if(null == dto.getProductId()){
            return RestResult.validateFailed("商品编号为空！");
        }
        return productService.update(id, dto);
    }

    @ApiOperation("根据商品名称获取商品列表")
    @PostMapping(value = "/findProductByName.do")
    protected RestResult findProductByName(String name){
        List<ProductDTO> result = productService.findByName(name);
        return RestResult.success(result);
    }
    @ApiOperation("根据ID获取商品信息")
    @GetMapping(value = "/getProduct.do/{id}")
    protected RestResult getProduct(@PathVariable Integer id){
        if(null == id){
            return RestResult.validateFailed("id为空！");
        }
        ProductDTO result = productService.findById(id);
        return RestResult.success(result);
    }

    @ApiOperation("删除-逻辑删除")
    @PreAuthorize(" hasAuthority('PMS:PRODUCT:DELETE') or hasRole('ADMIN')")
    @PostMapping(value = "/delete.do")
    protected RestResult delete(List<Integer> ids){
        RestResult result = productService.deleteList(ids);
        return result;
    }

    @ApiOperation("修改是否上下架状态")
    @PreAuthorize(" hasAuthority('PMS:PRODUCT:SWITCH') or hasRole('ADMIN')")
    @PostMapping(value = "/updateIsPutaway.do")
    protected RestResult updateIsPutaway(String isPutaway, List<Integer>  ids){
        RestResult result = productService.updateIsPutAway(isPutaway, ids);
        return result;
    }

    @ApiOperation("文件上传")
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

    @ApiOperation("删除图片文件")
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
