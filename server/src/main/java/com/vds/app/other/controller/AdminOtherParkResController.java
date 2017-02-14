
package com.vds.app.other.controller;

import javax.inject.Inject;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vds.app.exception.Msg;
import com.vds.app.exception.MyException;
import com.vds.app.other.model.OtherParkRes;
import com.vds.app.other.service.OtherParkResService;

@RestController
@RequestMapping("/v2/admin/other/otherparkres")
public class AdminOtherParkResController {

	@Inject
	private OtherParkResService otherParkResService;

	//设置停车场车位资源数量(停车场管理员用)
	//如果是同一个停车场中的同一种类型，修改数量。否则添加数量
	@RequestMapping("updateBySiteIdAndType")
	public Object updateBySiteIdAndType(OtherParkRes obj) throws MyException {

		return otherParkResService.updateBySiteIdAndType(obj);
	}

	@RequestMapping("delById")
	public Object delById(String id) throws MyException {

		OtherParkRes obj = otherParkResService.findOne(id);
		if (null == obj) {
			return Msg.MsgError("id error");
		}
		return otherParkResService.remove(obj);
	}

	@RequestMapping("showById")
	public Object showById(String id) throws MyException {

		return otherParkResService.findByOne(id);
	}

	@RequestMapping("findByPage")
	public Object findByPage(Pageable pageable) {
		return otherParkResService.findAll(pageable);
	}

	@RequestMapping("editByEntity")
	public Object editByEntity(OtherParkRes obj) throws MyException {
		return otherParkResService.modify(obj);
	}
  
	@RequestMapping("addEntity")
	public Object addEntity(OtherParkRes obj) throws MyException {

		return otherParkResService.add(obj);
	}

}