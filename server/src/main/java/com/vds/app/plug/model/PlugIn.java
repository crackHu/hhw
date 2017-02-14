package com.vds.app.plug.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Scope;

@Entity
@Table(name = "tb_plug_in")
@Scope(value = "prototype")
public class PlugIn implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "pi_id")
	private String piId;//
	@Column(name = "pi_package")
	private String piPackage;//
	@Column(name = "pi_name")
	private String piName;//
	@Column(name = "pi_status")
	private int piStatus;//
	@Column(name = "pi_msg")
	private String piMsg;//
	@Column(name = "pi_create_time", columnDefinition = "DATE DEFAULT CURRENT_DATE")
	private Date piCreateTime;//
	@Column(name = "pi_modify_time", columnDefinition = "DATE DEFAULT CURRENT_DATE ON UPDATE CURRENT_TIMESTAMP")
	private Date piModifyTime;//

	public void setPiId(String piId) {
		this.piId = piId;
	}

	public String getPiId() {
		return piId;
	}

	public String getPiPackage() {
		return piPackage;
	}

	public void setPiPackage(String piPackage) {
		this.piPackage = piPackage;
	}

	public void setPiName(String piName) {
		this.piName = piName;
	}

	public String getPiName() {
		return piName;
	}

	public void setPiStatus(int piStatus) {
		this.piStatus = piStatus;
	}

	public int getPiStatus() {
		return piStatus;
	}

	public void setPiMsg(String piMsg) {
		this.piMsg = piMsg;
	}

	public String getPiMsg() {
		return piMsg;
	}

	public void setPiCreateTime(Date piCreateTime) {
		this.piCreateTime = piCreateTime;
	}

	public Date getPiCreateTime() {
		return piCreateTime;
	}

	public void setPiModifyTime(Date piModifyTime) {
		this.piModifyTime = piModifyTime;
	}

	public Date getPiModifyTime() {
		return piModifyTime;
	}

	public PlugIn() {
	}

	public PlugIn(String name, String version, Integer piStatus, String piMsg) {
		this.piName = name + "_" + version;
		this.piPackage = name;
		this.piStatus = piStatus;
		this.piMsg = piMsg;
	}
}
