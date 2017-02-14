package com.vds.app.user.controller;

import javax.inject.Inject;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vds.app.exception.Msg;
import com.vds.app.exception.MyException;
import com.vds.app.user.jpa.UserJpa;
import com.vds.app.user.model.User;
import com.vds.app.user.service.UserService;

@Controller
@RequestMapping("v2/admin/user")
public class AdminUserController {

	@Inject
	private UserService userService;

	@Inject
	private UserJpa userJpa;
	
	private String url = "views/admin/user/wechat/user_";

	@RequestMapping("getView")
	public String getView() {

		return url + "view";
	}

	@RequestMapping("showAdd")
	public String showAdd() {

		return url + "add";
	}


	@RequestMapping("getTableView")
	public String getTableView(Pageable pageable, ModelMap map) throws MyException {

		map.put("pageInfo", userService.findAll(pageable));
		return url + "table";
	}
	
	@ResponseBody
	@RequestMapping("getTablePage")
	public Object getTablePage(Pageable pageable, ModelMap map) throws MyException {

		return userService.findAll(pageable);
	}

	@RequestMapping("showEdit")
	public String showEdit(String id, ModelMap map) throws MyException {

		map.put("entity", userService.findByOne(id));
		return url + "edit";
	}
	
	@ResponseBody
	@RequestMapping("findByPage")
	public Msg findByPage(Pageable pageable) throws MyException{
		
		return Msg.MsgSuccess();
	}
	@ResponseBody
	@RequestMapping("showById")
	public Msg showById(String id) {

		return Msg.MsgSuccess(userJpa.findOne(id));
	}

	@ResponseBody
	@RequestMapping("editById")
	public Msg editById(User user) throws MyException {

		return Msg.MsgSuccess(userService.modify(user));
	}
	
	@ResponseBody
	@RequestMapping("delById")
	public Msg delById(String id) throws MyException{
		User user = userJpa.findOne(id);
		if(null==user){
			throw new MyException("0012","user Id 参数异常");
		}
		user.setUsIsRemove(1);
		return Msg.MsgSuccess(userService.remove(user));
	}
	
	@ResponseBody
	@RequestMapping("addByEntity")
	public Msg addByEntity(String loginName, String password, String code, String userType, String loginType) throws MyException{
		return Msg.MsgSuccess(userService.register(loginName, password, code, userType, loginType));
	}
	
	
}
