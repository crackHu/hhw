package com.vds.app.other.service.impl;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vds.app.base.BaseServiceImpl;
import com.vds.app.exception.Msg;
import com.vds.app.exception.MyException;
import com.vds.app.other.jpa.OtherParkPriceRuleJpa;
import com.vds.app.other.model.OtherParkPriceRule;
import com.vds.app.other.service.OtherParkPriceRuleService;
import com.vds.app.other.service.OtherParkSiteService;

@Service
public class OtherParkPriceRuleServiceImpl extends BaseServiceImpl<OtherParkPriceRule>
		implements OtherParkPriceRuleService {

	@Inject
	private OtherParkPriceRuleJpa otherParkPriceRuleJpa;

	@Inject
	private OtherParkSiteService otherParkSiteService;

	public Msg findAll(Pageable pageable) {
		Page<OtherParkPriceRule> list = otherParkPriceRuleJpa.findAll(pageable);
		return Msg.MsgSuccess(list);
	}

	@Override
	public OtherParkPriceRule findBySiteIdAndCategory(String siteId, String category) throws MyException {
		otherParkSiteService.findOne(siteId);
		OtherParkPriceRule opPrice =otherParkPriceRuleJpa.findByOtSiteIdAndOtCatalogId(siteId, category);
		if(null == opPrice){
			throw new MyException("0016","停车场 不存在该类型车位");
		}
		return opPrice;
	}
}
