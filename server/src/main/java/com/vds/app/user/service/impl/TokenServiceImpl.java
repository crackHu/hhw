package com.vds.app.user.service.impl;

import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.vds.app.base.BaseServiceImpl;
import com.vds.app.user.jpa.TokenJpa;
import com.vds.app.user.model.UserToken;
import com.vds.app.user.service.TokenService;
import com.vds.app.util.CayUtil;
import com.vds.app.util.Config;

@Service
public class TokenServiceImpl extends BaseServiceImpl<UserToken> implements TokenService {

	@Inject
	private TokenJpa tokenJpa;

	@Override
	public UserToken findByUsId(String usId) {
		// TODO Auto-generated method stub
		return tokenJpa.findByTkUsId(usId);
	}

	@Override
	public UserToken updateToken(String usId) {
		UserToken token = tokenJpa.findByTkUsId(usId);
		token.setTkTokenContent(CayUtil.getUUId());
		
		token.setTkExpireTime(CayUtil.getTimeByAfter(new Date(), Calendar.SECOND, Integer.valueOf(Config.getTokentimeout())));
		tokenJpa.save(token);

		return token;
	}

}
