package com.mall.manage.model.vo.product.attr;

import com.mall.manage.model.vo.product.BaseVO;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class AttrValueVO extends BaseVO {
    /**
     * 属性名编码
     */
    private Long nameId;
    /**
     * 属性值
     */
    private String value;
}
