package com.vds.app.user.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.vds.app.user.model.Transaction;
import com.vds.app.user.model.UserWallet;

public interface TransactionJpa extends JpaRepository<Transaction, String>{

	public 	Page<Transaction> findByTrWlId(String trWlId,Pageable pageable );
	
}
