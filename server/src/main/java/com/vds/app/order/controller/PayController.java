package com.vds.app.order.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vds.app.exception.MyException;
import com.vds.app.order.service.OrderService;

@RestController
@RequestMapping("v2/open/pay")
public class PayController {

	@Inject
	private OrderService orderService;

	/**
	 * 支付
	 * 
	 * @author Cay
	 * @data 2017年1月5日
	 * @return
	 */
	public Object pay(String oId) {

		return "json";
	}

	/**
	 * 服务端回调
	 * 
	 * @author Cay
	 * @data 2017年1月5日
	 * @return
	 * @throws MyException
	 */
	@RequestMapping("callBack/{status}/{orderId}/{price}/{sno}")
	public Object callBack(@PathVariable("status") String status, @PathVariable("orderId") String orderId,
			@PathVariable("price") String price,@PathVariable("sno")String sno) throws MyException {

		return orderService.wechatPayCallBack(status, orderId, price,sno);
	}

}
