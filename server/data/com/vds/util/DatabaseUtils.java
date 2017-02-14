package com.vds.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class DatabaseUtils {

	public static Connection openConnection(String url) throws Exception {
		Connection conn = null;
		if (null == url || url.equals("")) {
			url = "jdbc:mysql://172.16.1.127:3306/hhw?user=root&password=bacmp123";
		}
		Class.forName("com.mysql.jdbc.Driver");
		conn = (Connection) DriverManager.getConnection(url);
		return conn;
	}

	public static void closeDatabase(Connection conn, PreparedStatement pstmt, Object object) throws SQLException {
		conn.close();
		pstmt.close();
	}

	/**
	 * 写文件
	 * 
	 * @param file
	 * @param content
	 * @throws Exception
	 */
	public static void writeStringToFile(File file, String content) throws Exception {
		// File file = new File(path);
		if (!file.exists()) {
			file.createNewFile();
		}
		try {
			FileOutputStream out = new FileOutputStream(file);
			out.write(content.getBytes());
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 把输入字符串的首字母改成小写 _改成大写
	 * 
	 * @param str
	 *            user_name --> UserName
	 * @return
	 */
	static String initcap(String s) {
		s = getCamelStr(s);
		s = s.substring(0, 1).toUpperCase() + s.substring(1, s.length());
		return s;
	}
	
	static String initcap2(String s) {
		s = getCamelStr(s);
		s = s.substring(0, 1).toLowerCase() + s.substring(1, s.length());
		return s;
	}

	// 例：user_name --> userName
	static String getCamelStr(String s) {
		String[] values = s.split("_");
		String re = "";
		String value = "";
		re = values[0].toLowerCase();
		for (int i = 1; i < values.length; i++) {
			value = values[i];
			re += value.substring(0, 1).toUpperCase() + value.substring(1, value.length()).toLowerCase();
		}
		return re;
	}
}
