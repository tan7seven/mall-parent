package com.mall.mallwechar.service.pruduct.impl;

import com.mall.mallmodel.dto.product.ProductDto;
import com.mall.mallmodel.dto.product.ProductTypeDto;
import com.mall.mallmodel.entity.product.ProductTypeEntity;
import com.mall.mallwechar.repository.product.ProductTypeRepository;
import com.mall.mallwechar.service.pruduct.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Override
    public List<ProductTypeEntity> getTypeList(ProductTypeDto dto) {

        return productTypeRepository.findAll();
    }

    @Override
    public List<ProductDto> getList() {
        return null;
    }
}
