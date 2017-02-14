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
@Table(name = "tb_other_park_image")
@Scope(value = "prototype")
public class OtherParkImage implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ot_id")
	private String otId;//
	@Column(name = "ot_site_id")
	private String otSiteId;//停车场ID
	@Column(name = "ot_create_date")
	private Date otCreateDate;//创建时间
	@Column(name = "ot_modify_date")
	private Date otModifyDate;//修改时间
	@Column(name = "ot_url")
	private String otUrl;//图片链接
	@Column(name = "ot_description")
	private String otDescription;//图片说明
	@Column(name = "ot_primary")
	private int otPrimary;//是否为首图

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

	public void setOtUrl(String otUrl){
		this.otUrl=otUrl;
	}

	public String getOtUrl(){
		return otUrl;
	}

	public void setOtDescription(String otDescription){
		this.otDescription=otDescription;
	}

	public String getOtDescription(){
		return otDescription;
	}

	public void setOtPrimary(int otPrimary){
		this.otPrimary=otPrimary;
	}

	public int getOtPrimary(){
		return otPrimary;
	}

}
