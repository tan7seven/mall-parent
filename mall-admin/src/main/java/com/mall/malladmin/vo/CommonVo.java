package com.mall.malladmin.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommonVo implements Serializable{
     private Integer pageNum = 1;
     private Integer pageSize = 10;
}
