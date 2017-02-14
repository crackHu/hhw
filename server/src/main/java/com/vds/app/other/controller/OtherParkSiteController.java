
package com.vds.app.other.controller;

import javax.inject.Inject;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vds.app.exception.MyException;
import com.vds.app.other.model.OtherParkSite;
import com.vds.app.other.service.OtherParkSiteService;

@RestController
@RequestMapping("/v2/other/otherparksite")
public class OtherParkSiteController {

	@Inject
	private OtherParkSiteService otherParkSiteService;

	// 获取停车场明细 2017-0109（用户）
	@RequestMapping("getParkingById")
	public Object getParkingById(String id) throws MyException {

		return otherParkSiteService.findByPark(id);
	}

	// 获取停车场列表 2017-0109（用户）
	@RequestMapping("findByPage")
	public Object findByPage(Pageable pageable) {

		return otherParkSiteService.findAll(pageable);
	}

	// 设置释放车位数量2017-0110
	// 释放车辆需要小于车库车位总数量，大于已经租用的车位数量。
	@RequestMapping("setParkRes")
	public Object setParkRes(String otSiteId, long count) throws MyException {
		OtherParkSite op = otherParkSiteService.findOne(otSiteId);
		op.setOtFreeRes(count);
		return otherParkSiteService.editByEntity(op);
	}

	@RequestMapping("getParkingListByPage")
	public Object getParkingListByPage(Pageable pageable) {

		return otherParkSiteService.findByIsEnable(true, pageable);
	}

}