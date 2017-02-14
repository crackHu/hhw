package com.vds.app.plug.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vds.app.base.BaseService;
import com.vds.app.plug.model.PlugFunction;

public interface PlugFunctionService extends BaseService<PlugFunction>{

	public Page<PlugFunction> findByPiName(String name,Pageable pageable);
	
}
