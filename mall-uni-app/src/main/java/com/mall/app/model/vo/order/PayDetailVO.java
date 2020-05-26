package com.mall.app.model.vo.order;

import com.mall.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel
public class PayDetailVO extends BaseModel {

    @ApiModelProperty(value = "费用信息")
    private PriceVO priceMsg;

    @ApiModelProperty(value = "商品列表")
    private List<SkuMsgVO> skuList;

    @ApiModelProperty(value = "用户收货信息")
    private ReceiverVO receiverMsg;


    @Data
    @ApiModel
    public class SkuMsgVO{
        @ApiModelProperty(value = "商品名称")
        private Long productName;
        @ApiModelProperty(value = "SKU ID")
        private Long skuId;
        @ApiModelProperty(value = "SKU图片地址")
        private String picUrl;
        @ApiModelProperty(value = "SKU属性数据")
        private String attrJson;
        @ApiModelProperty(value = "售价")
        private BigDecimal salePrice;
    }

    @Data
    @ApiModel
    public class PriceVO{
        @ApiModelProperty(value = "商品价格")
        private BigDecimal productPrice;

        @ApiModelProperty(value = "实付金额")
        private BigDecimal paidPrice;

        @ApiModelProperty(value = "优惠金额")
        private BigDecimal couponPrice;

        @ApiModelProperty(value = "运费")
        private BigDecimal freightPrice;
    }

    @Data
    @ApiModel
    public class ReceiverVO{
        @ApiModelProperty(value = "收货人姓名")
        private String receiverName;

        @ApiModelProperty(value = "收货人电话号码")
        private String receiverPhone;

        @ApiModelProperty(value = "省、直辖市")
        private String receiverProvince;

        @ApiModelProperty(value = "市")
        private String receiverCity;

        @ApiModelProperty(value = "区")
        private String receiverRegion;

        @ApiModelProperty(value = "详细地址")
        private String receiverDetailAddress;
    }

}
