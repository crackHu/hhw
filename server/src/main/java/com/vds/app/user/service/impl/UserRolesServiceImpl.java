package com.vds.app.user.service.impl;

import javax.inject.Inject;
import com.vds.app.exception.Msg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.vds.app.base.BaseServiceImpl;
import com.vds.app.user.model.UserRoles;

import com.vds.app.user.jpa.UserRolesJpa;

import com.vds.app.user.service.UserRolesService;

@Service
public class UserRolesServiceImpl extends BaseServiceImpl<UserRoles> implements UserRolesService{

	@Inject
	private UserRolesJpa userRolesJpa;

	public Msg findAll(Pageable pageable) {
		Page<UserRoles> list = userRolesJpa.findAll(pageable);
	return Msg.MsgSuccess(list);
	}
}
