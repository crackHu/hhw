
package com.vds.app.user.controller;

import javax.inject.Inject;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vds.app.exception.Msg;
import com.vds.app.exception.MyException;
import com.vds.app.user.model.UserRs;
import com.vds.app.user.service.UserRsService;

@RestController
@RequestMapping("/v2/admin/user/userrs")
public class AdminUserRsController {

	@Inject
	private UserRsService userRsService;

	@RequestMapping("update")
	public Object update(UserRs obj) throws MyException {

		return userRsService.modify(obj);
	}

	@RequestMapping("delById")
	public Object delById(String id) throws MyException {

		UserRs obj = userRsService.findOne(id);
		if (null == obj) {
			return Msg.MsgError("id error");
		}
		return userRsService.remove(obj);
	}

	@RequestMapping("showById")
	public Object showById(String id) throws MyException {

		return userRsService.findByOne(id);
	}

	@RequestMapping("findByPage")
	public Object findByPage(Pageable pageable) {
		return userRsService.findAll(pageable);
	}

	@RequestMapping("editByEntity")
	public Object editByEntity(UserRs obj) throws MyException {
		return userRsService.modify(obj);
	}

	@RequestMapping("addEntity")
	public Object addEntity(UserRs obj) throws MyException, com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException {

		return userRsService.add(obj);
	}

}