package com.mall.app.model.param.order;

import com.alibaba.fastjson.annotation.JSONField;
import com.mall.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/7/30
 */
@Data
@ApiModel
public class WXOrderPayNoticeParam extends BaseModel {

    /**
     * 小程序ID	appid	是	String(32)	wx8888888888888888	微信分配的小程序ID
     * 商户号	mch_id	是	String(32)	1900000109	微信支付分配的商户号
     * 设备号	device_info	否	String(32)	013467007045764	微信支付分配的终端设备号，
     * 随机字符串	nonce_str	是	String(32)	5K8264ILTKCH16CQ2502SI8ZNMTM67VS	随机字符串，不长于32位
     * 签名	sign	是	String(32)	C380BEC2BFD727A4B6845133519F3AD6	签名，详见签名算法
     * 签名类型	sign_type	否	String(32)	HMAC-SHA256	签名类型，目前支持HMAC-SHA256和MD5，默认为MD5
     * 业务结果	result_code	是	String(16)	SUCCESS	SUCCESS/FAIL
     * 错误代码	err_code	否	String(32)	SYSTEMERROR	错误返回的信息描述
     * 错误代码描述	err_code_des	否	String(128)	系统错误	错误返回的信息描述
     * 用户标识	openid	是	String(128)	wxd930ea5d5a258f4f	用户在商户appid下的唯一标识
     * 是否关注公众账号	is_subscribe	是	String(1)	Y	用户是否关注公众账号，Y-关注，N-未关注
     * 交易类型	trade_type	是	String(16)	JSAPI	JSAPI、NATIVE、APP
     * 付款银行	bank_type	是	String(32)	CMC	银行类型，采用字符串类型的银行标识，银行类型见银行列表
     * 订单金额	total_fee	是	Int	100	订单总金额，单位为分
     * 应结订单金额	settlement_total_fee	否	Int	100	应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。
     * 货币种类	fee_type	否	String(8)	CNY	货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
     * 现金支付金额	cash_fee	是	Int	100	现金支付金额订单现金支付金额，详见支付金额
     * 现金支付货币类型	cash_fee_type	否	String(16)	CNY	货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
     * 总代金券金额	coupon_fee	否	Int	10	代金券金额<=订单金额，订单金额-代金券金额=现金支付金额，详见支付金额
     * 代金券使用数量	coupon_count	否	Int	1	代金券使用数量
     * 代金券类型	coupon_type_$n	否	String	CASH
     * CASH--充值代金券
     * NO_CASH---非充值代金券
     *
     * 并且订单使用了免充值券后有返回（取值：CASH、NO_CASH）。$n为下标,从0开始编号，举例：coupon_type_0
     *
     * 注意：只有下单时订单使用了优惠，回调通知才会返回券信息。
     * 下列情况可能导致订单不可以享受优惠：可能情况。
     * 代金券ID	coupon_id_$n	否	String(20)	10000	代金券ID,$n为下标，从0开始编号
     * 注意：只有下单时订单使用了优惠，回调通知才会返回券信息。
     * 下列情况可能导致订单不可以享受优惠：可能情况。
     * 单个代金券支付金额	coupon_fee_$n	否	Int	100	单个代金券支付金额,$n为下标，从0开始编号
     * 微信支付订单号	transaction_id	是	String(32)	1217752501201407033233368018	微信支付订单号
     * 商户订单号	out_trade_no	是	String(32)	1212321211201407033568112322	商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
     * 商家数据包	attach	否	String(128)	123456	商家数据包，原样返回
     * 支付完成时间	time_end	是	String(14)	20141030133525	支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
     */
    @JSONField(name = "total_fee")
    @ApiModelProperty(value = "订单金额 单位分")
    private BigDecimal totalFee;

    @JSONField(name = "time_end")
    @ApiModelProperty(value = "支付完成时间 格式为yyyyMMddHHmmss")
    private String timeEnd;

    @JSONField(name = "out_trade_no")
    @ApiModelProperty(value = "商户订单号 商户系统内部订单号")
    private Long outTradeNo;

    /**
     * 第三方交易号
     */
    @JSONField(name = "transaction_id")
    @ApiModelProperty(value = "微信支付订单号")
    private String transactionId;


}
