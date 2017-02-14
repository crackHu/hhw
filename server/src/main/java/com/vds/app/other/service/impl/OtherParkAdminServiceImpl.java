package com.vds.app.other.service.impl;

import javax.inject.Inject;
import com.vds.app.exception.Msg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.vds.app.base.BaseServiceImpl;
import com.vds.app.other.model.OtherParkAdmin;

import com.vds.app.other.jpa.OtherParkAdminJpa;

import com.vds.app.other.service.OtherParkAdminService;

@Service
public class OtherParkAdminServiceImpl extends BaseServiceImpl<OtherParkAdmin> implements OtherParkAdminService{

	@Inject
	private OtherParkAdminJpa otherParkAdminJpa;

	public Msg findAll(Pageable pageable) {
		Page<OtherParkAdmin> list = otherParkAdminJpa.findAll(pageable);
	return Msg.MsgSuccess(list);
	}
}
