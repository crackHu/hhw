
package com.vds.app.other.controller;

import javax.inject.Inject;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vds.app.exception.Msg;
import com.vds.app.exception.MyException;
import com.vds.app.other.model.OtherParkArea;
import com.vds.app.other.service.OtherParkAreaService;

@RestController
@RequestMapping("/v2/admin/other/otherparkarea")
public class AdminOtherParkAreaController {

	@Inject
	private OtherParkAreaService otherParkAreaService;


	@RequestMapping("update")
	public Object update(OtherParkArea obj) throws MyException {

		return otherParkAreaService.modify(obj);
	}

	@RequestMapping("delById")
	public Object delById(String id) throws MyException {

		OtherParkArea obj = otherParkAreaService.findOne(id);
		if (null == obj) {
			return Msg.MsgError("id error");
		}
		return otherParkAreaService.remove(obj);
	}
	//查询地址，地址为空时查询所有PId为空
	@RequestMapping("showByPId")
	public Object showByPId(String pId)throws MyException{
		return otherParkAreaService.showByPId(pId);
	}

	@RequestMapping("showById")
	public Object showById(String id) throws MyException {

		return otherParkAreaService.findByOne(id);
	}

	@RequestMapping("findByPage")
	public Object findByPage(Pageable pageable) {
		return otherParkAreaService.findAll(pageable);
	}

	@RequestMapping("editByEntity")
	public Object editByEntity(OtherParkArea obj) throws MyException {
		return otherParkAreaService.modify(obj);
	}

	@RequestMapping("addEntity")
	public Object addEntity(OtherParkArea obj) throws MyException {

		return otherParkAreaService.add(obj);
	}

}