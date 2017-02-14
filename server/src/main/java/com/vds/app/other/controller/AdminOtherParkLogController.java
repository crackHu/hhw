
package com.vds.app.other.controller;

import javax.inject.Inject;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vds.app.exception.Msg;
import com.vds.app.exception.MyException;
import com.vds.app.other.model.OtherParkLog;
import com.vds.app.other.service.OtherParkLogService;

@RestController
@RequestMapping("/v2/admin/other/otherparklog")
public class AdminOtherParkLogController {

	@Inject
	private OtherParkLogService otherParkLogService;


	@RequestMapping("update")
	public Object update(OtherParkLog obj) throws MyException {

		return otherParkLogService.modify(obj);
	}

	@RequestMapping("delById")
	public Object delById(String id) throws MyException {

		OtherParkLog obj = otherParkLogService.findOne(id);
		if (null == obj) {
			return Msg.MsgError("id error");
		}
		return otherParkLogService.remove(obj);
	}

	@RequestMapping("showById")
	public Object showById(String id) throws MyException {

		return otherParkLogService.findByOne(id);
	}

	@RequestMapping("findByPage")
	public Object findByPage(Pageable pageable) {
		return otherParkLogService.findAll(pageable);
	}

	@RequestMapping("editByEntity")
	public Object editByEntity(OtherParkLog obj) throws MyException {
		return otherParkLogService.modify(obj);
	}

	@RequestMapping("addEntity")
	public Object addEntity(OtherParkLog obj) throws MyException {

		return otherParkLogService.add(obj);
	}

}