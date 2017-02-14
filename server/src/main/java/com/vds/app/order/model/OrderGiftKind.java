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
@Table(name = "tb_order_gift_kind")
@Scope(value = "prototype")
public class OrderGiftKind implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "od_gk_id")
	private String odGkId;//
	@Column(name = "od_gk_name")
	private String odGkName;//
	@Column(name = "od_gk_begin_time")
	private Date odGkBeginTime;//
	@Column(name = "od_gk_end_time")
	private Date odGkEndTime;//
	@Column(name = "od_gk_desc")
	private String odGkDesc;//
	@Column(name = "od_gk_is_enabled")
	private int odGkIsEnabled;//
	@Column(name = "od_gk_is_exchange")
	private int odGkIsExchange;//
	@Column(name = "od_gk_point")
	private int odGkPoint;//
	@Column(name = "od_gk_price")
	private double odGkPrice;//
	@Column(name = "od_gk_price_expression")
	private String odGkPriceExpression;//
	@Column(name = "od_gk_prefix")
	private String odGkPrefix;//
	@Column(name = "od_gk_create_time")
	private Date odGkCreateTime;//
	@Column(name = "od_gk_modify_time")
	private Date odGkModifyTime;//
	
	
	
	public String getOdGkId() {
		return odGkId;
	}
	public void setOdGkId(String odGkId) {
		this.odGkId = odGkId;
	}
	public String getOdGkName() {
		return odGkName;
	}
	public void setOdGkName(String odGkName) {
		this.odGkName = odGkName;
	}
	public Date getOdGkBeginTime() {
		return odGkBeginTime;
	}
	public void setOdGkBeginTime(Date odGkBeginTime) {
		this.odGkBeginTime = odGkBeginTime;
	}
	public Date getOdGkEndTime() {
		return odGkEndTime;
	}
	public void setOdGkEndTime(Date odGkEndTime) {
		this.odGkEndTime = odGkEndTime;
	}
	public String getOdGkDesc() {
		return odGkDesc;
	}
	public void setOdGkDesc(String odGkDesc) {
		this.odGkDesc = odGkDesc;
	}
	public int getOdGkIsEnabled() {
		return odGkIsEnabled;
	}
	public void setOdGkIsEnabled(int odGkIsEnabled) {
		this.odGkIsEnabled = odGkIsEnabled;
	}
	public int getOdGkIsExchange() {
		return odGkIsExchange;
	}
	public void setOdGkIsExchange(int odGkIsExchange) {
		this.odGkIsExchange = odGkIsExchange;
	}
	public int getOdGkPoint() {
		return odGkPoint;
	}
	public void setOdGkPoint(int odGkPoint) {
		this.odGkPoint = odGkPoint;
	}
	public double getOdGkPrice() {
		return odGkPrice;
	}
	public void setOdGkPrice(double odGkPrice) {
		this.odGkPrice = odGkPrice;
	}
	public String getOdGkPriceExpression() {
		return odGkPriceExpression;
	}
	public void setOdGkPriceExpression(String odGkPriceExpression) {
		this.odGkPriceExpression = odGkPriceExpression;
	}
	public String getOdGkPrefix() {
		return odGkPrefix;
	}
	public void setOdGkPrefix(String odGkPrefix) {
		this.odGkPrefix = odGkPrefix;
	}
	public Date getOdGkCreateTime() {
		return odGkCreateTime;
	}
	public void setOdGkCreateTime(Date odGkCreateTime) {
		this.odGkCreateTime = odGkCreateTime;
	}
	public Date getOdGkModifyTime() {
		return odGkModifyTime;
	}
	public void setOdGkModifyTime(Date odGkModifyTime) {
		this.odGkModifyTime = odGkModifyTime;
	}

	
}
