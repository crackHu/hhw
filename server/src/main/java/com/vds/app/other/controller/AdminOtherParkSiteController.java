
package com.vds.app.other.controller;

import javax.inject.Inject;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vds.app.exception.Msg;
import com.vds.app.exception.MyException;
import com.vds.app.other.model.OtherParkSite;
import com.vds.app.other.service.OtherParkSiteService;

@Controller
@RequestMapping("/v2/admin/other/otherparksite")
public class AdminOtherParkSiteController {
	
	@Inject
	private OtherParkSiteService otherParkSiteService;
	
	private String url = "views/admin/park/park_";
	
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

		map.put("pageInfo", otherParkSiteService.findAll(pageable));
		return url + "table";
	}
	
	@ResponseBody
	@RequestMapping("getTablePage")
	public Object getTablePage(Pageable pageable, ModelMap map) throws MyException {

		return otherParkSiteService.findAll(pageable);
	}

	@RequestMapping("showEdit")
	public String showEdit(String id, ModelMap map) throws MyException {

		map.put("entity", otherParkSiteService.findByOne(id));
		return url + "edit";
	}
	

	@ResponseBody
	@RequestMapping("update")
	public Object update(OtherParkSite obj) throws MyException {

		return otherParkSiteService.modify(obj);
	}

	@ResponseBody
	@RequestMapping("delById")
	public Object delById(String id) throws MyException {

		OtherParkSite obj = otherParkSiteService.findOne(id);
		if (null == obj) {
			return Msg.MsgError("id error");
		}
		return otherParkSiteService.remove(obj);
	}

	@ResponseBody
	@RequestMapping("showById")
	public Object showById(String id) throws MyException {

		return otherParkSiteService.findByOne(id);
	}

	// 设置释放车位数量2017-0110
	// 释放车辆需要小于车库车位总数量，大于已经租用的车位数量。
	@ResponseBody
	@RequestMapping("editByEntity")
	public Object editByEntity(OtherParkSite obj) throws MyException {
		return otherParkSiteService.editByEntity(obj);
	}

	// 设置 添加停车场2017-0110
	@ResponseBody
	@RequestMapping("addEntity")
	public Object addEntity(OtherParkSite obj) throws MyException {

		return otherParkSiteService.add(obj);
	}

}