package com.vds.app.plug.controller;

import javax.inject.Inject;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vds.app.exception.Msg;
import com.vds.app.plug.jpa.PlugInJpa;

@RestController
@RequestMapping("/v2/plug/")
public class PlugController {

	@Inject
	private PlugInJpa plugInJpa;
	
	@RequestMapping("findByPage")
	public Msg findByPage(Pageable pageable){
		
		return Msg.MsgSuccess(plugInJpa.findAll(pageable));
	}
	
	@RequestMapping("findByName")
	public Msg findByName(String name){
		
		return Msg.MsgSuccess(plugInJpa.findByPiName(name));
	}
	
		
}
