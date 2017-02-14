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
@Table(name = "tb_other_car")
@Scope(value = "prototype")
public class OtherCar implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ot_car_id")
	private String otCarId;//
	@Column(name = "ot_car_us_id")
	private String otCarUsId;//用户id
	@Column(name = "ot_car_province")
	private String otCarProvince;//车牌省份-'粤'
	@Column(name = "ot_car_city")
	private String otCarCity;//车牌城市-'A'
	@Column(name = "ot_car_num")
	private String otCarNum;//车牌号-'88888'
	@Column(name = "ot_create_time")
	private Date otCreateTime;//创建时间
	@Column(name = "ot_modify_time")
	private Date otModifyTime;//修改时间

	public void setOtCarId(String otCarId){
		this.otCarId=otCarId;
	}

	public String getOtCarId(){
		return otCarId;
	}

	public void setOtCarUsId(String otCarUsId){
		this.otCarUsId=otCarUsId;
	}

	public String getOtCarUsId(){
		return otCarUsId;
	}

	public void setOtCarProvince(String otCarProvince){
		this.otCarProvince=otCarProvince;
	}

	public String getOtCarProvince(){
		return otCarProvince;
	}

	public void setOtCarCity(String otCarCity){
		this.otCarCity=otCarCity;
	}

	public String getOtCarCity(){
		return otCarCity;
	}

	public void setOtCarNum(String otCarNum){
		this.otCarNum=otCarNum;
	}

	public String getOtCarNum(){
		return otCarNum;
	}

	public void setOtCreateTime(Date otCreateTime){
		this.otCreateTime=otCreateTime;
	}

	public Date getOtCreateTime(){
		return otCreateTime;
	}

	public void setOtModifyTime(Date otModifyTime){
		this.otModifyTime=otModifyTime;
	}

	public Date getOtModifyTime(){
		return otModifyTime;
	}

}
