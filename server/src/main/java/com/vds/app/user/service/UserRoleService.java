package com.vds.app.user.service;

import org.springframework.data.domain.Pageable;

import com.vds.app.base.BaseService;
import com.vds.app.exception.Msg;
import com.vds.app.user.model.UserRole;

public interface UserRoleService extends BaseService<UserRole> {

	public Msg findAll(Pageable pageable);
}
