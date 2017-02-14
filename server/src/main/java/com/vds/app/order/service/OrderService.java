package com.vds.app.order.service;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vds.app.base.BaseService;
import com.vds.app.exception.Msg;
import com.vds.app.exception.MyException;
import com.vds.app.order.model.Order;

public interface OrderService extends BaseService<Order> {

	Msg findAll(Pageable pageable);

	Page<Order> findByUsIdAndOdParkSiteId(String usId, String odParkSiteId, Integer status, Pageable pageables);

	Msg findByBarcode(String code) throws MyException;

	Msg updateOrderByCode(String code) throws MyException;

	Msg placeOrder(String usId, String orderId, String parkSiteId, String catalog, String carId,
			BigDecimal price, HttpServletRequest request) throws MyException;

	String wechatPayCallBack(String status, String orderId, String price,String sno) throws MyException;

	Msg underwayOrder(String uId) throws MyException;

	Msg hasOrder(String uId) throws MyException;;
	
	
}
