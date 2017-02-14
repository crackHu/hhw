package com.vds.app.other.model;

import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Scope;

@Entity
@Table(name = "tb_other_park_price_rule")
@Scope(value = "prototype")
public class OtherParkPriceRule implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ot_id")
	private String otId;//
	@Column(name = "ot_site_id")
	private String otSiteId;// 停车场ID
	@Column(name = "ot_catalog_id")
	private String otCatalogId;// 车位类型ID
	@Column(name = "ot_create_date")
	private Date otCreateDate;// 创建日期
	@Column(name = "ot_modify_date")
	private Date otModifyDate;// 修改日期
	@Column(name = "ot_price")
	private BigDecimal otPrice;// 价格（每小时）

	public void setOtId(String otId) {
		this.otId = otId;
	}

	public String getOtId() {
		return otId;
	}

	public void setOtSiteId(String otSiteId) {
		this.otSiteId = otSiteId;
	}

	public String getOtSiteId() {
		return otSiteId;
	}

	public void setOtCatalogId(String otCatalogId) {
		this.otCatalogId = otCatalogId;
	}

	public String getOtCatalogId() {
		return otCatalogId;
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

	public BigDecimal getOtPrice() {
		return otPrice;
	}

	public void setOtPrice(BigDecimal otPrice) {
		this.otPrice = otPrice;
	}

}
