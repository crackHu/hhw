
package com.vds.app.order.controller;

import javax.inject.Inject;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vds.app.exception.Msg;
import com.vds.app.exception.MyException;
import com.vds.app.order.model.OrderGift;
import com.vds.app.order.service.OrderGiftService;

@RestController
@RequestMapping("/v2/admin/order/ordergift")
public class AdminOrderGiftController {

	@Inject
	private OrderGiftService orderGiftService;


	@RequestMapping("update")
	public Object update(OrderGift obj) throws MyException {

		return orderGiftService.modify(obj);
	}

	@RequestMapping("delById")
	public Object delById(String id) throws MyException {

		OrderGift obj = orderGiftService.findOne(id);
		if (null == obj) {
			return Msg.MsgError("id error");
		}
		return orderGiftService.remove(obj);
	}

	@RequestMapping("showById")
	public Object showById(String id) throws MyException {

		return orderGiftService.findByOne(id);
	}

	@RequestMapping("findByPage")
	public Object findByPage(Pageable pageable) {
		return orderGiftService.findAll(pageable);
	}

	@RequestMapping("editByEntity")
	public Object editByEntity(OrderGift obj) throws MyException {
		return orderGiftService.modify(obj);
	}

	@RequestMapping("addEntity")
	public Object addEntity(OrderGift obj) throws MyException {

		return orderGiftService.add(obj);
	}

}