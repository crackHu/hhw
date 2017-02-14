package com.vds.app.other.controller;

import javax.inject.Inject;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vds.app.exception.Msg;
import com.vds.app.exception.MyException;
import com.vds.app.other.model.OtherCar;
import com.vds.app.other.service.OtherCarService;
import com.vds.app.user.controller.BaseController;

@RestController
@RequestMapping("v2/other/car")
public class OtherCarController extends BaseController {

	@Inject
	private OtherCarService otherCarService;

	@RequestMapping("getCarByToken")
	private Object getCarByToken(Pageable pageable) throws MyException {

		return Msg.MsgSuccess(otherCarService.findByUsId(getUserByToken().getUsId(), pageable));
	}

	@RequestMapping("delCarByToken")
	private Object delCarByToken(String carId) throws MyException {

		return otherCarService.delByCarIdAndUsId(carId, getUserByToken().getUsId());
	}

	@RequestMapping("setCarByToken")
	private Object setCarByToken(OtherCar car) throws MyException {
		car.setOtCarUsId(getUserByToken().getUsId());
		return otherCarService.add(car);
	}

}
