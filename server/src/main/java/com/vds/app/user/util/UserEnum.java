package com.vds.app.user.util;

import com.vds.app.exception.MyException;

public enum UserEnum {
	UN("1", "普通会员", "UN"),TCADMIN("2","停车场管理员","TCADMIN"), ADMIN("3", "管理员", "ADMIN"), SUPER("4", "超级管理员", "SUPER");
	private String code;

	private String name;

	private String value;

	UserEnum(String code, String name, String value) {
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

	public static UserEnum getCodeByValue(String value) throws MyException {
		UserEnum userEnum[] = UserEnum.values();
		for (int i = 0; i < userEnum.length; i++) {
			if (value.equalsIgnoreCase(userEnum[i].getValue())) {
				return userEnum[i];
			}
		}
		throw new MyException("9999","错误的用户类型");

	}
}
