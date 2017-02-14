package com.vds.app.user.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vds.app.exception.MyException;
import com.vds.app.user.service.UserPayPsdService;

@RestController
@RequestMapping("v2/user/paypsd")
public class PayPasswordController extends BaseController{

	@Inject
	private UserPayPsdService psdService;

	@RequestMapping("setPayPassword")
	public Object setPayPassword(String tokenContent,String payPassword) throws MyException {
		
		return psdService.setPayPassword(getWalletByToken(tokenContent).getWlId(), payPassword);
	}

}
