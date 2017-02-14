package com.vds.app.plug.service;

import com.vds.app.base.BaseService;
import com.vds.app.plug.model.PlugIn;

public interface PlugInService extends BaseService<PlugIn> {

	public void updateInitByAll();

	public boolean updateInit(String name, Integer status);

	public Object call(Object obj);
}
