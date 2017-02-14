package com.vds.app.user.service.impl;

import javax.inject.Inject;
import com.vds.app.exception.Msg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.vds.app.base.BaseServiceImpl;
import com.vds.app.user.model.UserRole;

import com.vds.app.user.jpa.UserRoleJpa;

import com.vds.app.user.service.UserRoleService;

@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole> implements UserRoleService{

	@Inject
	private UserRoleJpa userRoleJpa;

	public Msg findAll(Pageable pageable) {
		Page<UserRole> list = userRoleJpa.findAll(pageable);
	return Msg.MsgSuccess(list);
	}
}
