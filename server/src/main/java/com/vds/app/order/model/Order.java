package com.vds.app.order.model;

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

import com.vds.app.other.model.OtherParkSite;
import com.vds.app.util.RandomNumUtil;

@Entity
@Table(name = "tb_order")
@Scope(value = "prototype")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "od_id")
	private String odId;//
	@Column(name = "od_barcode")
	private String odBarcode;//
	@Column(name = "od_us_id")
	private String odUsId;//
	@Column(name = "od_status")
	private int odStatus;//
	@Column(name = "od_park_site_id")
	private String odParkSiteId;//
	@Column(name = "od_park_type")
	private String odParkType;//
	@Column(name = "od_start_time")
	private Date odStartTime;// 开始停车时间
	@Column(name = "od_end_time")
	private Date odEndTime;// 停止停车的时间
	@Column(name = "od_car_id")
	private String odCarId;// 用户车辆
	@Column(name = "od_car_num")
	private String odCarNum;// 用户车辆
	@Column(name = "od_first_price")
	private BigDecimal odFirstPrice;// 开始支付金额
	@Column(name = "od_add_price")
	private BigDecimal odAddPrice;// 累计支付金额
	@Column(name = "od_operator_id")
	private String odOperatorId;// 操作人id
	@Column(name = "od_is_invoice")
	private int odIsInvoice;// 是否开发票
	@Column(name = "od_invoice_title")
	private String odInvoiceTitle;// 发票抬头
	@Column(name = "od_is_privilege")
	private int odIsPrivilege;// 是否哟特权
	@Column(name = "od_create_time")
	private Date odCreateTime;// 创建时间
	@Column(name = "od_modify_time")
	private Date odModifyTime;// 修改时间

	/**
	 * 
	 * ============= 以下字段不保存到数据库 ==========
	 */
	@Transient
	private OtherParkSite parkSite;

	public Order() {
	}

	public OtherParkSite getParkSite() {
		return parkSite;
	}

	public void setParkSite(OtherParkSite parkSite) {
		this.parkSite = parkSite;
	}

	public Order(String usId, String parkSiteId, String catalog, String odCarNum,String odCarId) {
		this.odUsId = usId;
		this.odParkSiteId = parkSiteId;
		this.odCarNum = odCarNum;
		this.odParkType = catalog;
		this.odBarcode = RandomNumUtil.getRandom20();
		this.odCarId = odCarId;
	}

	@Transient
	private List<OrderPayment> orderPaymentList;

	public List<OrderPayment> getOrderPaymentList() {
		return orderPaymentList;
	}

	public void setOrderPaymentList(List<OrderPayment> orderPaymentList) {
		this.orderPaymentList = orderPaymentList;
	}

	public String getOdId() {
		return odId;
	}

	public void setOdId(String odId) {
		this.odId = odId;
	}

	public String getOdBarcode() {
		return odBarcode;
	}

	public void setOdBarcode(String odBarcode) {
		this.odBarcode = odBarcode;
	}

	public String getOdUsId() {
		return odUsId;
	}

	public void setOdUsId(String odUsId) {
		this.odUsId = odUsId;
	}

	public int getOdStatus() {
		return odStatus;
	}

	public void setOdStatus(int odStatus) {
		this.odStatus = odStatus;
	}

	public String getOdParkSiteId() {
		return odParkSiteId;
	}

	public void setOdParkSiteId(String odParkSiteId) {
		this.odParkSiteId = odParkSiteId;
	}

	public String getOdParkType() {
		return odParkType;
	}

	public void setOdParkType(String odParkType) {
		this.odParkType = odParkType;
	}

	public Date getOdStartTime() {
		return odStartTime;
	}

	public void setOdStartTime(Date odStartTime) {
		this.odStartTime = odStartTime;
	}

	public Date getOdEndTime() {
		return odEndTime;
	}

	public void setOdEndTime(Date odEndTime) {
		this.odEndTime = odEndTime;
	}

	public String getOdCarNum() {
		return odCarNum;
	}

	public void setOdCarNum(String odCarNum) {
		this.odCarNum = odCarNum;
	}

	public BigDecimal getOdFirstPrice() {
		return odFirstPrice;
	}

	public void setOdFirstPrice(BigDecimal odFirstPrice) {
		this.odFirstPrice = odFirstPrice;
	}

	public BigDecimal getOdAddPrice() {
		return odAddPrice;
	}

	public void setOdAddPrice(BigDecimal odAddPrice) {
		this.odAddPrice = odAddPrice;
	}

	public String getOdOperatorId() {
		return odOperatorId;
	}

	public void setOdOperatorId(String odOperatorId) {
		this.odOperatorId = odOperatorId;
	}

	public int getOdIsInvoice() {
		return odIsInvoice;
	}

	public void setOdIsInvoice(int odIsInvoice) {
		this.odIsInvoice = odIsInvoice;
	}

	public String getOdInvoiceTitle() {
		return odInvoiceTitle;
	}

	public void setOdInvoiceTitle(String odInvoiceTitle) {
		this.odInvoiceTitle = odInvoiceTitle;
	}

	public int getOdIsPrivilege() {
		return odIsPrivilege;
	}

	public void setOdIsPrivilege(int odIsPrivilege) {
		this.odIsPrivilege = odIsPrivilege;
	}

	public Date getOdCreateTime() {
		return odCreateTime;
	}

	public void setOdCreateTime(Date odCreateTime) {
		this.odCreateTime = odCreateTime;
	}

	public Date getOdModifyTime() {
		return odModifyTime;
	}

	public void setOdModifyTime(Date odModifyTime) {
		this.odModifyTime = odModifyTime;
	}

	public String getOdCarId() {
		return odCarId;
	}

	public void setOdCarId(String odCarId) {
		this.odCarId = odCarId;
	}

}
