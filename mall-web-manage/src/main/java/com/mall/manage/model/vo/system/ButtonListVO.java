package com.mall.manage.model.vo.system;

import com.mall.manage.model.vo.product.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/7/24
 */
@Data
@ApiModel
public class ButtonListVO extends BaseVO {

    @ApiModelProperty(value = "按钮列表")
    private List<ButtonList> buttonList;

    @ApiModelProperty(value = "已授权的按钮列表")
    private List<ButtonListAuth> buttonListAuthority;
    @ApiModel
    @Data
    public static class ButtonList extends BaseVO{
        /**
         * 主键
         */
        @ApiModelProperty(value = "主键")
        private Long id;
        /**
         * 按钮名称
         */
        @ApiModelProperty(value = "按钮名称")
        private  String buttonName;
    }

    @Data
    @ApiModel
    public static class ButtonListAuth extends BaseVO{
        /**
         * 主键
         */
        @ApiModelProperty(value = "主键")
        private Long id;
        /**
         * 按钮名称
         */
        @ApiModelProperty(value = "按钮名称")
        private  String buttonName;
    }
}
