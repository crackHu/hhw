package com.vds.app.user.service.impl;

import javax.inject.Inject;
import com.vds.app.exception.Msg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.vds.app.base.BaseServiceImpl;
import com.vds.app.user.model.UserResources;

import com.vds.app.user.jpa.UserResourcesJpa;

import com.vds.app.user.service.UserResourcesService;

@Service
public class UserResourcesServiceImpl extends BaseServiceImpl<UserResources> implements UserResourcesService{

	@Inject
	private UserResourcesJpa userResourcesJpa;

	public Msg findAll(Pageable pageable) {
		Page<UserResources> list = userResourcesJpa.findAll(pageable);
	return Msg.MsgSuccess(list);
	}
}
