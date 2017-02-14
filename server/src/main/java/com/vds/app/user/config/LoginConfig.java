package com.vds.app.user.config;


public class LoginConfig {
	
	//有效时间 10分钟
	private static final long VALIDITYTIME = 10000*60;  
	//登錄方式
	private static final Integer LOGINMETHODS = 0;
	
	private static final String PICVALIDITY = "true";
	
	public static long getValiditytime() {
		return VALIDITYTIME;
	}

	public static Integer getLoginmethods() {
		return LOGINMETHODS;
	}

	public static String getPicvalidity() {
		return PICVALIDITY;
	}

}
