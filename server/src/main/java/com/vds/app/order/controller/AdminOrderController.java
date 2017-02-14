
package com.vds.app.order.controller;

import javax.inject.Inject;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vds.app.exception.Msg;
import com.vds.app.exception.MyException;
import com.vds.app.order.model.Order;
import com.vds.app.order.service.OrderService;

@Controller
@RequestMapping("/v2/admin/order")
public class AdminOrderController {

	@Inject
	private OrderService orderService;

	private String url = "views/admin/order/order_";

	@RequestMapping("getView")
	public String getView() {

		return url + "view";
	}

	@RequestMapping("showAdd")
	public String showAdd() {

		return url + "add";
	}

	@RequestMapping("getTableView")
	public String getTableView(Pageable pageable, ModelMap map) throws MyException {

		map.put("pageInfo", orderService.findAll(pageable));
		return url + "table";
	}

	@ResponseBody
	@RequestMapping("getTablePage")
	public Object getTablePage(Pageable pageable, ModelMap map) throws MyException {

		return orderService.findAll(pageable);
	}

	@RequestMapping("showEdit")
	public String showEdit(String id, ModelMap map) throws MyException {

		map.put("entity", orderService.findByOne(id));
		return url + "edit";
	}

	@ResponseBody
	@RequestMapping("update")
	public Object update(Order obj) throws MyException {

		return orderService.modify(obj);
	}

	@ResponseBody
	@RequestMapping("delById")
	public Object delById(String id) throws MyException {

		Order obj = orderService.findOne(id);
		if (null == obj) {
			return Msg.MsgError("id error");
		}
		return orderService.remove(obj);
	}

	@ResponseBody
	@RequestMapping("showById")
	public Object showById(String id) throws MyException {

		return orderService.findByOne(id);
	}

	@ResponseBody
	@RequestMapping("findByPage")
	public Object findByPage(Pageable pageable) {
		return orderService.findAll(pageable);
	}

	@ResponseBody
	@RequestMapping("editByEntity")
	public Object editByEntity(Order obj) throws MyException {
		return orderService.modify(obj);
	}

	@ResponseBody
	@RequestMapping("addEntity")
	public Object addEntity(Order obj) throws MyException {

		return orderService.add(obj);
	}

}