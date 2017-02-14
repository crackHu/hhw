
package com.vds.app.other.controller;

import javax.inject.Inject;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vds.app.exception.Msg;
import com.vds.app.exception.MyException;
import com.vds.app.other.model.OtherParkAdmin;
import com.vds.app.other.service.OtherParkAdminService;

@RestController
@RequestMapping("/v2/admin/other/otherparkadmin")
public class AdminOtherParkAdminController {

	@Inject
	private OtherParkAdminService otherParkAdminService;


	@RequestMapping("update")
	public Object update(OtherParkAdmin obj) throws MyException {

		return otherParkAdminService.modify(obj);
	}

	@RequestMapping("delById")
	public Object delById(String id) throws MyException {

		OtherParkAdmin obj = otherParkAdminService.findOne(id);
		if (null == obj) {
			return Msg.MsgError("id error");
		}
		return otherParkAdminService.remove(obj);
	}

	@RequestMapping("showById")
	public Object showById(String id) throws MyException {

		return otherParkAdminService.findByOne(id);
	}

	@RequestMapping("findByPage")
	public Object findByPage(Pageable pageable) {
		return otherParkAdminService.findAll(pageable);
	}

	@RequestMapping("editByEntity")
	public Object editByEntity(OtherParkAdmin obj) throws MyException {
		return otherParkAdminService.modify(obj);
	}

	@RequestMapping("addEntity")
	public Object addEntity(OtherParkAdmin obj) throws MyException {

		return otherParkAdminService.add(obj);
	}

}