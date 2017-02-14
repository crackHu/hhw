package com.wechatpay.component;


import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.tencent.common.Configure;
import com.wechatpay.utils.GetWxOrderno;
import com.wechatpay.utils.RequestHandler;
import com.wechatpay.utils.Sha1Util;
import com.wechatpay.utils.TenpayUtil;
import com.wechatpay.utils.UtilWtdex;

public class Topay extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public static String dopay(String code,String order_sn, String money)
			throws ServletException, IOException {
		 //网页授权后获取传递的参数 String userId = request.getParameter("userId"); String
	
		String appid = Configure.getAppid();
		String appsecret = Configure.getSecret();
		String partner = Configure.getMchid();
		String partnerkey = Configure.getKey();
/*		String appid = "wx96ac0cbd42f08c3e";
		String appsecret = "dabcd1ef633aa2d6441e8a2b3030b383";
		String partner = "1238636002";
		String partnerkey = "bacmp123wxpay0000000000000000000";*/
		String openId = "";
		
		
		
		//post请求
		UtilWtdex utilWtdex = new UtilWtdex();
		
		
		/*ApplicationContext wac = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		
		BoBase boNameMgr = new BoBase();
		JSONObject jDataOut = new JSONObject();
		
		JSONObject json = new JSONObject();
		json.put("code", code);
		JSONObject json1 = new JSONObject();
		json1.put("din", json);
		boNameMgr = (BoBase) wac.getBean("boVdsweChatOpenId");
		jDataOut = boNameMgr.callMethod("gainOpenId",json1);
		JSONObject din = (JSONObject) jDataOut.get("data");*/
		
	
/*		//设置cookie
		javax.servlet.http.Cookie cookie = new javax.servlet.http.Cookie("openid",openId);
		cookie.setMaxAge(3600);
		//设置路径，这个路径即该工程下都可以访问该cookie 如果不设置路径，那么只有设置该cookie路径及其子路径可以访问			 
		cookie.setPath("/");		
		response.addCookie(cookie);*/
		
		System.out.println("openID========================"+openId);
		// 金额转化为分为单位
		//float sessionmoney = Float.parseFloat(money);
		//System.out.println("========================为转化前的金额为=================== "+sessionmoney);
		//float sessionmoney1=sessionmoney*100;
		BigDecimal sessionmoney1=new BigDecimal(money).multiply(new BigDecimal(100));
		System.out.println("====================转化后的sessionmoney1==================="+sessionmoney1);
		String finalmoney1=String.valueOf(sessionmoney1);
		System.out.println("====================转化后的finalmoney1==================="+finalmoney1);
		//finalmoney1 = finalmoney1.replace(".", "");
		finalmoney1 = finalmoney1.substring(0, finalmoney1.indexOf("."));
		System.out.println("====================转化后的finalmoney1===================" + finalmoney1);
		//String finalmoney = String.format("%.2f", sessionmoney);
		//finalmoney = finalmoney.replace(".", "");
		//System.out.println("finalmoney" + finalmoney);

		// 获取openId后调用统一支付接口https://api.mch.weixin.qq.com/pay/unifiedorder
		String currTime = TenpayUtil.getCurrTime();
		// 8位日期
		String strTime = currTime.substring(8, currTime.length());
		// 四位随机数
		String strRandom = TenpayUtil.buildRandom(4) + "";
		// 10位序列号,可以自行调整。
		String strReq = strTime + strRandom;

		// 商户号
		String mch_id = partner;
		// 子商户号 非必输
		// String sub_mch_id="";
		// 设备号 非必输
		String device_info = "";
		// 随机数
		String nonce_str = strReq;
		// 商品描述
		// String body = describe;

		// 商品描述根据情况修改
		String body = "化妆品";
		// 附加数据
		// String attach = userId;
		// 商户订单号
		String out_trade_no = order_sn;
		int intMoney = Integer.parseInt("10");

		// 总金额以分为单位，不带小数点
		int total_fee = intMoney;
		// 订单生成的机器 IP
		String spbill_create_ip = "172.16.1.117";
		// 订 单 生 成 时 间 非必输
		// String time_start ="";
		// 订单失效时间 非必输
		// String time_expire = "";
		// 商品标记 非必输
		// String goods_tag = "";

		// 这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
		String notify_url = "";		
		//String notify_url = "http://www.basoft.cn:8180/vdsBoer/shop/weChatNotifyUrl.jsp";
		String trade_type = "JSAPI";
		String openid = openId;
		// 非必输
		// String product_id = "";
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", appid);
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("body", body);
		// packageParams.put("attach", attach);
		packageParams.put("out_trade_no", out_trade_no);

		// 这里写的金额为1 分到时修改
		packageParams.put("total_fee", finalmoney1);
		//packageParams.put("total_fee", "finalmoney");
		packageParams.put("spbill_create_ip", spbill_create_ip);
		packageParams.put("notify_url", notify_url);

		packageParams.put("trade_type", trade_type);
		packageParams.put("openid", openid);

		RequestHandler reqHandler = new RequestHandler(null, null);
		reqHandler.init(appid, appsecret, partnerkey);

		String sign = reqHandler.createSign(packageParams);
		String xml = "<xml>" + "<appid>" + appid + "</appid>" + "<mch_id>"
				+ mch_id + "</mch_id>" + "<nonce_str>" + nonce_str
				+ "</nonce_str>" + "<sign>" + sign + "</sign>"
				+ "<body><![CDATA[" + body + "]]></body>"
				+ "<out_trade_no>"
				+ out_trade_no
				+ "</out_trade_no>"
				+
				// 金额，这里写的1 分到时修改
				"<total_fee>"
				+ finalmoney1
				+ "</total_fee>"
				+
				// "<total_fee>"+finalmoney+"</total_fee>"+
				"<spbill_create_ip>" + spbill_create_ip + "</spbill_create_ip>"
				+ "<notify_url>" + notify_url + "</notify_url>"
				+ "<trade_type>" + trade_type + "</trade_type>" + "<openid>"
				+ openid + "</openid>" + "</xml>";
		System.out.println(xml);
		String allParameters = "";
		try {
			allParameters = reqHandler.genPackage(packageParams);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		Map<String, Object> dataMap2 = new HashMap<String, Object>();
		String prepay_id = "";
		try {
			prepay_id = new GetWxOrderno().getPayNo(createOrderURL, xml);
			if (prepay_id.equals("")) {
				System.out.println("统一支付接口获取预支付订单出错");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		SortedMap<String, String> finalpackage = new TreeMap<String, String>();
		String appid2 = appid;
		String timestamp = Sha1Util.getTimeStamp();
		String nonceStr2 = nonce_str;
		String prepay_id2 = "prepay_id=" + prepay_id;
		String packages = prepay_id2;
		finalpackage.put("appId", appid2);
		finalpackage.put("timeStamp", timestamp);
		finalpackage.put("nonceStr", nonceStr2);
		finalpackage.put("package", packages);
		finalpackage.put("signType", "MD5");
		String finalsign = reqHandler.createSign(finalpackage);
		return "\"appId\":\"" + appid2 + "\",\"timeStamp\":\"" + timestamp
				+ "\",\"nonceStr\":\"" + nonceStr2 + "\",\"package\":\""
				+ packages + "\",\"signType\" : \"MD5" + "\",\"paySign\":\""
				+ finalsign + "\"";
	}
	
}
