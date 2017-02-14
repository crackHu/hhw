package com.vds.app.plug.controller;

import javax.inject.Inject;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vds.app.exception.MyException;
import com.vds.app.plug.service.PlugInService;

@RestController
@RequestMapping("v2/admin/pluginfo")
public class PlugInController {

	@Inject
	private PlugInService piService;

	@RequestMapping("findByPage")
	public Object findByPage(Pageable pageable) throws MyException {

		return piService.findPage(pageable);
	}

}
