package com.vds.app.plug.controller;

import javax.inject.Inject;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vds.app.exception.Msg;
import com.vds.app.plug.service.PlugFunctionService;

@RestController
@RequestMapping("/v2/open/plug")
public class PlugOpenController {
	
	@Inject
	private PlugFunctionService pfService;
	
	@RequestMapping("getPlugFunction")
	public Object getPlugFunction(String name,Pageable pageable){
		
		return Msg.MsgSuccess(pfService.findByPiName(name,pageable));
	}
	
	
	
}
