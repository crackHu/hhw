package com.vds.app.interceptor;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.vds.app.exception.MyException;
import com.vds.app.order.jpa.OrderJpa;
import com.vds.app.other.jpa.OtherParkSiteJpa;
import com.vds.app.other.model.OtherParkSite;
import com.vds.app.other.service.OtherParkSiteService;
import com.vds.app.util.CayUtil;
import com.vds.app.util.Config;

@Configuration
@EnableScheduling // 启用定时任务
public class Timer {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	OrderJpa orderJpa;
	
	@Inject
	OtherParkSiteJpa OtherParkSiteJpa;
	
	@Inject
	OtherParkSiteService otherParkSiteService;
	
	@PersistenceContext
	private EntityManager manager;
	
	public static int status = 0;

	@Modifying
	@Transactional
	@Scheduled(cron = "0/20 * * * * ?") // 每20秒执行一次
	public void scheduler() {
		System.out.println("----Timer start----");
		if(status==0){
			return;
		}
		String updateHql = "update Order o set o.odStatus = -1 where o.odStatus = 0 and o.odCreateTime <=:data and o.odParkSiteId =:parkSiteId";
		String countHql = "SELECT COUNT(o) FROM Order o WHERE o.odStatus = 0 and o.odCreateTime <=:data and o.odParkSiteId =:parkSiteId";
		
		List<OtherParkSite> orderList = OtherParkSiteJpa.findByOtIsEnabled(true);
		for(OtherParkSite ops:orderList){
			Query query = manager.createQuery(countHql);
			query.setParameter("parkSiteId", ops.getOtId());
			query.setParameter("data", CayUtil.getTimeByAfter(new Date(), Calendar.MINUTE, 1), TemporalType.TIMESTAMP);
			int count = Integer.valueOf(query.getSingleResult().toString());
			
			Query query2 = manager.createQuery(updateHql);
			query2.setParameter("parkSiteId", ops.getOtId());
			query2.setParameter("data", CayUtil.getTimeByAfter(new Date(), Calendar.MILLISECOND,Integer.valueOf(Config.ordertimeout)), TemporalType.TIMESTAMP);
			int count2 = query2.executeUpdate();
			logger.debug("定时任务：修改了"+ops.getOtName()+" "+count2+"条记录");
			try {
				otherParkSiteService.updateParkSite(1, ops.getOtId(), count);
			} catch (MyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		manager.close();
		System.out.println("----Timer  end----");
	}

}
