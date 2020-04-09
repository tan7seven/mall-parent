package com.mall.manage.model.vo.product.attr;

import com.alibaba.fastjson.JSONObject;
import com.mall.manage.model.vo.product.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
@ApiModel
public class AttrValueVO extends BaseVO {
    @ApiModelProperty(value = "主键")
    private Long id;
    /**
     * 属性名编码
     */
    @ApiModelProperty(value = "属性名编码")
    private Long nameId;
    /**
     * 属性值
     */
    @ApiModelProperty(value = "属性值")
    private Object value;
    @ApiModelProperty(value = "属性值 List类型")
    private List<String> valueList;
    @ApiModelProperty(value = "类型 1销售属性 2显示参数")
    private Integer type;
    @ApiModelProperty(value = "输入类型")
    private Integer inputType;

    @ApiModelProperty(value = "sku属性值")
    private String skuValue;
    @ApiModelProperty(value = "sku属性名")
    private String skuName;
    @ApiModelProperty(value = "sku属性名ID")
    private Long skuNameId;
    public List<String> getValueList(){
        if (this.value instanceof String) {
            String valueString = (String) this.value;
            try {
                List<String> attrStringList = JSONObject.parseArray(valueString, String.class);
                return attrStringList;
            } catch (Exception e) {
            }
        }
        return Arrays.asList(this.value.toString());
    }
}
