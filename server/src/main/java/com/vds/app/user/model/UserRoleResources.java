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
@Table(name = "tb_user_role_resources")
@Scope(value = "prototype")
public class UserRoleResources implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ur_id")
	private String urId;//
	@Column(name = "ur_ro_id")
	private String urRoId;//角色id
	@Column(name = "ur_rs_id")
	private String urRsId;//资源id
	@Column(name = "ur_ro_name")
	private String urRoName;//角色的名称
	@Column(name = "ur_rs_name")
	private String urRsName;//对应资源的名称
	@Column(name = "ur_rs_url")
	private String urRsUrl;//资源的url
	@Column(name = "ur_is_remove")
	private String urIsRemove;//
	@Column(name = "ur_create_time")
	private Date urCreateTime;//
	@Column(name = "ur_modify_time")
	private Date urModifyTime;//

	public void setUrId(String urId){
		this.urId=urId;
	}

	public String getUrId(){
		return urId;
	}

	public void setUrRoId(String urRoId){
		this.urRoId=urRoId;
	}

	public String getUrRoId(){
		return urRoId;
	}

	public void setUrRsId(String urRsId){
		this.urRsId=urRsId;
	}

	public String getUrRsId(){
		return urRsId;
	}

	public void setUrRoName(String urRoName){
		this.urRoName=urRoName;
	}

	public String getUrRoName(){
		return urRoName;
	}

	public void setUrRsName(String urRsName){
		this.urRsName=urRsName;
	}

	public String getUrRsName(){
		return urRsName;
	}

	public void setUrRsUrl(String urRsUrl){
		this.urRsUrl=urRsUrl;
	}

	public String getUrRsUrl(){
		return urRsUrl;
	}

	public void setUrIsRemove(String urIsRemove){
		this.urIsRemove=urIsRemove;
	}

	public String getUrIsRemove(){
		return urIsRemove;
	}

	public void setUrCreateTime(Date urCreateTime){
		this.urCreateTime=urCreateTime;
	}

	public Date getUrCreateTime(){
		return urCreateTime;
	}

	public void setUrModifyTime(Date urModifyTime){
		this.urModifyTime=urModifyTime;
	}

	public Date getUrModifyTime(){
		return urModifyTime;
	}

}
