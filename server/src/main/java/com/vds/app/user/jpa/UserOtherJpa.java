package com.vds.app.user.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vds.app.user.model.UserOther;

public interface UserOtherJpa extends JpaRepository<UserOther, String> {

	UserOther findByUoOpenIdAndUoType(String openId, String uType);

	UserOther findByUoUsId(String usId);

}
