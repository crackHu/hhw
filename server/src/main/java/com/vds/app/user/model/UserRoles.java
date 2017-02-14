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
@Table(name = "tb_user_roles")
@Scope(value = "prototype")
public class UserRoles implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ua_id")
	private String uaId;//
	@Column(name = "ua_us_id")
	private String uaUsId;//用户id
	@Column(name = "ua_ro_id")
	private String uaRoId;//角色id
	@Column(name = "ua_create_time")
	private Date uaCreateTime;//

	public void setUaId(String uaId){
		this.uaId=uaId;
	}

	public String getUaId(){
		return uaId;
	}

	public void setUaUsId(String uaUsId){
		this.uaUsId=uaUsId;
	}

	public String getUaUsId(){
		return uaUsId;
	}

	public void setUaRoId(String uaRoId){
		this.uaRoId=uaRoId;
	}

	public String getUaRoId(){
		return uaRoId;
	}

	public void setUaCreateTime(Date uaCreateTime){
		this.uaCreateTime=uaCreateTime;
	}

	public Date getUaCreateTime(){
		return uaCreateTime;
	}

}
