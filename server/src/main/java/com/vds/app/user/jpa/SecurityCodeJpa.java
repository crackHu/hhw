package com.vds.app.user.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vds.app.user.model.SecurityCode;

public interface SecurityCodeJpa extends JpaRepository<SecurityCode, String>{

	public SecurityCode findByScPhone(String phone);
}


