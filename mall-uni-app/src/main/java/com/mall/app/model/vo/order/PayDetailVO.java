package com.mall.app.model.vo.order;

import com.mall.app.model.BasePriceModel;
import com.mall.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel
public class PayDetailVO extends BaseModel {

    @ApiModelProperty(value = "费用信息")
    private PriceVO priceMsg;

    @ApiModelProperty(value = "商品列表")
    private List<SkuVO> skuList;

    @ApiModelProperty(value = "用户收货信息")
    private ReceiverVO receiverMsg;


    @Data
    @ApiModel
    public  class PriceVO extends BasePriceModel {

    }

    @Data
    @ApiModel
    public  class ReceiverVO extends BaseModel{

        @ApiModelProperty(value = "收货信息ID")
        private Long addressId;
        @ApiModelProperty(value = "收货人姓名")
        private String name;

        @ApiModelProperty(value = "收货人电话号码")
        private String mobile;

        @ApiModelProperty(value = "省、直辖市")
        private String province;

        @ApiModelProperty(value = "市")
        private String city;

        @ApiModelProperty(value = "区")
        private String region;

        @ApiModelProperty(value = "详细地址")
        private String address;

        @ApiModelProperty(value = "是否默认")
        private Boolean defaulted;
    }

}
