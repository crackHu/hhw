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
@Table(name = "tb_user_message")
@Scope(value = "prototype")
public class UserMessage implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "um_id")
	private String umId;//
	@Column(name = "um_us_id")
	private String umUsId;//
	@Column(name = "um_content")
	private String umContent;//
	@Column(name = "um_type")
	private String umType;//
	@Column(name = "um_create_time")
	private Date umCreateTime;//

	public void setUmId(String umId){
		this.umId=umId;
	}

	public String getUmId(){
		return umId;
	}

	public void setUmUsId(String umUsId){
		this.umUsId=umUsId;
	}

	public String getUmUsId(){
		return umUsId;
	}

	public void setUmContent(String umContent){
		this.umContent=umContent;
	}

	public String getUmContent(){
		return umContent;
	}

	public void setUmType(String umType){
		this.umType=umType;
	}

	public String getUmType(){
		return umType;
	}

	public void setUmCreateTime(Date umCreateTime){
		this.umCreateTime=umCreateTime;
	}

	public Date getUmCreateTime(){
		return umCreateTime;
	}

}
