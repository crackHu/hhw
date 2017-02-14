
package com.vds.app.user.controller;

import javax.inject.Inject;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vds.app.exception.Msg;
import com.vds.app.exception.MyException;
import com.vds.app.user.model.UserResources;
import com.vds.app.user.service.UserResourcesService;

@RestController
@RequestMapping("/v2/admin/user/userresources")
public class AdminUserResourcesController {

	@Inject
	private UserResourcesService userResourcesService;

	@RequestMapping("update")
	public Object update(UserResources obj) throws MyException {

		return userResourcesService.modify(obj);
	}

	@RequestMapping("delById")
	public Object delById(String id) throws MyException {

		UserResources obj = userResourcesService.findOne(id);
		if (null == obj) {
			return Msg.MsgError("id error");
		}
		return userResourcesService.remove(obj);
	}

	@RequestMapping("showById")
	public Object showById(String id) throws MyException {

		return userResourcesService.findByOne(id);
	}

	@RequestMapping("findByPage")
	public Object findByPage(Pageable pageable) {
		return userResourcesService.findAll(pageable);
	}

	@RequestMapping("editByEntity")
	public Object editByEntity(UserResources obj) throws MyException {
		return userResourcesService.modify(obj);
	}

	@RequestMapping("addEntity")
	public Object addEntity(UserResources obj) throws MyException {

		return userResourcesService.add(obj);
	}

}