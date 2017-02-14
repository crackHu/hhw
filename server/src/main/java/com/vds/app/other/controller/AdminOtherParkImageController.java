
package com.vds.app.other.controller;

import javax.inject.Inject;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vds.app.exception.Msg;
import com.vds.app.exception.MyException;
import com.vds.app.other.model.OtherParkImage;
import com.vds.app.other.service.OtherParkImageService;

@RestController
@RequestMapping("/v2/admin/other/otherparkimage")
public class AdminOtherParkImageController {

	@Inject
	private OtherParkImageService otherParkImageService;


	@RequestMapping("update")
	public Object update(OtherParkImage obj) throws MyException {

		return otherParkImageService.modify(obj);
	}

	@RequestMapping("delById")
	public Object delById(String id) throws MyException {

		OtherParkImage obj = otherParkImageService.findOne(id);
		if (null == obj) {
			return Msg.MsgError("id error");
		}
		return otherParkImageService.remove(obj);
	}

	@RequestMapping("showById")
	public Object showById(String id) throws MyException {

		return otherParkImageService.findByOne(id);
	}

	@RequestMapping("findByPage")
	public Object findByPage(Pageable pageable) {
		return otherParkImageService.findAll(pageable);
	}

	@RequestMapping("editByEntity")
	public Object editByEntity(OtherParkImage obj) throws MyException {
		return otherParkImageService.modify(obj);
	}

	@RequestMapping("addEntity")
	public Object addEntity(OtherParkImage obj) throws MyException {

		return otherParkImageService.add(obj);
	}

}