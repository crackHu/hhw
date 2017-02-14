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
@Table(name = "tb_other_park_area")
@Scope(value = "prototype")
public class OtherParkArea implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ot_id")
	private String otId;//
	@Column(name = "ot_p_id")
	private String otPId;//父级ID
	@Column(name = "ot_create_date")
	private Date otCreateDate;//创建时间
	@Column(name = "ot_modify_date")
	private Date otModifyDate;//修改时间
	@Column(name = "ot_name")
	private String otName;//地区名称
 
     
 

	public void setOtId(String otId){
		this.otId=otId;
	}

	public String getOtId(){
		return otId;
	}

	public void setOtPId(String otPId){
		this.otPId=otPId;
	}

	public String getOtPId(){
		return otPId;
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

	public void setOtName(String otName){
		this.otName=otName;
	}

	public String getOtName(){
		return otName;
	}

}
