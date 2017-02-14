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
@Table(name = "tb_user_role")
@Scope(value = "prototype")
public class UserRole implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ro_id")
	private String roId;//
	@Column(name = "ro_name")
	private String roName;//权限角色名称
	@Column(name = "ro_code")
	private String roCode;//权限代码code
	@Column(name = "ro_is_remove")
	private int roIsRemove;//是否删除
	@Column(name = "ro_create_time")
	private Date roCreateTime;//
	@Column(name = "ro_modify_time")
	private Date roModifyTime;//

	public void setRoId(String roId){
		this.roId=roId;
	}

	public String getRoId(){
		return roId;
	}

	public void setRoName(String roName){
		this.roName=roName;
	}

	public String getRoName(){
		return roName;
	}

	public void setRoCode(String roCode){
		this.roCode=roCode;
	}

	public String getRoCode(){
		return roCode;
	}

	public void setRoIsRemove(int roIsRemove){
		this.roIsRemove=roIsRemove;
	}

	public int getRoIsRemove(){
		return roIsRemove;
	}

	public void setRoCreateTime(Date roCreateTime){
		this.roCreateTime=roCreateTime;
	}

	public Date getRoCreateTime(){
		return roCreateTime;
	}

	public void setRoModifyTime(Date roModifyTime){
		this.roModifyTime=roModifyTime;
	}

	public Date getRoModifyTime(){
		return roModifyTime;
	}

}
