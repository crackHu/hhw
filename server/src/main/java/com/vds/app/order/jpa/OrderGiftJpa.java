package com.vds.app.order.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vds.app.order.model.OrderGift;

public interface OrderGiftJpa extends JpaRepository<OrderGift, String>{

}
