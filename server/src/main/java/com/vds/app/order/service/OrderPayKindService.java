package com.vds.app.order.service;

import org.springframework.data.domain.Pageable;
import com.vds.app.base.BaseService;
import com.vds.app.exception.Msg;
import com.vds.app.exception.MyException;
import com.vds.app.order.jpa.OrderPayKindJpa;

import com.vds.app.order.model.OrderPayKind;

public interface OrderPayKindService extends BaseService<OrderPayKind>{

	public Msg findAll(Pageable pageable);
}
