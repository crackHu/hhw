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
@Table(name = "tb_other_park_res")
@Scope(value = "prototype")
public class OtherParkRes implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ot_id")
	private String otId;//
	@Column(name = "ot_site_id")
	private String otSiteId;//停车场ID
	@Column(name = "ot_type")
	private String otType;//类型，1：露天 2：地下 3：多层
	@Column(name = "ot_create_date")
	private Date otCreateDate;//创建时间
	@Column(name = "ot_modify_date")
	private Date otModifyDate;//修改时间
	@Column(name = "ot_count")
	private long otCount;//车位数量

	public void setOtId(String otId){
		this.otId=otId;
	}

	public String getOtId(){
		return otId;
	}

	public void setOtSiteId(String otSiteId){
		this.otSiteId=otSiteId;
	}

	public String getOtSiteId(){
		return otSiteId;
	}

	public void setOtType(String otType){
		this.otType=otType;
	}

	public String getOtType(){
		return otType;
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

	public void setOtCount(long otCount){
		this.otCount=otCount;
	}

	public long getOtCount(){
		return otCount;
	}

}
