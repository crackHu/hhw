package com.vds.app.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(locations = "classpath:application.properties",prefix="config")
public class Config {

	@Value("${config.isReal}")
	public static String isReal;

	@Value("${config.proName}")
	public static String proName;

	@Value("${config.proUrl}")
	public static String proUrl;

	@Value("${config.fileSavePath}")
	public static String fileSavePath;

	@Value("${config.fileRootUrl}")
	public static String fileRootUrl;
	
	@Value("${config.tokentimeout}")
	public static String tokentimeout;
	
	@Value("${config.ordertimeout}")
	public static String ordertimeout;
	
	public static String getOrdertimeout() {
		return ordertimeout;
	}

	public static void setOrdertimeout(String ordertimeout) {
		Config.ordertimeout = ordertimeout;
	}

	public static String getIsReal() {
		return isReal;
	}

	public static void setIsReal(String isReal) {
		Config.isReal = isReal;
	}

	public static String getProName() {
		return proName;
	}

	public static void setProName(String proName) {
		Config.proName = proName;
	}

	public static String getProUrl() {
		return proUrl;
	}

	public static void setProUrl(String proUrl) {
		Config.proUrl = proUrl;
	}

	public static String getFileSavePath() {
		return fileSavePath;
	}

	public static void setFileSavePath(String fileSavePath) {
		Config.fileSavePath = fileSavePath;
	}

	public static String getFileRootUrl() {
		return fileRootUrl;
	}

	public static void setFileRootUrl(String fileRootUrl) {
		Config.fileRootUrl = fileRootUrl;
	}

	public static String getTokentimeout() {
		return tokentimeout;
	}

	public static void setTokentimeout(String tokentimeout) {
		Config.tokentimeout = tokentimeout;
	}
	

}
