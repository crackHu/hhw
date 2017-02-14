package com.vds.app.plug.controller;

import javax.inject.Inject;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vds.app.exception.MyException;
import com.vds.app.plug.service.PlugFunctionService;

@RestController
@RequestMapping("v2/admin/plugconfig")
public class PlugFunctionController {

	@Inject
	private PlugFunctionService pfService;

	@RequestMapping("findByPage")
	public Object findByPage(Pageable pageable) throws MyException {

		return pfService.findPage(pageable);
	}

}
