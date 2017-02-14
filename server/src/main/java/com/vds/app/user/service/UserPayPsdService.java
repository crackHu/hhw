package com.vds.app.user.service;

import org.springframework.data.domain.Pageable;

import com.vds.app.base.BaseService;
import com.vds.app.exception.Msg;
import com.vds.app.exception.MyException;
import com.vds.app.user.model.UserPayPsd;

public interface UserPayPsdService extends BaseService<UserPayPsd> {

	public Msg findAll(Pageable pageable);

	public Msg setPayPassword(String wId, String password) throws MyException;
}
