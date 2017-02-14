package com.vds.app.other.service.impl;

import java.util.List;

import javax.inject.Inject;
import com.vds.app.exception.Msg;
import com.vds.app.exception.MyException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.vds.app.base.BaseServiceImpl;
import com.vds.app.other.model.OtherParkArea;

import com.vds.app.other.jpa.OtherParkAreaJpa;

import com.vds.app.other.service.OtherParkAreaService;

@Service
public class OtherParkAreaServiceImpl extends BaseServiceImpl<OtherParkArea> implements OtherParkAreaService{

	@Inject
	private OtherParkAreaJpa otherParkAreaJpa;

	public Msg findAll(Pageable pageable) {
		Page<OtherParkArea> list = otherParkAreaJpa.findAll(pageable);
	    return Msg.MsgSuccess(list);
	}

	@Override
	public Object showByPId(String pId) throws MyException {
		if(pId == "")
			pId = null;
		List<?> lists = this.find("from OtherParkArea a where a.otPId = "+pId);
		return Msg.MsgSuccess(lists);
	}
}
