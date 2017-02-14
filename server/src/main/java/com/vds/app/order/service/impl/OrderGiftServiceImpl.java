package com.vds.app.order.service.impl;

import javax.inject.Inject;
import com.vds.app.exception.Msg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.vds.app.base.BaseServiceImpl;
import com.vds.app.order.model.OrderGift;

import com.vds.app.order.jpa.OrderGiftJpa;

import com.vds.app.order.service.OrderGiftService;

@Service
public class OrderGiftServiceImpl extends BaseServiceImpl<OrderGift> implements OrderGiftService{

	@Inject
	private OrderGiftJpa orderGiftJpa;

	public Msg findAll(Pageable pageable) {
		Page<OrderGift> list = orderGiftJpa.findAll(pageable);
	return Msg.MsgSuccess(list);
	}
}
