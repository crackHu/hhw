
package com.vds.app.user.controller;

import javax.inject.Inject;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vds.app.exception.Msg;
import com.vds.app.exception.MyException;
import com.vds.app.user.model.UserPayPsd;
import com.vds.app.user.service.UserPayPsdService;

@Controller
@RequestMapping("/v2/admin/user/userpaypsd")
public class AdminUserPayPsdController {

	@Inject
	private UserPayPsdService userPayPsdService;

	@RequestMapping("getView")
	public String getView(ModelMap map, Pageable pageable) {
		map.put("smsCpMap", userPayPsdService.findAll(pageable));
		return "admin/sms/sms_cp_view";
	}

	@ResponseBody
	@RequestMapping("getTable")
	public Object getTable(Pageable pageable) {

		return userPayPsdService.findAll(pageable);
	}

	@ResponseBody
	@RequestMapping("update")
	public Object update(UserPayPsd cp) throws MyException {

		return userPayPsdService.modify(cp);
	}

	@ResponseBody
	@RequestMapping("delById")
	public Object delById(String id) throws MyException {

		UserPayPsd cp = userPayPsdService.findOne(id);
		if (null == cp) {
			return Msg.MsgError("id error");
		}
		return userPayPsdService.remove(cp);
	}

	@ResponseBody
	@RequestMapping("showById")
	public Object showById(String id) throws MyException {

		return userPayPsdService.findByOne(id);
	}

	@ResponseBody
	@RequestMapping("findByPage")
	public Object findByPage(Pageable pageable) {
		return userPayPsdService.findAll(pageable);
	}

	@ResponseBody
	@RequestMapping("editByEntiry")
	public Object editByUserPayPsd(UserPayPsd cp) throws MyException {
		return userPayPsdService.modify(cp);
	}

	@ResponseBody
	@RequestMapping("addUserPayPsd")
	public Object addUserPayPsd(UserPayPsd cp) throws MyException, com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException {

		return userPayPsdService.add(cp);
	}

}