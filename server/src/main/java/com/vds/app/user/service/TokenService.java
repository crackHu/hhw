package com.vds.app.user.service;

import com.vds.app.base.BaseService;
import com.vds.app.user.model.UserToken;

public interface TokenService extends BaseService<UserToken>{

	public UserToken findByUsId(String usId);
	
	public UserToken updateToken(String usId);
}
