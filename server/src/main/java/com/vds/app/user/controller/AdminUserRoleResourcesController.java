
package com.vds.app.user.controller;

import javax.inject.Inject;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vds.app.exception.Msg;
import com.vds.app.exception.MyException;
import com.vds.app.user.model.UserRoleResources;
import com.vds.app.user.service.UserRoleResourcesService;

@RestController
@RequestMapping("/v2/admin/user/userroleresources")
public class AdminUserRoleResourcesController {

	@Inject
	private UserRoleResourcesService userRoleResourcesService;


	@RequestMapping("update")
	public Object update(UserRoleResources obj) throws MyException {

		return userRoleResourcesService.modify(obj);
	}

	@RequestMapping("delById")
	public Object delById(String id) throws MyException {

		UserRoleResources obj = userRoleResourcesService.findOne(id);
		if (null == obj) {
			return Msg.MsgError("id error");
		}
		return userRoleResourcesService.remove(obj);
	}

	@RequestMapping("showById")
	public Object showById(String id) throws MyException {

		return userRoleResourcesService.findByOne(id);
	}

	@RequestMapping("findByPage")
	public Object findByPage(Pageable pageable) {
		return userRoleResourcesService.findAll(pageable);
	}

	@RequestMapping("editByEntity")
	public Object editByEntity(UserRoleResources obj) throws MyException {
		return userRoleResourcesService.modify(obj);
	}

	@RequestMapping("addEntity")
	public Object addUserEntity(UserRoleResources obj) throws MyException, com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException {

		return userRoleResourcesService.add(obj);
	}

}