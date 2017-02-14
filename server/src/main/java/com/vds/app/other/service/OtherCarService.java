package com.vds.app.other.service;

import org.springframework.data.domain.Pageable;

import com.vds.app.base.BaseService;
import com.vds.app.exception.Msg;
import com.vds.app.exception.MyException;
import com.vds.app.other.model.OtherCar;

public interface OtherCarService extends BaseService<OtherCar> {

	Msg findAll(Pageable pageable);

	Msg delByCarIdAndUsId(String carId, String usId) throws MyException;

	Msg findByUsId(String usId, Pageable pageable);
}
