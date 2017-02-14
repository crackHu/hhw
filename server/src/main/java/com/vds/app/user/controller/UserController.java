package com.vds.app.user.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vds.app.exception.MyException;
import com.vds.app.user.service.UserService;

@RestController
@RequestMapping("v2/user")
public class UserController extends BaseController {

	@Inject
	private UserService userService;

	@RequestMapping("getUserInfoByToken")
	public Object getUserInfoByToken() throws MyException {

		return userService.findUserAndCarByUId(getUserByToken());
	}
	
	

}
