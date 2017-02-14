package com.vds.app.user.service.impl;

import javax.inject.Inject;
import com.vds.app.exception.Msg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.vds.app.base.BaseServiceImpl;
import com.vds.app.user.model.UserRoleResources;

import com.vds.app.user.jpa.UserRoleResourcesJpa;

import com.vds.app.user.service.UserRoleResourcesService;

@Service
public class UserRoleResourcesServiceImpl extends BaseServiceImpl<UserRoleResources> implements UserRoleResourcesService{

	@Inject
	private UserRoleResourcesJpa userRoleResourcesJpa;

	public Msg findAll(Pageable pageable) {
		Page<UserRoleResources> list = userRoleResourcesJpa.findAll(pageable);
	return Msg.MsgSuccess(list);
	}
}
