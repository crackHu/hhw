
package com.vds.app.order.controller;

import javax.inject.Inject;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vds.app.exception.Msg;
import com.vds.app.exception.MyException;
import com.vds.app.order.model.OrderGiftKind;
import com.vds.app.order.service.OrderGiftKindService;

@RestController
@RequestMapping("/v2/admin/order/ordergiftkind")
public class AdminOrderGiftKindController {

	@Inject
	private OrderGiftKindService orderGiftKindService;


	@RequestMapping("update")
	public Object update(OrderGiftKind obj) throws MyException {

		return orderGiftKindService.modify(obj);
	}

	@RequestMapping("delById")
	public Object delById(String id) throws MyException {

		OrderGiftKind obj = orderGiftKindService.findOne(id);
		if (null == obj) {
			return Msg.MsgError("id error");
		}
		return orderGiftKindService.remove(obj);
	}

	@RequestMapping("showById")
	public Object showById(String id) throws MyException {

		return orderGiftKindService.findByOne(id);
	}

	@RequestMapping("findByPage")
	public Object findByPage(Pageable pageable) {
		return orderGiftKindService.findAll(pageable);
	}

	@RequestMapping("editByEntity")
	public Object editByEntity(OrderGiftKind obj) throws MyException {
		return orderGiftKindService.modify(obj);
	}

	@RequestMapping("addEntity")
	public Object addEntity(OrderGiftKind obj) throws MyException {

		return orderGiftKindService.add(obj);
	}

}