package com.vds.app.order.service;

import org.springframework.data.domain.Pageable;
import com.vds.app.base.BaseService;
import com.vds.app.exception.Msg;
import com.vds.app.exception.MyException;
import com.vds.app.order.jpa.OrderGiftKindJpa;

import com.vds.app.order.model.OrderGiftKind;

public interface OrderGiftKindService extends BaseService<OrderGiftKind>{

	public Msg findAll(Pageable pageable);
}
