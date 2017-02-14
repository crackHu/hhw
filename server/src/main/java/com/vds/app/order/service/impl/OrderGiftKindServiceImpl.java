package com.vds.app.order.service.impl;

import javax.inject.Inject;
import com.vds.app.exception.Msg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.vds.app.base.BaseServiceImpl;
import com.vds.app.order.model.OrderGiftKind;

import com.vds.app.order.jpa.OrderGiftKindJpa;

import com.vds.app.order.service.OrderGiftKindService;

@Service
public class OrderGiftKindServiceImpl extends BaseServiceImpl<OrderGiftKind> implements OrderGiftKindService{

	@Inject
	private OrderGiftKindJpa orderGiftKindJpa;

	public Msg findAll(Pageable pageable) {
		Page<OrderGiftKind> list = orderGiftKindJpa.findAll(pageable);
	return Msg.MsgSuccess(list);
	}
}
