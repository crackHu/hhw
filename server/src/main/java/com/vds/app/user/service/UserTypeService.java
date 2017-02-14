package com.vds.app.user.service;

import org.springframework.data.domain.Pageable;
import com.vds.app.base.BaseService;
import com.vds.app.exception.Msg;
import com.vds.app.exception.MyException;
import com.vds.app.user.jpa.UserTypeJpa;

import com.vds.app.user.model.UserType;

public interface UserTypeService extends BaseService<UserType>{

	public Msg findAll(Pageable pageable);
}
