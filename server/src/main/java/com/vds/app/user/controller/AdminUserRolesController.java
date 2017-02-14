
package com.vds.app.user.controller;

import javax.inject.Inject;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vds.app.exception.Msg;
import com.vds.app.exception.MyException;
import com.vds.app.user.model.UserRoles;
import com.vds.app.user.service.UserRolesService;

@RestController
@RequestMapping("/v2/admin/user/userroles")
public class AdminUserRolesController {

	@Inject
	private UserRolesService userRolesService;


	@RequestMapping("update")
	public Object update(UserRoles obj) throws MyException {

		return userRolesService.modify(obj);
	}

	@RequestMapping("delById")
	public Object delById(String id) throws MyException {

		UserRoles obj = userRolesService.findOne(id);
		if (null == obj) {
			return Msg.MsgError("id error");
		}
		return userRolesService.remove(obj);
	}

	@RequestMapping("showById")
	public Object showById(String id) throws MyException {

		return userRolesService.findByOne(id);
	}

	@RequestMapping("findByPage")
	public Object findByPage(Pageable pageable) {
		return userRolesService.findAll(pageable);
	}

	@RequestMapping("editByEntity")
	public Object editByEntity(UserRoles obj) throws MyException {
		return userRolesService.modify(obj);
	}

	@RequestMapping("addEntity")
	public Object addEntity(UserRoles obj) throws MyException, com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException {

		return userRolesService.add(obj);
	}

}