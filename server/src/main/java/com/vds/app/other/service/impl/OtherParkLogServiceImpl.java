package com.vds.app.other.service.impl;

import javax.inject.Inject;
import com.vds.app.exception.Msg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.vds.app.base.BaseServiceImpl;
import com.vds.app.other.model.OtherParkLog;

import com.vds.app.other.jpa.OtherParkLogJpa;

import com.vds.app.other.service.OtherParkLogService;

@Service
public class OtherParkLogServiceImpl extends BaseServiceImpl<OtherParkLog> implements OtherParkLogService{

	@Inject
	private OtherParkLogJpa otherParkLogJpa;

	public Msg findAll(Pageable pageable) {
		Page<OtherParkLog> list = otherParkLogJpa.findAll(pageable);
	return Msg.MsgSuccess(list);
	}
}
