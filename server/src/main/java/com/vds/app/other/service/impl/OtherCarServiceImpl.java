package com.vds.app.other.service.impl;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vds.app.base.BaseServiceImpl;
import com.vds.app.exception.Msg;
import com.vds.app.exception.MyException;
import com.vds.app.other.jpa.OtherCarJpa;
import com.vds.app.other.model.OtherCar;
import com.vds.app.other.service.OtherCarService;

@Service
public class OtherCarServiceImpl extends BaseServiceImpl<OtherCar> implements OtherCarService {

	@Inject
	private OtherCarJpa otherCarJpa;

	public Msg findAll(Pageable pageable) {
		Page<OtherCar> list = otherCarJpa.findAll(pageable);
		return Msg.MsgSuccess(list);
	}

	@Override
	public Msg delByCarIdAndUsId(String carId, String usId) throws MyException {
		OtherCar car = otherCarJpa.findByOtCarIdAndOtCarUsId(carId, usId);
		if (null == car) {
			throw new MyException("0002", "参数异常");
		}
		return remove(car);
	}

	@Override
	public Msg findByUsId(String usId, Pageable pageable) {
		return Msg.MsgSuccess(otherCarJpa.findByOtCarUsId(usId, pageable));
	}
}
