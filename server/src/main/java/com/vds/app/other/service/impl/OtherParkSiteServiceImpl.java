package com.vds.app.other.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vds.app.base.BaseServiceImpl;
import com.vds.app.exception.Msg;
import com.vds.app.exception.MyException;
import com.vds.app.other.jpa.OtherParkImageJpa;
import com.vds.app.other.jpa.OtherParkSiteJpa;
import com.vds.app.other.model.OtherParkSite;
import com.vds.app.other.service.OtherParkResService;
import com.vds.app.other.service.OtherParkSiteService;

@Service
public class OtherParkSiteServiceImpl extends BaseServiceImpl<OtherParkSite> implements OtherParkSiteService {

	@Inject
	private OtherParkSiteJpa otherParkSiteJpa;
	@Inject
	private OtherParkImageJpa otherParkImageJpa;
	@Inject
	private OtherParkResService otherParkResService;

	public Msg findAll(Pageable pageable) {
		Page<OtherParkSite> list = otherParkSiteJpa.findAll(pageable);
		return Msg.MsgSuccess(list);
	}

	@Override
	public Msg findByPark(String id) throws MyException {
		OtherParkSite OtherParkSite = otherParkSiteJpa.findOne(id);
		if (OtherParkSite == null) {
			throw new MyException("0009", "没有找到停车场");
		}
		OtherParkSite.setImages(otherParkImageJpa.findByOtSiteId(id));
		return Msg.MsgSuccess(OtherParkSite);
	}

	@Override
	public Msg editByEntity(OtherParkSite obj) throws MyException {
		List<?> lists = otherParkResService
				.find("select sum(otCount) from OtherParkRes where otSiteId=" + obj.getOtId());
		if (lists == null || lists.get(0) == null) {
			throw new MyException("1012", "当前车位未能找到车位资源，请确认停车场ID是否正确");
		}
		int otCountSum = Integer.valueOf(lists.get(0).toString());// 当前车库总车位数
		OtherParkSite otherParkSite = otherParkSiteJpa.findOne(obj.getOtId());
		Long otFreeRes = otherParkSite.getOtFreeRes();// 当前空闲的车位数量
		Long occupyRes = otherParkSite.getOtReleaseRes() - otFreeRes;// 当前占用的车位数量
		Long otReleaseRes = obj.getOtReleaseRes();// 需要释放的车位数量
		if (otReleaseRes > otCountSum || otReleaseRes < occupyRes) {
			throw new MyException("1013", "释放车位数错误，不能大于车库车位总数且不能小于已经租用的车位数");
		}
		otherParkSite.setOtReleaseRes(otReleaseRes);// 释放车位数量
		otherParkSite.setOtFreeRes(otReleaseRes - occupyRes);// 更新空闲车位数量
		return Msg.MsgSuccess(otherParkSiteJpa.save(otherParkSite));
	}

	@Override
	public Msg findByIsEnable(boolean isEnable, Pageable pageable) {
		// TODO Auto-generated method stub
		return Msg.MsgSuccess(otherParkSiteJpa.findByOtIsEnabled(isEnable, pageable));
	}

	
	@Transactional
	@Override
	public void updateParkSite(int method, String parkSiteId,int count) throws MyException {
		//减--
		if(method == 0){
			
			OtherParkSite parkSite = otherParkSiteJpa.findOne(parkSiteId);
			if(null==parkSite){
				throw new MyException("0023","停车场不存在");
			}
			long freeCount = parkSite.getOtFreeRes();
			if(freeCount-count<0){
				throw new MyException("0023",parkSite.getOtName()+"停车空闲数量数据异常");
			}
			
			parkSite.setOtFreeRes(freeCount-count);
			otherParkSiteJpa.save(parkSite);
			
		//加--
		}else if(method == 1){
			
			OtherParkSite parkSite = otherParkSiteJpa.findOne(parkSiteId);
			if(null==parkSite){
				throw new MyException("0023","停车场不存在");
			}
			long freeCount = parkSite.getOtFreeRes();
			long releaseCount = parkSite.getOtReleaseRes();
			if(freeCount+count>releaseCount){
				throw new MyException("0023",parkSite.getOtName()+"停车位数量异常");
			}
			
			parkSite.setOtFreeRes(freeCount+count);
			otherParkSiteJpa.save(parkSite);
			
		}
		
	}

}
