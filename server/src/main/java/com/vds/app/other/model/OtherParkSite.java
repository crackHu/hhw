package com.vds.app.other.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Scope;

@Entity
@Table(name = "tb_other_park_site")
@Scope(value = "prototype")
public class OtherParkSite implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ot_id")
	private String otId;//
	@Column(name = "ot_create_date")
	private Date otCreateDate;// 创建时间
	@Column(name = "ot_modify_date")
	private Date otModifyDate;// 修改时间
	@Column(name = "ot_name")
	private String otName;// 停车场名称
	@Column(name = "ot_description")
	private String otDescription;// 描述
	@Column(name = "ot_address")
	private String otAddress;// 地址
	@Column(name = "ot_is_enabled")
	private boolean otIsEnabled;// 是否启用
	@Column(name = "ot_area")
	private String otArea;// 地区
	@Column(name = "ot_point_x")
	private double otPointX;// 百度坐标X
	@Column(name = "ot_point_y")
	private double otPointY;// 百度坐标Y
	@Column(name = "ot_release_res")
	private long otReleaseRes;// 可供使用的车位数量
	@Column(name = "ot_free_res")
	private long otFreeRes;// 当前空闲车位数量
	@Column(name = "ot_price")
	private BigDecimal otPrice;//默认的价格
	
	/**
	 * 
	 * ============= 以下字段不保存到数据库 ==========
	 */
	@Transient
	private List<OtherParkImage> images;

	public BigDecimal getOtPrice() {
		return otPrice;
	}

	public void setOtPrice(BigDecimal otPrice) {
		this.otPrice = otPrice;
	}

	public List<OtherParkImage> getImages() {
		return images;
	}

	public void setImages(List<OtherParkImage> images) {
		this.images = images;
	}

	public void setOtId(String otId) {
		this.otId = otId;
	}

	public String getOtId() {
		return otId;
	}

	public void setOtCreateDate(Date otCreateDate) {
		this.otCreateDate = otCreateDate;
	}

	public Date getOtCreateDate() {
		return otCreateDate;
	}

	public void setOtModifyDate(Date otModifyDate) {
		this.otModifyDate = otModifyDate;
	}

	public Date getOtModifyDate() {
		return otModifyDate;
	}

	public void setOtName(String otName) {
		this.otName = otName;
	}

	public String getOtName() {
		return otName;
	}

	public void setOtDescription(String otDescription) {
		this.otDescription = otDescription;
	}

	public String getOtDescription() {
		return otDescription;
	}

	public void setOtAddress(String otAddress) {
		this.otAddress = otAddress;
	}

	public String getOtAddress() {
		return otAddress;
	}

	public boolean isOtIsEnabled() {
		return otIsEnabled;
	}

	public void setOtIsEnabled(boolean otIsEnabled) {
		this.otIsEnabled = otIsEnabled;
	}

	public void setOtArea(String otArea) {
		this.otArea = otArea;
	}

	public String getOtArea() {
		return otArea;
	}

	public void setOtPointX(double otPointX) {
		this.otPointX = otPointX;
	}

	public double getOtPointX() {
		return otPointX;
	}

	public void setOtPointY(double otPointY) {
		this.otPointY = otPointY;
	}

	public double getOtPointY() {
		return otPointY;
	}

	public void setOtReleaseRes(long otReleaseRes) {
		this.otReleaseRes = otReleaseRes;
	}

	public long getOtReleaseRes() {
		return otReleaseRes;
	}

	public void setOtFreeRes(long otFreeRes) {
		this.otFreeRes = otFreeRes;
	}

	public long getOtFreeRes() {
		return otFreeRes;
	}

}
