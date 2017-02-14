package com.vds.app.other.service;

import org.springframework.data.domain.Pageable;
import com.vds.app.base.BaseService;
import com.vds.app.exception.Msg; 
import com.vds.app.other.model.OtherParkImage;

public interface OtherParkImageService extends BaseService<OtherParkImage>{

	public Msg findAll(Pageable pageable);
}
