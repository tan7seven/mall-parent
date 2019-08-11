package com.mall.mallmodel.dto.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommonDto implements Serializable{
     private Integer pageNum = 1;
     private Integer pageSize = 10;
}
