
package com.vds.app.other.controller;

import javax.inject.Inject;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vds.app.exception.Msg;
import com.vds.app.exception.MyException;
import com.vds.app.other.model.OtherCar;
import com.vds.app.other.service.OtherCarService;

@RestController
@RequestMapping("/v2/admin/other/othercar")
public class AdminOtherCarController {

	@Inject
	private OtherCarService otherCarService;


	@RequestMapping("update")
	public Object update(OtherCar obj) throws MyException {

		return otherCarService.modify(obj);
	}

	@RequestMapping("delById")
	public Object delById(String id) throws MyException {

		OtherCar obj = otherCarService.findOne(id);
		if (null == obj) {
			return Msg.MsgError("id error");
		}
		return otherCarService.remove(obj);
	}

	@RequestMapping("showById")
	public Object showById(String id) throws MyException {

		return otherCarService.findByOne(id);
	}

	@RequestMapping("findByPage")
	public Object findByPage(Pageable pageable) {
		return otherCarService.findAll(pageable);
	}

	@RequestMapping("editByEntity")
	public Object editByEntity(OtherCar obj) throws MyException {
		return otherCarService.modify(obj);
	}

	@RequestMapping("addEntity")
	public Object addEntity(OtherCar obj) throws MyException {

		return otherCarService.add(obj);
	}

}