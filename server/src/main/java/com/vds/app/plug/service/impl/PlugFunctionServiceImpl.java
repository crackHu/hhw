package com.vds.app.plug.service.impl;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vds.app.base.BaseServiceImpl;
import com.vds.app.plug.jpa.PlugFunctionJpa;
import com.vds.app.plug.jpa.PlugInJpa;
import com.vds.app.plug.model.PlugFunction;
import com.vds.app.plug.model.PlugIn;
import com.vds.app.plug.service.PlugFunctionService;

@Service
public class PlugFunctionServiceImpl extends BaseServiceImpl<PlugFunction> implements PlugFunctionService{

	@Inject
	PlugInJpa plugInJpa;
	
	@Inject
	PlugFunctionJpa pfJpa;
	
	@Override
	public Page<PlugFunction> findByPiName(String name,Pageable pageable) {
		PlugIn pi = plugInJpa.findByPiName(name);
		if(null==pi){
			return null;
		}
		return pfJpa.findByPfPiId(pi.getPiId(),pageable);
	}

}
