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
@Table(name = "tb_user_rs")
@Scope(value = "prototype")
public class UserRs implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "urs_id")
	private String ursId;//
	@Column(name = "urs_us_id")
	private String ursUsId;//
	@Column(name = "urs_access")
	private String ursAccess;//
	@Column(name = "urs_type")
	private String ursType;//
	@Column(name = "urs_create_time")
	private Date ursCreateTime;//
	@Column(name = "urs_modify_time")
	private Date ursModifyTime;//

	public void setUrsId(String ursId){
		this.ursId=ursId;
	}

	public String getUrsId(){
		return ursId;
	}

	public void setUrsUsId(String ursUsId){
		this.ursUsId=ursUsId;
	}

	public String getUrsUsId(){
		return ursUsId;
	}

	public void setUrsAccess(String ursAccess){
		this.ursAccess=ursAccess;
	}

	public String getUrsAccess(){
		return ursAccess;
	}

	public void setUrsType(String ursType){
		this.ursType=ursType;
	}

	public String getUrsType(){
		return ursType;
	}

	public void setUrsCreateTime(Date ursCreateTime){
		this.ursCreateTime=ursCreateTime;
	}

	public Date getUrsCreateTime(){
		return ursCreateTime;
	}

	public void setUrsModifyTime(Date ursModifyTime){
		this.ursModifyTime=ursModifyTime;
	}

	public Date getUrsModifyTime(){
		return ursModifyTime;
	}

}
