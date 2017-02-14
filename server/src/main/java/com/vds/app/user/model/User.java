package com.vds.app.user.model;

import java.io.Serializable;
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

import com.vds.app.exception.MyException;
import com.vds.app.other.model.OtherCar;
import com.vds.app.user.util.LoginEnum;
import com.vds.app.user.util.UserEnum;

@Entity
@Table(name = "tb_user")
@Scope(value = "prototype")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "us_id")
	private String usId;//
	@Column(name = "us_name")
	private String usName;// 昵称
	@Column(name = "us_user_name")
	private String usUserName;// 用户名
	@Column(name = "us_phone")
	private String usPhone;// 电话
	@Column(name = "us_password")
	private String usPassword;// 密码
	@Column(name = "us_type")
	private String usType;// 类型
	@Column(name = "us_pic")
	private String usPic;
	@Column(name = "us_is_remove")
	private int usIsRemove;// 是否删除
	@Column(name = "us_create_time", columnDefinition = "DATE DEFAULT CURRENT_DATE")
	private Date usCreateTime;// 创建时间
	@Column(name = "us_modify_time", columnDefinition = "DATE DEFAULT CURRENT_DATE ON UPDATE CURRENT_TIMESTAMP")
	private Date usModifyTime;// 修改时间

	// 以下字段不做保存到数据库
	@Transient
	private List<OtherCar> cars;

	public List<OtherCar> getCars() {
		return cars;
	}

	public void setCars(List<OtherCar> cars) {
		this.cars = cars;
	}

	public void setUsId(String usId) {
		this.usId = usId;
	}

	public String getUsId() {
		return usId;
	}

	public void setUsName(String usName) {
		this.usName = usName;
	}

	public String getUsName() {
		return usName;
	}

	public void setUsUserName(String usUserName) {
		this.usUserName = usUserName;
	}

	public String getUsUserName() {
		return usUserName;
	}

	public void setUsPassword(String usPassword) {
		this.usPassword = usPassword;
	}

	public String getUsPassword() {
		return usPassword;
	}

	public void setUsType(String usType) {
		this.usType = usType;
	}

	public String getUsType() {
		return usType;
	}

	public void setUsIsRemove(int usIsRemove) {
		this.usIsRemove = usIsRemove;
	}

	public int getUsIsRemove() {
		return usIsRemove;
	}

	public void setUsCreateTime(Date usCreateTime) {
		this.usCreateTime = usCreateTime;
	}

	public Date getUsCreateTime() {
		return usCreateTime;
	}

	public void setUsModifyTime(Date usModifyTime) {
		this.usModifyTime = usModifyTime;
	}

	public Date getUsModifyTime() {
		return usModifyTime;
	}

	public String getUsPhone() {
		return usPhone;
	}

	public void setUsPhone(String usPhone) {
		this.usPhone = usPhone;
	}

	public String getUsPic() {
		return usPic;
	}

	public void setUsPic(String usPic) {
		this.usPic = usPic;
	}

	public static User getRegisterUser(String loginName, String password, String userType, String loginType)
			throws MyException {
		User u = new User();
		u.setUsIsRemove(0);
		// 暂时是手机号码
		if (loginType.equalsIgnoreCase(LoginEnum.PHONE.getValue())) {
			u.setUsPhone(loginName);
			u.setUsUserName(loginName);
		} else if (loginType.equalsIgnoreCase(LoginEnum.NAME.getValue())) {
			u.setUsUserName(loginName);
		}

		u.setUsPassword(password);
		u.setUsName("未设置");
		u.setUsType(UserEnum.getCodeByValue(userType).getCode());

		return u;
	}

	public static User getTheThirdRegister(String openId, String nikeName, String usType, String pic)
			throws MyException {
		User u = new User();
		u.setUsUserName(openId);
		u.setUsName(nikeName);
		u.setUsType(UserEnum.getCodeByValue(usType).getCode());
		u.setUsPic(pic);
		u.setUsIsRemove(0);
		return u;
	}

}
