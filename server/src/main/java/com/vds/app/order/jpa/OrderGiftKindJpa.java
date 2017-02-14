package com.vds.app.order.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vds.app.order.model.OrderGiftKind;

public interface OrderGiftKindJpa extends JpaRepository<OrderGiftKind, String>{

}
