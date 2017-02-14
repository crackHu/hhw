package com.vds.app.user.service.impl;

import javax.inject.Inject;
import com.vds.app.exception.Msg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.vds.app.base.BaseServiceImpl;
import com.vds.app.user.model.UserRs;

import com.vds.app.user.jpa.UserRsJpa;

import com.vds.app.user.service.UserRsService;

@Service
public class UserRsServiceImpl extends BaseServiceImpl<UserRs> implements UserRsService{

	@Inject
	private UserRsJpa userRsJpa;

	public Msg findAll(Pageable pageable) {
		Page<UserRs> list = userRsJpa.findAll(pageable);
	return Msg.MsgSuccess(list);
	}
}
