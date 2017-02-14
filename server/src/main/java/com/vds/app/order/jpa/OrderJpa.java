package com.vds.app.order.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.vds.app.order.model.Order;

public interface OrderJpa extends JpaRepository<Order, String> {

	Page<Order> findByOdUsIdAndOdParkSiteIdAndOdStatus(String odUsId, String odParkSiteId, Integer status,
			Pageable pageable);

	Page<Order> findByOdUsIdAndOdStatus(String odUsId, Integer status, Pageable pageable);

	Page<Order> findByOdUsIdAndOdParkSiteId(String odUsId, String odParkSiteId, Pageable pageable);

	Page<Order> findByOdUsId(String odUsId, Pageable pageable);
	
	Order findByOdBarcode(String code);
	
	List<Order> findByOdUsIdAndOdStatus(String userId,Integer status);
	
	@Query("From Order o WHERE o.odUsId=?1 AND (o.odStatus=0 OR o.odStatus=1 OR o.odStatus=2) ORDER BY o.odCreateTime DESC")
	List<Order> findByUnderOrder(String userId);
	
	@Modifying
	@Query("update Order o set o.odStatus = -1 where o.odStatus = 0 and o.odCreateTime <= ?1")
	void updateTimeoutOrder(String date);
}
