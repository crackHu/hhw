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
@Table(name = "tb_order_payment")
@Scope(value = "prototype")
public class OrderPayment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "od_pay_id")
	private String odPayId;//
	@Column(name = "od_pay_us_id")
	private String odPayUsId;//
	@Column(name = "od_pay_od_id")
	private String odPayOdId;//
	@Column(name = "od_pay_account")
	private String odPayAccount;//
	@Column(name = "od_pay_amount")
	private String odPayAmount;//
	@Column(name = "od_pay_bank")
	private String odPayBank;//
	@Column(name = "od_pay_expire_time")
	private Date odPayExpireTime;//
	@Column(name = "od_pay_fee")
	private String odPayFee;//
	@Column(name = "od_pay_memo")
	private String odPayMemo;//
	@Column(name = "od_pay_method")
	private String odPayMethod;//
	@Column(name = "od_pay_time")
	private Date odPayTime;//
	@Column(name = "od_pay_sno")
	private String odPaySno;//
	@Column(name = "od_pay_status")
	private int odPayStatus;//
	@Column(name = "od_pay_create_time")
	private Date odPayCreateTime;//
	@Column(name = "od_pay_modify_time")
	private Date odPayModifyTime;//

	public String getOdPayId() {
		return odPayId;
	}

	public void setOdPayId(String odPayId) {
		this.odPayId = odPayId;
	}

	public String getOdPayUsId() {
		return odPayUsId;
	}

	public void setOdPayUsId(String odPayUsId) {
		this.odPayUsId = odPayUsId;
	}

	public String getOdPayOdId() {
		return odPayOdId;
	}

	public void setOdPayOdId(String odPayOdId) {
		this.odPayOdId = odPayOdId;
	}

	public String getOdPayAccount() {
		return odPayAccount;
	}

	public void setOdPayAccount(String odPayAccount) {
		this.odPayAccount = odPayAccount;
	}

	public String getOdPayAmount() {
		return odPayAmount;
	}

	public void setOdPayAmount(String odPayAmount) {
		this.odPayAmount = odPayAmount;
	}

	public String getOdPayBank() {
		return odPayBank;
	}

	public void setOdPayBank(String odPayBank) {
		this.odPayBank = odPayBank;
	}

	public Date getOdPayExpireTime() {
		return odPayExpireTime;
	}

	public void setOdPayExpireTime(Date odPayExpireTime) {
		this.odPayExpireTime = odPayExpireTime;
	}

	public String getOdPayFee() {
		return odPayFee;
	}

	public void setOdPayFee(String odPayFee) {
		this.odPayFee = odPayFee;
	}

	public String getOdPayMemo() {
		return odPayMemo;
	}

	public void setOdPayMemo(String odPayMemo) {
		this.odPayMemo = odPayMemo;
	}

	public String getOdPayMethod() {
		return odPayMethod;
	}

	public void setOdPayMethod(String odPayMethod) {
		this.odPayMethod = odPayMethod;
	}

	public Date getOdPayTime() {
		return odPayTime;
	}

	public void setOdPayTime(Date odPayTime) {
		this.odPayTime = odPayTime;
	}

	public String getOdPaySno() {
		return odPaySno;
	}

	public void setOdPaySno(String odPaySno) {
		this.odPaySno = odPaySno;
	}

	public int getOdPayStatus() {
		return odPayStatus;
	}

	public void setOdPayStatus(int odPayStatus) {
		this.odPayStatus = odPayStatus;
	}

	public Date getOdPayCreateTime() {
		return odPayCreateTime;
	}

	public void setOdPayCreateTime(Date odPayCreateTime) {
		this.odPayCreateTime = odPayCreateTime;
	}

	public Date getOdPayModifyTime() {
		return odPayModifyTime;
	}

	public void setOdPayModifyTime(Date odPayModifyTime) {
		this.odPayModifyTime = odPayModifyTime;
	}
	
	public OrderPayment(){
		
	}
	
	
	public OrderPayment(String method,String odPayUsId,String odPayAccount,String odPayOdId,String odPayAmount){
		this.odPayMethod=method;
		this.odPayAccount=odPayAccount;
		this.odPayUsId=odPayUsId;
		this.odPayOdId=odPayOdId;
		this.odPayAmount=odPayAmount;
	}

}
