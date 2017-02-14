package com.vds.app.util;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.tencent.common.Configure;

/**
 * 什么都有的工具类
 * 
 * @author Cay
 *
 */
public class CayUtil {

	/**
	 * ========================== Time ==========================
	 */

	/**
	 * 
	 * @param date
	 * @param cType
	 *            ====>Calendar 的属性参数 例如：Calendar.MINUTE
	 * @param lenght
	 *            ====>整形,表示想要获取多长的cType 类型之后的时间
	 * @return
	 */
	public static Date getTimeByAfter(Date date, Integer cType, Integer lenght) {
		Calendar now = Calendar.getInstance();
		// Calendar.MINUTE
		now.add(cType, lenght);

		return now.getTime();
	}

	/**
	 * ========================= number ============================
	 */

	/**
	 * 获取随机随机数字字符串
	 * 
	 * @param lenght
	 *            获取随机数字字符串的长度
	 * @return
	 */
	public static String getRandomNumberByLenght(Integer lenght) {
		if (lenght < 0) {
			lenght = 1;
		}
		return String.valueOf((int) ((Math.random() * 9 + 1) * Math.pow(10, lenght - 1)));
		// return "123456";
	}

	/**
	 * 获取UUID 去“-”,↑大写
	 * 
	 * @return
	 */
	public static String getUUId() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().toUpperCase().replace("-", "");
	}

	public static StringBuffer getInterfaceMethod(HttpServletRequest request) {

		return request.getRequestURL();
	}

	public static void main(String[] args) {
		System.out.println(getUUId());
	}

	public static String getWechatParamString(String code) {
		String param = "";

		param += "appid=" + Configure.getAppid();
		param += "&secret=" + Configure.getSecret();
		param += "&code=" + code;
		param += "&grant_type=authorization_code";
		return param;
	}

	public static String getWechatParamUserInfo(String access_token, String openId) {
		String param = "";
		param += "access_token=" + access_token;
		param += "&openid=" + openId;
		param += "&lang=zh_CN";
		return param;
	}

}
