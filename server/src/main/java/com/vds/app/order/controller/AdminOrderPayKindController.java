
package com.vds.app.order.controller;

import javax.inject.Inject;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vds.app.exception.Msg;
import com.vds.app.exception.MyException;
import com.vds.app.order.model.OrderPayKind;
import com.vds.app.order.service.OrderPayKindService;

@RestController
@RequestMapping("/v2/admin/order/orderpaykind")
public class AdminOrderPayKindController {

	@Inject
	private OrderPayKindService orderPayKindService;


	@RequestMapping("update")
	public Object update(OrderPayKind obj) throws MyException {

		return orderPayKindService.modify(obj);
	}

	@RequestMapping("delById")
	public Object delById(String id) throws MyException {

		OrderPayKind obj = orderPayKindService.findOne(id);
		if (null == obj) {
			return Msg.MsgError("id error");
		}
		return orderPayKindService.remove(obj);
	}

	@RequestMapping("showById")
	public Object showById(String id) throws MyException {

		return orderPayKindService.findByOne(id);
	}

	@RequestMapping("findByPage")
	public Object findByPage(Pageable pageable) {
		return orderPayKindService.findAll(pageable);
	}

	@RequestMapping("editByEntity")
	public Object editByEntity(OrderPayKind obj) throws MyException {
		return orderPayKindService.modify(obj);
	}

	@RequestMapping("addEntity")
	public Object addEntity(OrderPayKind obj) throws MyException {

		return orderPayKindService.add(obj);
	}

}