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
@Table(name = "tb_user_resources")
@Scope(value = "prototype")
public class UserResources implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "rs_id")
	private String rsId;//
	@Column(name = "rs_name")
	private String rsName;//
	@Column(name = "rs_url")
	private String rsUrl;//
	@Column(name = "rs_is_remove")
	private int rsIsRemove;//
	@Column(name = "rs_create_time")
	private Date rsCreateTime;//
	@Column(name = "rs_modify_time")
	private Date rsModifyTime;//

	public void setRsId(String rsId){
		this.rsId=rsId;
	}

	public String getRsId(){
		return rsId;
	}

	public void setRsName(String rsName){
		this.rsName=rsName;
	}

	public String getRsName(){
		return rsName;
	}

	public void setRsUrl(String rsUrl){
		this.rsUrl=rsUrl;
	}

	public String getRsUrl(){
		return rsUrl;
	}

	public void setRsIsRemove(int rsIsRemove){
		this.rsIsRemove=rsIsRemove;
	}

	public int getRsIsRemove(){
		return rsIsRemove;
	}

	public void setRsCreateTime(Date rsCreateTime){
		this.rsCreateTime=rsCreateTime;
	}

	public Date getRsCreateTime(){
		return rsCreateTime;
	}

	public void setRsModifyTime(Date rsModifyTime){
		this.rsModifyTime=rsModifyTime;
	}

	public Date getRsModifyTime(){
		return rsModifyTime;
	}

}
