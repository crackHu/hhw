package com.vds.app.other.service;

import org.springframework.data.domain.Pageable;
import com.vds.app.base.BaseService;
import com.vds.app.exception.Msg; 
import com.vds.app.other.model.OtherParkLog;

public interface OtherParkLogService extends BaseService<OtherParkLog>{

	public Msg findAll(Pageable pageable);
}
