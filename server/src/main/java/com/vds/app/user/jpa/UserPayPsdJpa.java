package com.vds.app.user.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vds.app.user.model.UserPayPsd;

public interface UserPayPsdJpa extends JpaRepository<UserPayPsd, String>{
		
	public UserPayPsd findByPpWlId(String ppWlId);

}
