package com.vds.app.other.service.impl;

import javax.inject.Inject;
import com.vds.app.exception.Msg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.vds.app.base.BaseServiceImpl;
import com.vds.app.other.model.OtherParkImage;

import com.vds.app.other.jpa.OtherParkImageJpa;

import com.vds.app.other.service.OtherParkImageService;

@Service
public class OtherParkImageServiceImpl extends BaseServiceImpl<OtherParkImage> implements OtherParkImageService{

	@Inject
	private OtherParkImageJpa otherParkImageJpa;

	public Msg findAll(Pageable pageable) {
		Page<OtherParkImage> list = otherParkImageJpa.findAll(pageable);
	return Msg.MsgSuccess(list);
	}
}
