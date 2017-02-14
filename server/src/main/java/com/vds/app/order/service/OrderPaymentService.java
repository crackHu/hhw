package com.vds.app.order.service;

import org.springframework.data.domain.Pageable;

import com.vds.app.base.BaseService;
import com.vds.app.exception.Msg;
import com.vds.app.exception.MyException;
import com.vds.app.order.model.OrderPayment;

public interface OrderPaymentService extends BaseService<OrderPayment> {

	public Msg findAll(Pageable pageable);

	public Msg findByOrId(String orId) throws MyException;
}
