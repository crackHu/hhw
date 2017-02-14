package com.vds.app.user.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Scope;

import com.vds.app.util.CayUtil;

@Entity
@Table(name = "tb_user_token")
@Scope(value = "prototype")
public class UserToken implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "tk_id")
	private String tkId;//
	@Column(name = "tk_token_content")
	private String tkTokenContent;//
	@Column(name = "tk_us_id")
	private String tkUsId;//
	@Column(name = "tk_expire_time")
	private Date tkExpireTime;//

	public void setTkId(String tkId) {
		this.tkId = tkId;
	}

	public String getTkId() {
		return tkId;
	}

	public void setTkTokenContent(String tkTokenContent) {
		this.tkTokenContent = tkTokenContent;
	}

	public String getTkTokenContent() {
		return tkTokenContent;
	}

	public void setTkUsId(String tkUsId) {
		this.tkUsId = tkUsId;
	}

	public String getTkUsId() {
		return tkUsId;
	}

	public void setTkExpireTime(Date tkExpireTime) {
		this.tkExpireTime = tkExpireTime;
	}

	public Date getTkExpireTime() {
		return tkExpireTime;
	}

	public static UserToken getTokenByUser(User user) {
		UserToken tk = new UserToken();
		tk.setTkUsId(user.getUsId());
		tk.setTkTokenContent(CayUtil.getUUId());
		tk.setTkExpireTime(CayUtil.getTimeByAfter(new Date(), Calendar.MONTH, 1));
		return tk;
	}
}
