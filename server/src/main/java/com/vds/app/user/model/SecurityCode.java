package com.vds.app.user.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Scope;

import com.vds.app.user.config.LoginConfig;
import com.vds.app.user.util.UserDateUtil;
import com.vds.app.util.CayUtil;
import com.vds.app.util.sms.Sms;

@Entity
@Table(name = "tb_security_code")
@Scope(value = "prototype")
public class SecurityCode implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "sc_id")
	private String scId;//
	@Column(name = "sc_code")
	private String scCode;//
	@Column(name = "sc_phone")
	private String scPhone;//
	@Column(name = "sc_expire_time")
	private Date scExpireTime;//

	public void setScId(String scId){
		this.scId=scId;
	}

	public String getScId(){
		return scId;
	}

	public void setScCode(String scCode){
		this.scCode=scCode;
	}

	public String getScCode(){
		return scCode;
	}

	public void setScPhone(String scPhone){
		this.scPhone=scPhone;
	}

	public String getScPhone(){
		return scPhone;
	}

	public void setScExpireTime(Date scExpireTime){
		this.scExpireTime=scExpireTime;
	}

	public Date getScExpireTime(){
		return scExpireTime;
	}
	
	@Transient
	public static SecurityCode postFwSecurityCode(String phone){
		SecurityCode securityCode = new SecurityCode();
		securityCode.setScPhone(phone);
		securityCode.setScCode(CayUtil.getRandomNumberByLenght(4));
		securityCode.setScExpireTime(UserDateUtil.getExpireTime(LoginConfig.getValiditytime()));
		Sms.send2(phone, securityCode.getScCode());
		return securityCode;
	}

}
