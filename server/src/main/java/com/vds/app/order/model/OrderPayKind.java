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
@Table(name = "tb_order_pay_kind")
@Scope(value = "prototype")
public class OrderPayKind implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "od_pk_id")
	private String orPkId;//
	@Column(name = "od_pk_name")
	private String orPkName;//
	@Column(name = "od_pk_code")
	private String orPkCode;//
	@Column(name = "od_pk_order")
	private int orPkOrder;//
	@Column(name = "od_pk_desc")
	private String orPkDesc;//
	@Column(name = "od_pk_icon")
	private String orPkIcon;//
	@Column(name = "od_pk_method")
	private String orPkMethod;//
	@Column(name = "od_pk_timeout")
	private Date orPkTimeout;//
	@Column(name = "od_pk_content")
	private String orPkContent;//
	@Column(name = "od_create_time")
	private Date orCreateTime;//
	@Column(name = "od_modify_time")
	private Date orModifyTime;//

	public String getOrPkId() {
		return orPkId;
	}

	public void setOrPkId(String orPkId) {
		this.orPkId = orPkId;
	}

	public String getOrPkName() {
		return orPkName;
	}

	public void setOrPkName(String orPkName) {
		this.orPkName = orPkName;
	}

	public String getOrPkCode() {
		return orPkCode;
	}

	public void setOrPkCode(String orPkCode) {
		this.orPkCode = orPkCode;
	}

	public int getOrPkOrder() {
		return orPkOrder;
	}

	public void setOrPkOrder(int orPkOrder) {
		this.orPkOrder = orPkOrder;
	}

	public String getOrPkDesc() {
		return orPkDesc;
	}

	public void setOrPkDesc(String orPkDesc) {
		this.orPkDesc = orPkDesc;
	}

	public String getOrPkIcon() {
		return orPkIcon;
	}

	public void setOrPkIcon(String orPkIcon) {
		this.orPkIcon = orPkIcon;
	}

	public String getOrPkMethod() {
		return orPkMethod;
	}

	public void setOrPkMethod(String orPkMethod) {
		this.orPkMethod = orPkMethod;
	}

	public Date getOrPkTimeout() {
		return orPkTimeout;
	}

	public void setOrPkTimeout(Date orPkTimeout) {
		this.orPkTimeout = orPkTimeout;
	}

	public String getOrPkContent() {
		return orPkContent;
	}

	public void setOrPkContent(String orPkContent) {
		this.orPkContent = orPkContent;
	}

	public Date getOrCreateTime() {
		return orCreateTime;
	}

	public void setOrCreateTime(Date orCreateTime) {
		this.orCreateTime = orCreateTime;
	}

	public Date getOrModifyTime() {
		return orModifyTime;
	}

	public void setOrModifyTime(Date orModifyTime) {
		this.orModifyTime = orModifyTime;
	}

}
