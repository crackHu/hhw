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
@Table(name = "tb_user_pay_psd")
@Scope(value = "prototype")
public class UserPayPsd implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "pp_id")
	private String ppId;//
	@Column(name = "pp_pay_password")
	private String ppPayPassword;//
	@Column(name = "pp_wl_id")
	private String ppWlId;//
	@Column(name = "pp_create_time")
	private Date ppCreateTime;//
	@Column(name = "pp_modify_time")
	private Date ppModifyTime;//

	public void setPpId(String ppId){
		this.ppId=ppId;
	}

	public String getPpId(){
		return ppId;
	}

	public void setPpPayPassword(String ppPayPassword){
		this.ppPayPassword=ppPayPassword;
	}

	public String getPpPayPassword(){
		return ppPayPassword;
	}

	public void setPpWlId(String ppWlId){
		this.ppWlId=ppWlId;
	}

	public String getPpWlId(){
		return ppWlId;
	}

	public void setPpCreateTime(Date ppCreateTime){
		this.ppCreateTime=ppCreateTime;
	}

	public Date getPpCreateTime(){
		return ppCreateTime;
	}

	public void setPpModifyTime(Date ppModifyTime){
		this.ppModifyTime=ppModifyTime;
	}

	public Date getPpModifyTime(){
		return ppModifyTime;
	}

}
