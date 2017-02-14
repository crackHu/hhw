package com.vds.app.user.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RestController;

import com.vds.app.exception.MyException;
import com.vds.app.user.jpa.TokenJpa;
import com.vds.app.user.jpa.UserJpa;
import com.vds.app.user.jpa.UserWalletJpa;
import com.vds.app.user.model.User;
import com.vds.app.user.model.UserToken;
import com.vds.app.user.model.UserWallet;

@RestController
public class BaseController {

	@Inject
	private HttpServletRequest request;

	@Inject
	private TokenJpa tokenJpa;

	@Inject
	private UserJpa userJpa;

	@Inject
	private UserWalletJpa walletJpa;

	public User getUserByToken() throws MyException {

		UserToken token = tokenJpa.findByTkTokenContent(getToken());
		if (null == token) {
			throw new MyException("0000", "未登录");
		}
		User user = userJpa.findOne(token.getTkUsId());
		if (null == user) {
			throw new MyException("0000", "账户不存在");
		}
		return user;
	}

	public UserWallet getWalletByToken(String tokenContent) throws MyException {

		UserWallet wallet = walletJpa.findByWlUsIdAndWlIsRemove(getUserByToken().getUsId(), 0);
		if (null == wallet) {
			throw new MyException("9999", "没有钱包");
		}
		return wallet;
	}

	public String getToken() {
		return request.getParameter("token");
	}

}
