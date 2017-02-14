
package com.vds.app.order.controller;

import javax.inject.Inject;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vds.app.exception.Msg;
import com.vds.app.exception.MyException;
import com.vds.app.order.model.OrderPayment;
import com.vds.app.order.service.OrderPaymentService;

@RestController
@RequestMapping("/v2/admin/order/orderpayment")
public class AdminOrderPaymentController {

	@Inject
	private OrderPaymentService orderPaymentService;


	@RequestMapping("update")
	public Object update(OrderPayment obj) throws MyException {

		return orderPaymentService.modify(obj);
	}

	@RequestMapping("delById")
	public Object delById(String id) throws MyException {

		OrderPayment obj = orderPaymentService.findOne(id);
		if (null == obj) {
			return Msg.MsgError("id error");
		}
		return orderPaymentService.remove(obj);
	}

	@RequestMapping("showById")
	public Object showById(String id) throws MyException {

		return orderPaymentService.findByOne(id);
	}

	@RequestMapping("findByPage")
	public Object findByPage(Pageable pageable) {
		return orderPaymentService.findAll(pageable);
	}

	@RequestMapping("editByEntity")
	public Object editByEntity(OrderPayment obj) throws MyException {
		return orderPaymentService.modify(obj);
	}

	@RequestMapping("addEntity")
	public Object addEntity(OrderPayment obj) throws MyException {

		return orderPaymentService.add(obj);
	}

}