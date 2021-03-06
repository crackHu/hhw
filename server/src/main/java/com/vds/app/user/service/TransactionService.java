package com.vds.app.user.service;

import org.springframework.data.domain.Pageable;

import com.vds.app.base.BaseService;
import com.vds.app.exception.Msg;
import com.vds.app.user.model.Transaction;

public interface TransactionService extends BaseService<Transaction>{

	public Msg findAll(Pageable pageable);
}
