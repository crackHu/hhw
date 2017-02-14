package com.vds.app.order.service.impl;

import javax.inject.Inject;
import com.vds.app.exception.Msg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.vds.app.base.BaseServiceImpl;
import com.vds.app.order.model.OrderPayKind;

import com.vds.app.order.jpa.OrderPayKindJpa;

import com.vds.app.order.service.OrderPayKindService;

@Service
public class OrderPayKindServiceImpl extends BaseServiceImpl<OrderPayKind> implements OrderPayKindService{

	@Inject
	private OrderPayKindJpa orderPayKindJpa;

	public Msg findAll(Pageable pageable) {
		Page<OrderPayKind> list = orderPayKindJpa.findAll(pageable);
	return Msg.MsgSuccess(list);
	}
}
