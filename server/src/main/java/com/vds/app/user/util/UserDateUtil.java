package com.vds.app.user.util;

import java.util.Calendar;
import java.util.Date;

public class UserDateUtil {

	public static Date getExpireTime(long expireLong) {

		Calendar calendar = Calendar.getInstance();

		calendar.setTimeInMillis(calendar.getTimeInMillis() + expireLong);

		return calendar.getTime();
	}

	public static void main(String[] args) {

		System.out.println(getExpireTime(10000 * 60));
	}

}
