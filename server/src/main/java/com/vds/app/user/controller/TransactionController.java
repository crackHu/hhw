package com.vds.app.user.controller;

import javax.inject.Inject;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vds.app.exception.Msg;
import com.vds.app.exception.MyException;
import com.vds.app.user.jpa.TransactionJpa;

@RestController
@RequestMapping("v2/user/transaction")
public class TransactionController extends BaseController{

	@Inject
	private TransactionJpa transactionJpa;
	
	//查询个人交易信息（分页）
	@RequestMapping("getTransaction")
	public Object getTransaction(String tokenContent,Pageable pageable) throws MyException{
		
		return transactionJpa.findByTrWlId(getWalletByToken(tokenContent).getWlId(), pageable);
	}
}
