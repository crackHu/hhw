
package com.vds.app.user.controller;

import javax.inject.Inject;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vds.app.exception.Msg;
import com.vds.app.exception.MyException;
import com.vds.app.user.model.UserRole;
import com.vds.app.user.service.UserRoleService;

@RestController
@RequestMapping("/v2/admin/user/userrole")
public class AdminUserRoleController {

	@Inject
	private UserRoleService userRoleService;

	@RequestMapping("update")
	public Object update(UserRole obj) throws MyException {

		return userRoleService.modify(obj);
	}

	@RequestMapping("delById")
	public Object delById(String id) throws MyException {

		UserRole obj = userRoleService.findOne(id);
		if (null == obj) {
			return Msg.MsgError("id error");
		}
		return userRoleService.remove(obj);
	}

	@RequestMapping("showById")
	public Object showById(String id) throws MyException {

		return userRoleService.findByOne(id);
	}

	@RequestMapping("findByPage")
	public Object findByPage(Pageable pageable) {
		return userRoleService.findAll(pageable);
	}

	@RequestMapping("editByEntity")
	public Object editByEntity(UserRole obj) throws MyException {
		return userRoleService.modify(obj);
	}

	@RequestMapping("addEntity")
	public Object addEntity(UserRole obj) throws MyException {

		return userRoleService.add(obj);
	}

}