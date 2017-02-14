package com.vds.app.user.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Scope;

import com.vds.app.exception.MyException;
import com.vds.app.user.util.UserEnum;

@Entity
@Table(name = "tb_user_other")
@Scope(value = "prototype")
public class UserOther implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "uo_id")
	private String uoId;//
	@Column(name = "uo_us_id")
	private String uoUsId;//
	@Column(name = "uo_type")
	private String uoType;// QQ 还是微信
	@Column(name = "uo_open_id")
	private String uoOpenId;// 第三方的accessToken
	@Column(name = "uo_account")
	private String uoAccount;// 第三方的账号

	public UserOther() {

	}

	public UserOther(String usId, String openId, String uType, String account) throws MyException {
		this.uoUsId = usId;
		this.uoOpenId = openId;
		this.uoType = UserEnum.getCodeByValue(uType).getCode();
		this.uoAccount = account;

	}

	public void setUoId(String uoId) {
		this.uoId = uoId;
	}

	public String getUoId() {
		return uoId;
	}

	public void setUoUsId(String uoUsId) {
		this.uoUsId = uoUsId;
	}

	public String getUoUsId() {
		return uoUsId;
	}

	public void setUoType(String uoType) {
		this.uoType = uoType;
	}

	public String getUoType() {
		return uoType;
	}

	public void setUoOpenId(String uoOpenId) {
		this.uoOpenId = uoOpenId;
	}

	public String getUoOpenId() {
		return uoOpenId;
	}

	public void setUoAccount(String uoAccount) {
		this.uoAccount = uoAccount;
	}

	public String getUoAccount() {
		return uoAccount;
	}

}
