package com.vds.app.order.controller;

import java.math.BigDecimal;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vds.app.exception.Msg;
import com.vds.app.exception.MyException;
import com.vds.app.order.service.OrderPaymentService;
import com.vds.app.order.service.OrderService;
import com.vds.app.user.controller.BaseController;

@RestController
@RequestMapping("v2/order")
public class OrderController extends BaseController {

	@Inject
	private OrderService orderSerivce;

	@Inject
	private OrderPaymentService orderPaymentService;

	@RequestMapping("getOrderListByToken")
	public Object getOrderListByToken(String parkSiteId, Integer status, Pageable pageable) throws MyException {
		return Msg.MsgSuccess(
				orderSerivce.findByUsIdAndOdParkSiteId(getUserByToken().getUsId(), parkSiteId, status, pageable));
	}

	@RequestMapping("getOrderByOrId")
	public Object getOrderByOrId(String id) throws MyException {

		return orderPaymentService.findByOrId(id);
	}

	@RequestMapping("setOrderStatusByCode")
	public Object setOrderStatusByCode(String code) throws MyException {

		return orderSerivce.updateOrderByCode(code);
	}

	@RequestMapping("placeOrder")
	public Object placeOrder(String orderId, String carId, String parkSiteId, String catalog, BigDecimal price,
			HttpServletRequest request) throws MyException {

		return orderSerivce.placeOrder(getUserByToken().getUsId(), orderId, parkSiteId, catalog, carId, price, request);
	}

	@RequestMapping("underwayOrder")
	public Object underwayOrder() throws MyException {

		return orderSerivce.underwayOrder(getUserByToken().getUsId());
	}

	@RequestMapping("hasOrder")
	public Object hasOrder() throws MyException {

		return orderSerivce.hasOrder(getUserByToken().getUsId());
	}
	
	

}
