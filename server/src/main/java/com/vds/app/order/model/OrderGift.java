package com.vds.app.order.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Scope;

@Entity
@Table(name = "tb_order_gift")
@Scope(value = "prototype")
public class OrderGift implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "od_gf_id")
	private String odGfId;//
	@Column(name = "od_gf_us_id")
	private String odGfUsId;//
	@Column(name = "od_gf_gk_id")
	private String odGfGkId;//
	@Column(name = "od_gf_code")
	private String odGfCode;//
	@Column(name = "od_gf_is_user")
	private int odGfIsUser;//
	@Column(name = "od_gf_start_time")
	private Date odGfStartTime;//
	@Column(name = "od_gf_end_time")
	private Date odGfEndTime;//
	@Column(name = "od_gf_price")
	private double odGfPrice;//
	@Column(name = "od_gf_create_time")
	private Date odGfCreateTime;//
	@Column(name = "od_gf_modfiy_time")
	private Date odGfModfiyTime;//
	public String getOdGfId() {
		return odGfId;
	}
	public void setOdGfId(String odGfId) {
		this.odGfId = odGfId;
	}
	public String getOdGfUsId() {
		return odGfUsId;
	}
	public void setOdGfUsId(String odGfUsId) {
		this.odGfUsId = odGfUsId;
	}
	public String getOdGfGkId() {
		return odGfGkId;
	}
	public void setOdGfGkId(String odGfGkId) {
		this.odGfGkId = odGfGkId;
	}
	public String getOdGfCode() {
		return odGfCode;
	}
	public void setOdGfCode(String odGfCode) {
		this.odGfCode = odGfCode;
	}
	public int getOdGfIsUser() {
		return odGfIsUser;
	}
	public void setOdGfIsUser(int odGfIsUser) {
		this.odGfIsUser = odGfIsUser;
	}
	public Date getOdGfStartTime() {
		return odGfStartTime;
	}
	public void setOdGfStartTime(Date odGfStartTime) {
		this.odGfStartTime = odGfStartTime;
	}
	public Date getOdGfEndTime() {
		return odGfEndTime;
	}
	public void setOdGfEndTime(Date odGfEndTime) {
		this.odGfEndTime = odGfEndTime;
	}
	public double getOdGfPrice() {
		return odGfPrice;
	}
	public void setOdGfPrice(double odGfPrice) {
		this.odGfPrice = odGfPrice;
	}
	public Date getOdGfCreateTime() {
		return odGfCreateTime;
	}
	public void setOdGfCreateTime(Date odGfCreateTime) {
		this.odGfCreateTime = odGfCreateTime;
	}
	public Date getOdGfModfiyTime() {
		return odGfModfiyTime;
	}
	public void setOdGfModfiyTime(Date odGfModfiyTime) {
		this.odGfModfiyTime = odGfModfiyTime;
	}

	
}
