package com.vds.app.plug.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vds.app.plug.model.PlugFunction;

public interface PlugFunctionJpa extends JpaRepository<PlugFunction, String>{

	Page<PlugFunction> findByPfPiId(String id,Pageable pageable);
	
}
