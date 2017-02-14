package com.vds.app.order.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vds.app.order.model.OrderPayment;

public interface OrderPaymentJpa extends JpaRepository<OrderPayment, String> {

	public List<OrderPayment> findByOdPayOdId(String odPayOrId);
}
