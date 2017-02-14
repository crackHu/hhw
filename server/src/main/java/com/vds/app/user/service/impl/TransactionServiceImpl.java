package com.vds.app.user.service.impl;

import javax.inject.Inject;
import com.vds.app.exception.Msg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.vds.app.base.BaseServiceImpl;
import com.vds.app.user.model.Transaction;

import com.vds.app.user.jpa.TransactionJpa;

import com.vds.app.user.service.TransactionService;

@Service
public class TransactionServiceImpl extends BaseServiceImpl<Transaction> implements TransactionService{

	@Inject
	private TransactionJpa transactionJpa;

	public Msg findAll(Pageable pageable) {
		Page<Transaction> list = transactionJpa.findAll(pageable);
	return Msg.MsgSuccess(list);
	}
}
