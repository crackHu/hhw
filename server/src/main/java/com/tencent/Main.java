package com.tencent;

import com.tencent.common.Signature;
import com.tencent.common.Util;
import com.tencent.common.XMLParser;
import com.tencent.protocol.pay_protocol.UnifiedorderPayReqData;
import com.tencent.protocol.pay_protocol.UnifiedorderPayResData;
import com.vds.app.exception.Msg;
import com.vds.app.util.ChangeType;
import com.vds.app.util.IpUtil;
import com.vds.app.util.RandomNumUtil;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) throws Exception {

		// UnifiedorderPayReqData data = new UnifiedorderPayReqData("hhw-停车",
		// "x123132654654x21", 200, "42.96.176.248",
		// ChangeType.curDay(), ChangeType.TomorrowYearDay(),
		// "http://www.basoft.cn/hhw/v2/open/pay/callBack",
		// "JSAPI");
		// String requestMsg = WXPay.requestUnifiedorderPayService(data);
		//
		// UnifiedorderPayResData payResData = (UnifiedorderPayResData)
		// Util.getObjectFromXML(requestMsg,
		// UnifiedorderPayResData.class);
		// System.out.println(requestMsg);
		// if (payResData.getReturn_code().equals("SUCCESS") &&
		// payResData.getResult_code().equals("SUCCESS")) {
		// // long time = System.currentTimeMillis()/1000;
		// // payResData.setTimestamp(String.valueOf(time));
		// // String sign = Signature.getSignFromResponseString(ss);
		// // payResData.setSign(sign);
		// Map<String, Object> objData = XMLParser.getMapFromXML(requestMsg);
		// objData.put("package", "Sign=WXPay");
		// objData.put("timestamp", System.currentTimeMillis() / 1000);
		//
		// Map<String, Object> signParams = new HashMap<String, Object>();
		// signParams.put("appid", objData.get("appid"));
		// signParams.put("noncestr", objData.get("nonce_str"));
		// signParams.put("package", objData.get("package"));
		// signParams.put("partnerid", objData.get("mch_id"));
		// signParams.put("prepayid", objData.get("prepay_id"));
		// signParams.put("timestamp", objData.get("timestamp"));
		// String sign = Signature.getSign(signParams);
		// objData.put("sign", sign);
		//
		// }

		try {

			// --------------------------------------------------------------------
			// 温馨提示，第一次使用该SDK时请到com.tencent.common.Configure类里面进行配置
			// --------------------------------------------------------------------

			// --------------------------------------------------------------------
			// PART One:基础组件测试
			// --------------------------------------------------------------------

			// 1）https请求可用性测试
			// HTTPSPostRquestWithCert.test();

			// 2）测试项目用到的XStream组件，本项目利用这个组件将Java对象转换成XML数据Post给API
			// XStreamTest.test();

			// --------------------------------------------------------------------
			// PART Two:基础服务测试
			// --------------------------------------------------------------------

			// 1）测试被扫支付API
			// PayServiceTest.test();

			// 2）测试被扫订单查询API
			// PayQueryServiceTest.test();

			// 3）测试撤销API
			// 温馨提示，测试支付API成功扣到钱之后，可以通过调用PayQueryServiceTest.test()，将支付成功返回的transaction_id和out_trade_no数据贴进去，完成撤销工作，把钱退回来
			// ^_^v
			// ReverseServiceTest.test();

			// 4）测试退款申请API
			// RefundServiceTest.test();

			// 5）测试退款查询API
			// RefundQueryServiceTest.test();

			// 6）测试对账单API
			// DownloadBillServiceTest.test();

			// 本地通过xml进行API数据模拟的时候，先按需手动修改xml各个节点的值，然后通过以下方法对这个新的xml数据进行签名得到一串合法的签名，最后把这串签名放到这个xml里面的sign字段里，这样进行模拟的时候就可以通过签名验证了
			// Util.log(Signature.getSignFromResponseString(Util.getLocalXMLString("/test/com/tencent/business/refundqueryserviceresponsedata/refundquerysuccess2.xml")));

			// Util.log(new Date().getTime());
			// Util.log(System.currentTimeMillis());

		} catch (Exception e) {
			Util.log(e.getMessage());
		}

	}

}
