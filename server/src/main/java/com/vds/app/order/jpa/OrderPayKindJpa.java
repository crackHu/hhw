package com.vds.app.order.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vds.app.order.model.OrderPayKind;

public interface OrderPayKindJpa extends JpaRepository<OrderPayKind, String>{

}
