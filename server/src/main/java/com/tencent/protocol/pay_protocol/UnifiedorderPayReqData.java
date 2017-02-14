package com.tencent.protocol.pay_protocol;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.tencent.common.Configure;
import com.tencent.common.RandomStringGenerator;
import com.tencent.common.Signature;

public class UnifiedorderPayReqData {
	private String appid = "";//公众账号ID--是--微信分配的公众账号ID（企业号corpid即为此appId）
	private String mch_id = "";//商户号--是--微信支付分配的商户号
	private String device_info = "";//设备号--否--终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB"
	private String nonce_str = "";//随机字符串--是--随机字符串，不长于32位
	private String sign = "";//签名--是
	private String sign_type = "";//签名类型--否--签名类型，目前支持HMAC-SHA256和MD5，默认为MD5
	private String body = "";//商品描述--是--商品简单描述，该字段须严格按照规范传递
//	PC网站 扫码支付 浏览器打开的网站主页title名 -商品概述 腾讯充值中心-QQ会员充值  
//	微信浏览器 公众号支付 商家名称-销售商品类目 罗辑思维-图书 线上电商，商家名称必须为实际销售商品的商家 
//	门店扫码 公众号支付 店名-销售商品类目 天虹南山店-超市 线下门店支付 
//	门店扫码 扫码支付 店名-销售商品类目 天虹南山店-超市 线下门店支付 
//	门店刷卡 刷卡支付 店名-销售商品类目 天虹南山店-超市 线下门店支付 
//	第三方手机浏览器 H5支付 浏览器打开的移动网页的主页title名-商品概述 腾讯充值中心-QQ会员充值  
//	第三方APP APP支付 应用市场上的APP名字-商品概述 天天爱消除-游戏充值 
	private String detail = "";//商品详情--否--商品详细列表，使用Json格式，传输签名前请务必使用CDATA标签将JSON文本串保护起来。
//	goods_detail []：
//	goods_id String 必填 32 商品的编号
//	wxpay_goods_id String 可选 32 微信支付定义的统一商品编号
//	goods_name String 必填 256 商品名称
//	quantity Int 必填 商品数量
//	price Int 必填 商品单价，单位为分
//	goods_category String 可选 32 商品类目ID
//	body String 可选 1000 商品描述信息
	private String attach = "";//附加数据--否--附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
	private String out_trade_no = "";//商户订单号--是--商户系统内部的订单号,32个字符内、可包含字母
	private String fee_type = "";//货币类型--是--符合ISO 4217标准的三位字母代码，默认人民币：CNY
	private int total_fee = 0;//总金额 --是--订单总金额，单位为分
	private String spbill_create_ip = "";//终端IP--是--APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP
	private String time_start = "";//交易起始时间--否--订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010
	private String time_expire = "";//交易结束时间--否--订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010--注意：最短失效时间间隔必须大于5分钟
	private String goods_tag = "";//商品标记--商品标记，代金券或立减优惠功能的参数
	private String notify_url = "";//通知地址--是--接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数
	private String trade_type = "";//交易类型--取值如下：JSAPI，NATIVE，APP
//	JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付，统一下单接口trade_type的传参可参考这里
//	MICROPAY--刷卡支付，刷卡支付有单独的支付接口，不调用统一下单接口
	private String product_id = "";//商品ID--否--trade_type=NATIVE，此参数必传。此id为二维码中包含的商品ID，商户自行定义
	private String limit_pay = "";//指定支付方式--否--no_credit--指定不能使用信用卡支付
	private String openid = "";//用户标识--否--trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识
	
	
	
	
	/**
	 * 
	 * @param body 商品描述
	 * @param out_trade_no 商户订单号--商户系统内部的订单号,32个字符内、可包含字母
	 * @param total_fee 总金额--订单总金额，单位为分
	 * @param spbill_create_ip 终端IP--APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP
	 * @param time_start 交易起始时间--订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010
	 * @param time_expire 交易结束时间 --订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010--注意：最短失效时间间隔必须大于5分钟
	 * @param notify_url 通知地址--接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数
	 * @param trade_type 交易类型--取值如下：JSAPI，NATIVE，APP
	 */
	public UnifiedorderPayReqData(
			String body, String out_trade_no,
			int total_fee, String spbill_create_ip, String time_start, String time_expire,
			String notify_url, String trade_type) {
		this.appid = Configure.getAppid();
		this.mch_id = Configure.getMchid();
		this.nonce_str = RandomStringGenerator.getRandomStringByLength(32);
		
		this.body = body;
		this.out_trade_no = out_trade_no;
		this.total_fee = total_fee;
		this.spbill_create_ip = spbill_create_ip;
		this.time_start = time_start;
		this.time_expire = time_expire;
		this.notify_url = notify_url;
		this.trade_type = trade_type;
		this.sign = Signature.getSign(toMap());
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public String getDevice_info() {
		return device_info;
	}
	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getSign_type() {
		return sign_type;
	}
	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getFee_type() {
		return fee_type;
	}
	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}
	public int getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}
	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}
	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}
	public String getTime_start() {
		return time_start;
	}
	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}
	public String getTime_expire() {
		return time_expire;
	}
	public void setTime_expire(String time_expire) {
		this.time_expire = time_expire;
	}
	public String getGoods_tag() {
		return goods_tag;
	}
	public void setGoods_tag(String goods_tag) {
		this.goods_tag = goods_tag;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	public String getTrade_type() {
		return trade_type;
	}
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getLimit_pay() {
		return limit_pay;
	}
	public void setLimit_pay(String limit_pay) {
		this.limit_pay = limit_pay;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	public Map<String,Object> toMap(){
        Map<String,Object> map = new HashMap<String, Object>();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            Object obj;
            try {
                obj = field.get(this);
                if(obj!=null){
                    map.put(field.getName(), obj);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }
	
}
