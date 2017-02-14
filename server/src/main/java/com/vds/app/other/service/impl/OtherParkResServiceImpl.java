package com.vds.app.other.service.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import com.vds.app.exception.Msg;
import com.vds.app.exception.MyException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.vds.app.base.BaseServiceImpl;
import com.vds.app.other.model.OtherParkRes;

import com.vds.app.other.jpa.OtherParkResJpa;

import com.vds.app.other.service.OtherParkResService;

@Service
public class OtherParkResServiceImpl extends BaseServiceImpl<OtherParkRes> implements OtherParkResService{

	@Inject
	private OtherParkResJpa otherParkResJpa;

	public Msg findAll(Pageable pageable) {
		Page<OtherParkRes> list = otherParkResJpa.findAll(pageable);
	return Msg.MsgSuccess(list);
	}

	@Override
	public Object updateBySiteIdAndType(OtherParkRes otherParkRes) throws MyException {
	   if(otherParkRes.getOtSiteId() == null || otherParkRes.getOtSiteId() == ""  
			 || otherParkRes.getOtType() == null || otherParkRes.getOtType() == ""
			 || otherParkRes.getOtCount() < 0  ){
			throw new MyException("0011","参数异常！");
		}
		Date time = new Date(System.currentTimeMillis());
		
		String hql = "from OtherParkRes ot where ot.otSiteId="+otherParkRes.getOtSiteId()+" and ot.otType="+otherParkRes.getOtType();
		List<?> oths = 	this.find(hql);
	    if(oths == null || oths.size() == 0 ){//新增
	      otherParkRes.setOtCreateDate(time);
		  return Msg.MsgSuccess(add(otherParkRes));
		}
	    OtherParkRes os = null ;
	    for (Object object : oths) {
	    	os = (OtherParkRes) object;
		}
	    os.setOtCount(otherParkRes.getOtCount());
	    os.setOtModifyDate(time);
	    return Msg.MsgSuccess(modify(os));//修改
	}
}
