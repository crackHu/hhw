package com.vds.app.user.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vds.app.user.model.User;

public interface UserJpa extends JpaRepository<User, String> {

	User findByUsUserNameAndUsType(String userName, String ustype);

	User findByUsPhoneAndUsTypeAndUsIsRemove(String usPhone, String usType, Integer isRemove);

	User findByUsUserNameAndUsTypeAndUsIsRemove(String userName, String type, Integer isRemove);

}
