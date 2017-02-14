package com.vds.app.user.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vds.app.user.model.UserToken;

public interface TokenJpa extends JpaRepository<UserToken, String>{

	UserToken findByTkUsId(String usId);
	
	UserToken findByTkTokenContent(String token);
}
