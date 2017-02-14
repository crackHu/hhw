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
@Table(name = "tb_user_type")
@Scope(value = "prototype")
public class UserType implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ut_id")
	private String utId;//
	@Column(name = "ut_name")
	private String utName;//
	@Column(name = "ut_is_remove")
	private int utIsRemove;//
	@Column(name = "ut_create_time",columnDefinition = "DATE DEFAULT CURRENT_DATE")
	private Date utCreateTime;//
	@Column(name = "ut_modify_time",columnDefinition = "DATE DEFAULT CURRENT_DATE ON UPDATE CURRENT_TIMESTAMP")
	private Date utModifyTime;//

	public void setUtId(String utId){
		this.utId=utId;
	}

	public String getUtId(){
		return utId;
	}

	public void setUtName(String utName){
		this.utName=utName;
	}

	public String getUtName(){
		return utName;
	}

	public void setUtIsRemove(int utIsRemove){
		this.utIsRemove=utIsRemove;
	}

	public int getUtIsRemove(){
		return utIsRemove;
	}

	public void setUtCreateTime(Date utCreateTime){
		this.utCreateTime=utCreateTime;
	}

	public Date getUtCreateTime(){
		return utCreateTime;
	}

	public void setUtModifyTime(Date utModifyTime){
		this.utModifyTime=utModifyTime;
	}

	public Date getUtModifyTime(){
		return utModifyTime;
	}

}
