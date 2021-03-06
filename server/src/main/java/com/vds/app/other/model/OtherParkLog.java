package com.vds.app.other.model;

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
@Table(name = "tb_other_park_log")
@Scope(value = "prototype")
public class OtherParkLog implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ot_id")
	private String otId;//
	@Column(name = "ot_user_id")
	private String otUserId;//操作员
	@Column(name = "ot_create_date")
	private Date otCreateDate;//创建时间
	@Column(name = "ot_modify_date")
	private Date otModifyDate;//修改时间
	@Column(name = "ot_content")
	private String otContent;//内容
	@Column(name = "ot_type")
	private int otType;//类型

	public void setOtId(String otId){
		this.otId=otId;
	}

	public String getOtId(){
		return otId;
	}

	public void setOtUserId(String otUserId){
		this.otUserId=otUserId;
	}

	public String getOtUserId(){
		return otUserId;
	}

	public void setOtCreateDate(Date otCreateDate){
		this.otCreateDate=otCreateDate;
	}

	public Date getOtCreateDate(){
		return otCreateDate;
	}

	public void setOtModifyDate(Date otModifyDate){
		this.otModifyDate=otModifyDate;
	}

	public Date getOtModifyDate(){
		return otModifyDate;
	}

	public void setOtContent(String otContent){
		this.otContent=otContent;
	}

	public String getOtContent(){
		return otContent;
	}

	public void setOtType(int otType){
		this.otType=otType;
	}

	public int getOtType(){
		return otType;
	}

}
