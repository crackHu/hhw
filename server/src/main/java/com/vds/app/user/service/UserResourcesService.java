package com.vds.app.user.service;

import org.springframework.data.domain.Pageable;

import com.vds.app.base.BaseService;
import com.vds.app.exception.Msg;
import com.vds.app.user.model.UserResources;

public interface UserResourcesService extends BaseService<UserResources>{

	public Msg findAll(Pageable pageable);
}
