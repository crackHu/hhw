package com.vds.app.order.service.impl;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vds.app.base.BaseServiceImpl;
import com.vds.app.exception.Msg;
import com.vds.app.exception.MyException;
import com.vds.app.order.jpa.OrderJpa;
import com.vds.app.order.jpa.OrderPaymentJpa;
import com.vds.app.order.model.Order;
import com.vds.app.order.model.OrderPayment;
import com.vds.app.order.service.OrderPaymentService;

@Service
public class OrderPaymentServiceImpl extends BaseServiceImpl<OrderPayment> implements OrderPaymentService {

	@Inject
	private OrderPaymentJpa orderPaymentJpa;

	@Inject
	private OrderJpa orderJpa;

	public Msg findAll(Pageable pageable) {
		Page<OrderPayment> list = orderPaymentJpa.findAll(pageable);
		return Msg.MsgSuccess(list);
	}

	@Override
	public Msg findByOrId(String orId) throws MyException {
		// TODO Auto-generated method stub
		Order order = orderJpa.findOne(orId);
		if (null == order) {
			throw new MyException("0004","没有该订单");
		}
		order.setOrderPaymentList(orderPaymentJpa.findByOdPayOdId(orId));
		return Msg.MsgSuccess(order);
	}
}
