package com.vds.app.user.config;

public class UserMeteStatus {

	
	private static Integer status = 0;
	
	
	private static String message = "初始化中";


	public static Integer getStatus() {
		return status;
	}


	public static void setStatus(Integer status) {
		UserMeteStatus.status = status;
	}


	public static String getMessage() {
		return message;
	}


	public static void setMessage(String message) {
		UserMeteStatus.message = message;
	}
	
	
}