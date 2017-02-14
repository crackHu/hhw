package com.vds.app.user.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vds.app.user.model.UserWallet;

public interface UserWalletJpa extends JpaRepository<UserWallet, String> {

	UserWallet findByWlUsIdAndWlIsRemove(String usId, int isRemove);
	
}
