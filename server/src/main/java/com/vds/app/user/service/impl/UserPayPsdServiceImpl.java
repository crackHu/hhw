package com.vds.app.user.service.impl;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vds.app.base.BaseServiceImpl;
import com.vds.app.exception.Msg;
import com.vds.app.exception.MyException;
import com.vds.app.user.jpa.UserPayPsdJpa;
import com.vds.app.user.model.UserPayPsd;
import com.vds.app.user.service.UserPayPsdService;
import com.vds.app.util.MD5Util;

@Service
public class UserPayPsdServiceImpl extends BaseServiceImpl<UserPayPsd> implements UserPayPsdService {
	@Inject
	private UserPayPsdJpa jpa;

	public Msg findAll(Pageable pageable) {
		Page<UserPayPsd> list = jpa.findAll(pageable);
		return Msg.MsgSuccess(list);
	}

	@Override
	public Msg setPayPassword(String wId, String password) throws MyException {

		UserPayPsd  userPayPsd = jpa.findByPpWlId(wId);
		if(null==password || password.equals("")){
			throw new MyException();
		}
		userPayPsd.setPpPayPassword(MD5Util.md5(password));
		jpa.save(userPayPsd);
		return Msg.MsgSuccess();
	}
}
