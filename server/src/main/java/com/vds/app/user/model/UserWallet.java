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
@Table(name = "tb_user_wallet")
@Scope(value = "prototype")
public class UserWallet implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "wl_id")
	private String wlId;//
	@Column(name = "wl_balance")
	private double wlBalance;//余额
	@Column(name = "wl_us_id")
	private String wlUsId;//关联user id
	@Column(name = "wl_is_remove")
	private int wlIsRemove;//是否 删除
	@Column(name = "wl_create_time")
	private Date wlCreateTime;//
	@Column(name = "wl_modify_time")
	private Date wlModifyTime;//

	public void setWlId(String wlId){
		this.wlId=wlId;
	}

	public String getWlId(){
		return wlId;
	}

	public void setWlBalance(double wlBalance){
		this.wlBalance=wlBalance;
	}

	public double getWlBalance(){
		return wlBalance;
	}

	public void setWlUsId(String wlUsId){
		this.wlUsId=wlUsId;
	}

	public String getWlUsId(){
		return wlUsId;
	}

	public void setWlIsRemove(int wlIsRemove){
		this.wlIsRemove=wlIsRemove;
	}

	public int getWlIsRemove(){
		return wlIsRemove;
	}

	public void setWlCreateTime(Date wlCreateTime){
		this.wlCreateTime=wlCreateTime;
	}

	public Date getWlCreateTime(){
		return wlCreateTime;
	}

	public void setWlModifyTime(Date wlModifyTime){
		this.wlModifyTime=wlModifyTime;
	}

	public Date getWlModifyTime(){
		return wlModifyTime;
	}

}
