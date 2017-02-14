package com.vds.app.other.service;

import org.springframework.data.domain.Pageable;
import com.vds.app.base.BaseService;
import com.vds.app.exception.Msg;
import com.vds.app.exception.MyException;
import com.vds.app.other.model.OtherParkArea;

public interface OtherParkAreaService extends BaseService<OtherParkArea>{

	public Msg findAll(Pageable pageable);

	public Object showByPId(String pId) throws MyException;
}
