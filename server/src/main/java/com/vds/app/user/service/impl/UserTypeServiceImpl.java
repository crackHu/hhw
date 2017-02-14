package com.vds.app.user.service.impl;

import javax.inject.Inject;
import com.vds.app.exception.Msg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.vds.app.base.BaseServiceImpl;
import com.vds.app.user.model.UserType;

import com.vds.app.user.jpa.UserTypeJpa;

import com.vds.app.user.service.UserTypeService;

@Service
public class UserTypeServiceImpl extends BaseServiceImpl<UserType> implements UserTypeService{

	@Inject
	private UserTypeJpa userTypeJpa;

	public Msg findAll(Pageable pageable) {
		Page<UserType> list = userTypeJpa.findAll(pageable);
	return Msg.MsgSuccess(list);
	}
}
