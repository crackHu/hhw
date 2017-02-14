package com.vds.app.other.service;

import org.springframework.data.domain.Pageable;
import com.vds.app.base.BaseService;
import com.vds.app.exception.Msg;
import com.vds.app.exception.MyException;
import com.vds.app.other.model.OtherParkPriceRule;

public interface OtherParkPriceRuleService extends BaseService<OtherParkPriceRule>{

	public Msg findAll(Pageable pageable);
	
	public OtherParkPriceRule findBySiteIdAndCategory(String siteId,String category) throws MyException;
}
