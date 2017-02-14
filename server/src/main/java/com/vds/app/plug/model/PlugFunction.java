package com.vds.app.plug.model;

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
@Table(name = "tb_plug_function")
@Scope(value = "prototype")
public class PlugFunction implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "pf_id")
	private String pfId;//
	@Column(name = "pf_pi_id")
	private String pfPiId;//
	@Column(name = "pf_name")
	private String pfName;//
	@Column(name = "pf_status")
	private int pfStatus;//
	@Column(name = "pf_msg")
	private String pfMsg;//
	@Column(name = "pf_is_remove")
	private int pfIsRemove;//
	@Column(name = "pf_create_time")
	private Date pfCreateTime;//
	@Column(name = "pf_modify_time")
	private Date pfModifyTime;//

	public void setPfId(String pfId){
		this.pfId=pfId;
	}

	public String getPfId(){
		return pfId;
	}

	public void setPfPiId(String pfPiId){
		this.pfPiId=pfPiId;
	}

	public String getPfPiId(){
		return pfPiId;
	}

	public void setPfName(String pfName){
		this.pfName=pfName;
	}

	public String getPfName(){
		return pfName;
	}

	public void setPfStatus(int pfStatus){
		this.pfStatus=pfStatus;
	}

	public int getPfStatus(){
		return pfStatus;
	}

	public void setPfMsg(String pfMsg){
		this.pfMsg=pfMsg;
	}

	public String getPfMsg(){
		return pfMsg;
	}

	public void setPfIsRemove(int pfIsRemove){
		this.pfIsRemove=pfIsRemove;
	}

	public int getPfIsRemove(){
		return pfIsRemove;
	}

	public void setPfCreateTime(Date pfCreateTime){
		this.pfCreateTime=pfCreateTime;
	}

	public Date getPfCreateTime(){
		return pfCreateTime;
	}

	public void setPfModifyTime(Date pfModifyTime){
		this.pfModifyTime=pfModifyTime;
	}

	public Date getPfModifyTime(){
		return pfModifyTime;
	}

}
