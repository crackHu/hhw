package com.vds.app.user.util;

import com.vds.app.exception.MyException;

public enum LoginEnum {
	
	PHONE("1", "手机登录", "PHONE"), EMAIL("2", "E_mail登录", "EMAIL"), NAME("3", "用户名登录", "NAME")
	, QQ("4", "QQ第三方登陆","QQ"), WECHAT("5", "微信第三方登陆","WECHAT");
	private String code;

	private String name;

	private String value;

	LoginEnum(String code, String name, String value) {
		this.code = code;
		this.name = name;
		this.value = value;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LoginEnum getCodeByValue(String value) throws MyException {
		LoginEnum loginEnum[] = LoginEnum.values();
		for (int i = 0; i < loginEnum.length; i++) {
			if (value.equalsIgnoreCase(loginEnum[i].getValue())) {
				return loginEnum[i];
			}
		}
		throw new MyException("");
	}
	
	
	
	public static boolean isOtherLogin(String loginType) {
		if (loginType.equals(QQ.value) || loginType.equals(WECHAT.value)) {
			return true;
		}
		return false;
	}
	
	public static boolean isDefaultLogin(String loginType){
		if (loginType.equals(PHONE.value) || loginType.equals(NAME.value)) {
			return true;
		}
		return false;
	}

	public static boolean isAllLogin(String loginType) {
		if (isDefaultLogin(loginType) || isOtherLogin(loginType)) {
			return true;
		}
		return false;
	}

	
}
