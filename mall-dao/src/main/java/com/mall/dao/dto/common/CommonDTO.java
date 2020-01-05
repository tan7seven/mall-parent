package com.mall.dao.dto.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommonDTO implements Serializable{
     private Integer pageNum = 1;
     private Integer pageSize = 10;
}
