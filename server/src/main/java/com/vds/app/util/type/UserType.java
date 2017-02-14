package com.vds.app.util.type;

public enum UserType {

	ADMIN(2, "管理员"), VIP(0, "会员");
	private Integer code;

	private String message;

	private UserType(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
