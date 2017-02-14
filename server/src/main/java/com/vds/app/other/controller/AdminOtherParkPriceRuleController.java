
package com.vds.app.other.controller;

import javax.inject.Inject;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vds.app.exception.Msg;
import com.vds.app.exception.MyException;
import com.vds.app.other.model.OtherParkPriceRule;
import com.vds.app.other.service.OtherParkPriceRuleService;

@RestController
@RequestMapping("/v2/admin/other/otherparkpricerule")
public class AdminOtherParkPriceRuleController {

	@Inject
	private OtherParkPriceRuleService otherParkPriceRuleService;


	@RequestMapping("update")
	public Object update(OtherParkPriceRule obj) throws MyException {

		return otherParkPriceRuleService.modify(obj);
	}

	@RequestMapping("delById")
	public Object delById(String id) throws MyException {

		OtherParkPriceRule obj = otherParkPriceRuleService.findOne(id);
		if (null == obj) {
			return Msg.MsgError("id error");
		}
		return otherParkPriceRuleService.remove(obj);
	}

	@RequestMapping("showById")
	public Object showById(String id) throws MyException {

		return otherParkPriceRuleService.findByOne(id);
	}

	@RequestMapping("findByPage")
	public Object findByPage(Pageable pageable) {
		return otherParkPriceRuleService.findAll(pageable);
	}

	@RequestMapping("editByEntity")
	public Object editByEntity(OtherParkPriceRule obj) throws MyException {
		return otherParkPriceRuleService.modify(obj);
	}

	@RequestMapping("addEntity")
	public Object addEntity(OtherParkPriceRule obj) throws MyException {

		return otherParkPriceRuleService.add(obj);
	}

}