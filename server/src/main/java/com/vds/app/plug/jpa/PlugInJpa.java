package com.vds.app.plug.jpa;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.vds.app.plug.model.PlugIn;


public interface PlugInJpa extends JpaRepository<PlugIn, String>{
	
	@Transactional
	@Modifying(clearAutomatically = true) 
	@Query("update PlugIn pi set pi.piStatus = 0 , pi.piMsg = '未启动'")
	public void initAll();
	
	public PlugIn findByPiName(String piName);
}
