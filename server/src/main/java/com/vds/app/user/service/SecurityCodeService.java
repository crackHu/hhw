package com.vds.app.user.service;

import com.vds.app.base.BaseService;
import com.vds.app.exception.Msg;
import com.vds.app.exception.MyException;
import com.vds.app.user.model.SecurityCode;

public interface SecurityCodeService extends BaseService<SecurityCode>{
	
	public Msg add(SecurityCode SecurityCode) throws MyException;
	
	public Msg verificationPhoneCode(String phone,String code) throws MyException;
	
	
	
}
