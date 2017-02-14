package com.vds.app.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vds.app.exception.Msg;
import com.vds.app.exception.MyException;
import com.vds.app.user.jpa.UserWalletJpa;

@RestController
@RequestMapping("v2/user/wallet")
public class WalletController extends BaseController {

	private UserWalletJpa walletJpa;

	@RequestMapping("getWallet")
	public Object getWallet() throws MyException {

		return Msg.MsgSuccess(walletJpa.findByWlUsIdAndWlIsRemove(getUserByToken().getUsId(), 0));
	}

}
