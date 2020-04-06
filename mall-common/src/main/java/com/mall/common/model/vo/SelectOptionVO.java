package com.mall.common.model.vo;

import com.mall.common.model.BaseModel;
import lombok.Data;

/**
 * select选择框VO
 */
@Data
public class SelectOptionVO extends BaseModel {
    private String value;
    private String label;
}
