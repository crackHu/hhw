package com.vds.app.user.model;

import java.util.Date;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Scope;

@Entity
@Table(name = "tb_transaction")
@Scope(value = "prototype")
public class Transaction implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "tr_id")
	private String trId;//
	@Column(name = "tr_name")
	private String trName;//交易内容
	@Column(name = "tr_num")
	private String trNum;//交易单号
	@Column(name = "tr_price")
	private String trPrice;//金额
	@Column(name = "tr_real_price")
	private String trRealPrice;//最后的金额
	@Column(name = "tr_status")
	private int trStatus;//状态
	@Column(name = "tr_type")
	private int trType;//交易类型
	@Column(name = "tr_way")
	private String trWay;//交易方式
	@Column(name = "tr_status_name")
	private String trStatusName;//状态名称
	@Column(name = "tr_wl_id")
	private String trWlId;//钱包id
	@Column(name = "tr_create_time")
	private Date trCreateTime;//
	@Column(name = "tr_modify_time")
	private Date trModifyTime;//

	public void setTrId(String trId){
		this.trId=trId;
	}

	public String getTrId(){
		return trId;
	}

	public void setTrName(String trName){
		this.trName=trName;
	}

	public String getTrName(){
		return trName;
	}

	public void setTrNum(String trNum){
		this.trNum=trNum;
	}

	public String getTrNum(){
		return trNum;
	}

	public void setTrPrice(String trPrice){
		this.trPrice=trPrice;
	}

	public String getTrPrice(){
		return trPrice;
	}

	public void setTrRealPrice(String trRealPrice){
		this.trRealPrice=trRealPrice;
	}

	public String getTrRealPrice(){
		return trRealPrice;
	}

	public void setTrStatus(int trStatus){
		this.trStatus=trStatus;
	}

	public int getTrStatus(){
		return trStatus;
	}

	public void setTrType(int trType){
		this.trType=trType;
	}

	public int getTrType(){
		return trType;
	}

	public void setTrWay(String trWay){
		this.trWay=trWay;
	}

	public String getTrWay(){
		return trWay;
	}

	public void setTrStatusName(String trStatusName){
		this.trStatusName=trStatusName;
	}

	public String getTrStatusName(){
		return trStatusName;
	}

	public void setTrWlId(String trWlId){
		this.trWlId=trWlId;
	}

	public String getTrWlId(){
		return trWlId;
	}

	public void setTrCreateTime(Date trCreateTime){
		this.trCreateTime=trCreateTime;
	}

	public Date getTrCreateTime(){
		return trCreateTime;
	}

	public void setTrModifyTime(Date trModifyTime){
		this.trModifyTime=trModifyTime;
	}

	public Date getTrModifyTime(){
		return trModifyTime;
	}

}
