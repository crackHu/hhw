package com.vds.app.plug.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.vds.app.base.BaseServiceImpl;
import com.vds.app.exception.Msg;
import com.vds.app.plug.jpa.PlugInJpa;
import com.vds.app.plug.model.PlugIn;
import com.vds.app.plug.service.PlugInService;

import net.sf.json.JSONObject;

@Service
public class PlugInServiceImpl extends BaseServiceImpl<PlugIn> implements PlugInService {

	@Inject
	private PlugInJpa plugInJpa;

	@Override
	public void updateInitByAll() {
		
	}

	@Override
	public boolean updateInit(String name, Integer status) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object call(Object obj) {
		// TODO Auto-generated method stub
		JSONObject jo = JSONObject.fromObject(obj);
		String name = jo.get("name").toString();

		return Msg.MsgSuccess(plugInJpa.findByPiName(name));
	}

}
