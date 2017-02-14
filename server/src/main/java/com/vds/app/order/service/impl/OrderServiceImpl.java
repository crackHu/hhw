package com.vds.app.order.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tencent.common.Configure;
import com.vds.app.base.BaseServiceImpl;
import com.vds.app.exception.Msg;
import com.vds.app.exception.MyException;
import com.vds.app.interceptor.Timer;
import com.vds.app.order.jpa.OrderJpa;
import com.vds.app.order.jpa.OrderPaymentJpa;
import com.vds.app.order.model.Order;
import com.vds.app.order.model.OrderPayment;
import com.vds.app.order.service.OrderService;
import com.vds.app.order.util.OrderEnum;
import com.vds.app.other.jpa.OtherCarJpa;
import com.vds.app.other.jpa.OtherParkPriceRuleJpa;
import com.vds.app.other.jpa.OtherParkSiteJpa;
import com.vds.app.other.model.OtherCar;
import com.vds.app.other.model.OtherParkPriceRule;
import com.vds.app.other.model.OtherParkSite;
import com.vds.app.other.service.OtherParkSiteService;
import com.vds.app.user.jpa.UserJpa;
import com.vds.app.user.jpa.UserOtherJpa;
import com.vds.app.user.model.User;
import com.vds.app.user.model.UserOther;
import com.vds.app.util.CayUtil;
import com.vds.app.util.ChangeType;
import com.vds.app.util.RandomNumUtil;
import com.wechatpay.utils.GetWxOrderno;
import com.wechatpay.utils.RequestHandler;
import com.wechatpay.utils.Sha1Util;

@Transactional(rollbackOn = Exception.class)
@Service
public class OrderServiceImpl extends BaseServiceImpl<Order> implements OrderService {

	@Inject
	private OrderJpa orderJpa;

	@Inject
	private OrderPaymentJpa orderPaymentJpa;

	@Inject
	private OtherParkSiteJpa otherParkSiteJpa;

	@Inject
	private UserOtherJpa userOtherJpa;

	@Inject
	private OtherParkPriceRuleJpa OtherParkPriceRuleJpa;

	@Inject
	private OtherCarJpa otherCarJpa;

	@Inject
	private UserJpa userJpa;
	
	@Inject
	private OtherParkSiteService otherParkSiteService;

	public Msg findAll(Pageable pageable) {
		Page<Order> list = orderJpa.findAll(pageable);
		return Msg.MsgSuccess(list);
	}

	@Override
	public Page<Order> findByUsIdAndOdParkSiteId(String usId, String odParkSiteId, Integer status, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<Order> page;
		if (null == odParkSiteId || odParkSiteId.equals("")) {
			if (null == status) {
				page = orderJpa.findByOdUsId(usId, pageable);
			} else {
				page = orderJpa.findByOdUsIdAndOdStatus(usId, status, pageable);
			}

		} else {

			if (null == status) {
				page = orderJpa.findByOdUsIdAndOdParkSiteId(usId, odParkSiteId, pageable);
			} else {
				page = orderJpa.findByOdUsIdAndOdParkSiteIdAndOdStatus(usId, odParkSiteId, status, pageable);
			}

		}

		for (Order order : page.getContent()) {
			order.setParkSite(otherParkSiteJpa.findOne(order.getOdParkSiteId()));
		}

		return page;
	}

	@Override
	public Msg findByBarcode(String code) throws MyException {

		Order order = orderJpa.findByOdBarcode(code);
		if (null == order) {
			throw new MyException("0004", "没有该订单");
		}
		order.setOrderPaymentList(orderPaymentJpa.findByOdPayOdId(order.getOdId()));
		return Msg.MsgSuccess(order);
	}

