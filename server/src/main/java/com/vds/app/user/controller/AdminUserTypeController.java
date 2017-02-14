
package com.vds.app.user.controller;

import javax.inject.Inject;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vds.app.exception.Msg;
import com.vds.app.exception.MyException;
import com.vds.app.user.model.UserType;
import com.vds.app.user.service.UserTypeService;

@RestController
@RequestMapping("/v2/admin/user/usertype")
public class AdminUserTypeController {

	@Inject
	private UserTypeService userTypeService;


	@RequestMapping("update")
	public Object update(UserType obj) throws MyException {

		return userTypeService.modify(obj);
	}

	@RequestMapping("delById")
	public Object delById(String id) throws MyException {

		UserType obj = userTypeService.findOne(id);
		if (null == obj) {
			return Msg.MsgError("id error");
		}
		return userTypeService.remove(obj);
	}

	@RequestMapping("showById")
	public Object showById(String id) throws MyException {

		return userTypeService.findByOne(id);
	}

	@RequestMapping("findByPage")
	public Object findByPage(Pageable pageable) {
		return userTypeService.findAll(pageable);
	}

	@RequestMapping("editByEntity")
	public Object editByEntity(UserType obj) throws MyException {
		return userTypeService.modify(obj);
	}

	@RequestMapping("addEntity")
	public Object addEntity(UserType obj) throws MyException {

		return userTypeService.add(obj);
	}

}