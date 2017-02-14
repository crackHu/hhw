package com.vds.app.other.service;

import org.springframework.data.domain.Pageable;

import com.vds.app.base.BaseService;
import com.vds.app.exception.Msg;
import com.vds.app.exception.MyException;
import com.vds.app.other.model.OtherParkSite;

public interface OtherParkSiteService extends BaseService<OtherParkSite> {

	public Msg findAll(Pageable pageable);

	public Msg findByPark(String id) throws MyException;

	public Msg editByEntity(OtherParkSite obj) throws MyException;

	public Msg findByIsEnable(boolean isEnable, Pageable pageable);
	
	public void updateParkSite(int method,String parkSiteId,int count) throws MyException;

}