	@Override
	public Msg updateOrderByCode(String code) throws MyException {
		Order order = orderJpa.findByOdBarcode(code);
		if (null == order) {
			throw new MyException("0014", "没有该订单");
		}
		if (order.getOdStatus() != OrderEnum.PROCEED.getStatus()) {
			throw new MyException("0015", "订单状态异常");
		}
		order.setOdStatus(OrderEnum.FINISH.getStatus());
		return Msg.MsgSuccess(orderJpa.save(order));
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public Msg placeOrder(String usId, String orderId, String parkSiteId, String catalog, String carId,
			BigDecimal price, HttpServletRequest request) throws MyException {
		OtherParkSite ops = otherParkSiteJpa.findOne(parkSiteId);
		List<Order> orderList = orderJpa.findByUnderOrder(usId);

		if (null == ops) {
			throw new MyException("0019", "停车场不存在");
		}
		if (ops.getOtFreeRes() <= 0) {
			throw new MyException("0020", "没有空余的车位");
		}

		OtherCar oc = otherCarJpa.findByOtCarIdAndOtCarUsId(carId, usId);
		if (null == oc) {
			throw new MyException("0018", "该车不存在");
		}
		UserOther uo = userOtherJpa.findByUoUsId(usId);
		if (null == orderId || orderId.equals("")) {
			if (orderList.size() > 0) {
				throw new MyException("0021", "已存在订单未完成,不能新建订单");
			}
			Order order = new Order(usId, parkSiteId, catalog,
					oc.getOtCarProvince() + oc.getOtCarCity() + " " + oc.getOtCarNum(), carId);

			OtherParkPriceRule opPruce = OtherParkPriceRuleJpa.findByOtSiteIdAndOtCatalogId(parkSiteId, catalog);
			if (null == opPruce) {
				throw new MyException("0016", "停车场 参数 异常");
			}

			orderJpa.save(order);
			price = opPruce.getOtPrice();
			// **************
			// wechat pay
			// **************

			Object data = payWechat(order.getOdId(), price, RandomNumUtil.getRandom20(), uo.getUoOpenId(), 1, request);
			// **************
			
			otherParkSiteService.updateParkSite(0, parkSiteId, 1);
			return Msg.MsgSuccess(data);

		} else {
			Order order = orderJpa.findOne(orderId);
			if (null == order || order.getOdStatus() > 1) {
				throw new MyException("0022", "订单不存在或存在异常");
			}

			Object data = payWechat(order.getOdId(), price, RandomNumUtil.getRandom20(), uo.getUoOpenId(),
					order.getOdStatus() + 1, request);
			otherParkSiteService.updateParkSite(0, parkSiteId, 1);
			Timer.status = 1;
			
			// **************
			return Msg.MsgSuccess(data);
		}
	}

	@SuppressWarnings({ "static-access" })
	private Object payWechat(String orderid, BigDecimal price, String out_trade_no, String openid, Integer status,
			HttpServletRequest request) throws MyException {

		String appid = Configure.getAppid();
		String appsecret = Configure.getSecret();
		String partner = Configure.getMchid();
		String partnerkey = Configure.getKey();

		BigDecimal sessionmoney1 = new BigDecimal(price.multiply(new BigDecimal(100)).intValue());
		String finalmoney = String.valueOf(sessionmoney1);

		String nonce_str = RandomNumUtil.getRandom20();

		String spbill_create_ip = request.getRemoteAddr();

		String body = "猴好位-微信支付";

		String notify_url = Configure.notify_url + status + "/" + orderid + "/" + price + "/" + out_trade_no;
		
		System.out.println("notify_url:"+notify_url);
		String startTime = ChangeType.curDay();
		String expireTime = ChangeType.afterCurDay();
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", appid);
		packageParams.put("mch_id", partner);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("body", body);
		packageParams.put("out_trade_no", out_trade_no);
		packageParams.put("time_start", startTime);
		packageParams.put("time_expire", expireTime);
		packageParams.put("total_fee", finalmoney);
		packageParams.put("spbill_create_ip", spbill_create_ip);
		packageParams.put("notify_url", notify_url);

		packageParams.put("trade_type", "JSAPI");
		packageParams.put("openid", openid);

		RequestHandler reqHandler = new RequestHandler(null, null);
		reqHandler.init(appid, appsecret, partnerkey);

		String sign = reqHandler.createSign(packageParams);
		String xml = "<xml>" + "<appid>" + appid + "</appid>" + "<mch_id>" + partner + "</mch_id>" + "<nonce_str>"
				+ nonce_str + "</nonce_str>" + "<sign>" + sign + "</sign>" + "<body><![CDATA[" + body + "]]></body>"
				+ "<out_trade_no>" + out_trade_no + "</out_trade_no>" + "<total_fee>" + finalmoney + "</total_fee>"
				+ "<spbill_create_ip>" + spbill_create_ip + "</spbill_create_ip>" + "<notify_url>" + notify_url
				+ "</notify_url>" + "<trade_type>" + "JSAPI" + "</trade_type>" 
				+  "<time_start>" + startTime + "</time_start>"
				+  "<time_expire>" + expireTime + "</time_expire>"
				+"<openid>" + openid + "</openid>"
				+ "</xml>";
		System.out.println(xml);

		try {
			reqHandler.genPackage(packageParams);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		String prepay_id = "";
		try {
			prepay_id = new GetWxOrderno().getPayNo(createOrderURL, xml);
			if (prepay_id.equals("")) {
				throw new MyException("0017", "微信支付-下单异常");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new MyException("0017", "微信支付-下单异常");
		}
		SortedMap<String, String> finalpackage = new TreeMap<String, String>();
		String timestamp = Sha1Util.getTimeStamp();
		String prepay_id2 = "prepay_id=" + prepay_id;
		String packages = prepay_id2;
		finalpackage.put("appId", appid);
		finalpackage.put("timeStamp", timestamp);
		finalpackage.put("nonceStr", nonce_str);
		finalpackage.put("package", packages);
		finalpackage.put("signType", "MD5");
		String finalsign = reqHandler.createSign(finalpackage);
		finalpackage.put("paySign", finalsign);
		
		return finalpackage;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public String wechatPayCallBack(String status, String orderId, String price, String sno) throws MyException {

		System.out.println("---- wechat  callback ------");
		System.out.println("---- wechat  callback ------");
		System.out.println("---- wechat  callback ------");
		System.out.println("---- wechat  callback ------");
		System.out.println("---- wechat  callback ------");

		Order order = orderJpa.findOne(orderId);
		if (null == order) {
			System.out.println("order 信息 异常......");
			return "fail";
		}

		if (order.getOdStatus() == 2) {
			return "success";
		}

		User user = userJpa.findOne(order.getOdUsId());

		if (status.equals("1")) {

			OrderPayment op = new OrderPayment("微信支付", order.getOdUsId(), user.getUsName(), orderId, price);
			op.setOdPayTime(new Date());
			op.setOdPaySno(order.getOdBarcode());
			op.setOdPayStatus(1);
			op.setOdPayMemo(sno);
			orderPaymentJpa.save(op);

			order.setOdStartTime(new Date());
			// update by huyg +15min
			//order.setOdEndTime(CayUtil.getTimeByAfter(new Date(), Calendar.HOUR, 1));
			order.setOdEndTime(CayUtil.getTimeByAfter(new Date(), Calendar.MINUTE, 60 + 15));
			
			order.setOdFirstPrice(new BigDecimal(price));
			order.setOdAddPrice(new BigDecimal(price));
			order.setOdStatus(1);
			orderJpa.save(order);

		} else {
			OrderPayment op = new OrderPayment("微信支付", order.getOdUsId(), user.getUsName(), orderId, price);

			OtherParkSite ps = otherParkSiteJpa.findOne(order.getOdParkSiteId());
			BigDecimal psPrice = ps.getOtPrice();

			op.setOdPayTime(new Date());
			op.setOdPaySno(order.getOdBarcode());
			op.setOdPayStatus(2);
			op.setOdPayMemo(sno);
			orderPaymentJpa.save(op);

			BigDecimal hours = new BigDecimal(price).divide(psPrice, 1, BigDecimal.ROUND_HALF_UP);
			int munute = hours.multiply(new BigDecimal(60)).intValue();
			// add by huyg +15min
			munute += 15;
			System.out.println("price :" + price + "psPrice :" + psPrice + " munute:" + munute + " hours:" + hours);
			order.setOdEndTime(CayUtil.getTimeByAfter(order.getOdEndTime(), Calendar.MINUTE, munute));

			order.setOdAddPrice(order.getOdAddPrice().add(new BigDecimal(price)));
			order.setOdStatus(2);
			orderJpa.save(order);

		}
		return "success";
	}

	@Override
	public Msg underwayOrder(String uId) throws MyException {

		List<Order> orderList = orderJpa.findByUnderOrder(uId);
		if (orderList.size() == 0) {
			return Msg.MsgError("未有订单");
		}

		Order order = orderList.get(0);
		OtherParkSite ops = otherParkSiteJpa.findOne(order.getOdParkSiteId());
		order.setParkSite(ops);
		return Msg.MsgSuccess(order);
	}
	
	@Override
	public Msg hasOrder(String uId) throws MyException {
		 
		List<Order> orderList = orderJpa.findByUnderOrder(uId);
		if (orderList.size() == 0) {
			return Msg.MsgSuccess(false);
		} else {
			Order order = orderList.get(0);
			OtherParkSite ops = otherParkSiteJpa.findOne(order.getOdParkSiteId());
			order.setParkSite(ops);
			return Msg.MsgSuccess(order);
		}
	}

}
