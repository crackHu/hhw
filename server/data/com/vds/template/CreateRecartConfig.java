package com.vds.template;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CreateRecartConfig {

	public static String dbUrl = "jdbc:mysql://172.16.1.127:3306/vds5s2?useUnicode=true&characterEncoding=utf8";
	public static String dbName = "vds5s2";
	public static String jdbcName = "com.mysql.jdbc.Driver";
	public static String dbUserName = "root";
	public static String dbPassword = "bacmp123";

	private static final String host = "172.16.1.213:8080";

	private static final String projecteName = "vds5s3_user";

	private static final String date = "YYYY-MM-DD HH:mm:ss";

	private static final String tb = "tb_user_type";

	private static final String page = "user";

	private static final String api = "/v2/admin/user/usertype";

	private static final String show = "true";

	private static final String create = "true";

	private static final String update = "true";

	private static final String deleted = "true";

	private static final String fields = "";

	private static final String dateFields = "";

	public static void createReactConfig(String config) throws IOException, SQLException {
		String newClassName = getHomeDir("data/com/vds/template/react/" + page) + "/" + config + ".js";
		String actionTempContent = readFile(getHomeDir("data/com/vds/template") + "config.txt");
		new File(newClassName).getParentFile().mkdirs();
		if (!isExit(newClassName)) {
			buildClass(actionTempContent, newClassName, config);
		} else {
			System.out.println("是否覆蓋?y(yes) or n(no)");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String line = reader.readLine();

			if (line != "" && line.equalsIgnoreCase("y")) {
				buildClass(actionTempContent, newClassName, config);
			}
		}
	}

	public static String getHomeDir(String path) {
		if (!isEmpty(path)) {
			return conversionSpecialCharcters(System.getProperty("user.dir")) + "/" + path + "/";
		} else {
			return System.getProperty("user.dir");
		}
	}

	public static String conversionSpecialCharcters(String string) {
		return string.replaceAll("\\\\", "/");
	}

	public static boolean isExit(String filepath) {
		File file = new File(filepath);
		return file.exists();
	}

	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0 || str.equals("") || str.matches("\\s*");
	}

	public static String getTable(String tableName, Connection connection) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		ResultSetMetaData rsd = null;
		StringBuffer sb = new StringBuffer();
		// 查询时没有数据，只返回表头信息
		pst = connection.prepareStatement("select * from " + tableName + " where 1=2");
		rsd = pst.executeQuery().getMetaData();

		// 查询主键
		String primaryKey = null;
		pst = connection
				.prepareStatement("SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE WHERE CONSTRAINT_NAME='PRIMARY' and TABLE_NAME = ?");
		pst.setString(1, tableName.toUpperCase());
		rs = pst.executeQuery();
		if (rs.next()) {
			primaryKey = rs.getString(1);
		}

		// 查询列备注
		pst = connection.prepareStatement("select column_name, column_comment from information_schema.columns where table_name = ?");
		pst.setString(1, tableName.toUpperCase());
		rs = pst.executeQuery();

		// 先将注释放入到map再获取，防止有些列没有注释获取不对应的问题
		Map<String, String> commentMap = new HashMap<String, String>();
		while (rs.next()) {
			commentMap.put(rs.getString("COLUMN_NAME"), rs.getString("column_comment"));
		}

		for (int i = 2; i <= rsd.getColumnCount(); i++) {
			String name = tuofeng(rsd.getColumnName(i).toLowerCase());
			String dbType = rsd.getColumnTypeName(i);
			String javaT = "";
			if (!"id".equals(name.toLowerCase())) {
				if ("VARCHAR".equals(dbType.toUpperCase()) || "VARCHAR2".equals(dbType.toUpperCase()) || "CHAR".equals(dbType.toUpperCase())) {
					javaT = "String";
				} else if ("DATETIME".equals(dbType.toUpperCase()) || "DATE".equals(dbType.toUpperCase())
						|| "timestamp".equals(dbType.toLowerCase())) {
					javaT = "Date";
				} else if ("int".equals(dbType.toUpperCase()) || "INT".equals(dbType.toUpperCase()) || "INTEGER".equals(dbType.toUpperCase())
						|| "tinyint".equals(dbType.toLowerCase())) {
					javaT = "Integer";
				} else if ("decimal".equals(dbType.toUpperCase()) || "DECIMAL".equals(dbType.toUpperCase())) {
					javaT = "BigDecimal";
				} else if ("BIGINT".equals(dbType.toUpperCase()) || "bigint".equals(dbType.toUpperCase())) {
					javaT = "Long";
				} else if ("bit".equals(dbType.toUpperCase()) || "BIT".equals(dbType.toUpperCase())) {
					javaT = "Boolean";
				} else {
					javaT = "Long";
				}

				String get = name;

				if (i == 2) {
					sb.append("{\r\n");
					sb.append("\ttitle: '" + commentMap.get(rsd.getColumnName(i).toLowerCase()) + "',\r\n");
					sb.append("\tdataIndex: '" + get + "',\r\n");
					sb.append("\twidth: '10%',\r\n");
					sb.append("\tremder:{\r\n");
					sb.append("\t\tlink: true,\r\n");
					sb.append("\t\tonclick: true,\r\n");
					sb.append("\t\tmodaltitle: '查看 - {" + get + "}',\r\n");
					sb.append("\t\t},");
					sb.append("\t},");
				} else {

					if (get.endsWith("IsRemove")) {
						sb.append("\t{\r\n");
						sb.append("\ttitle: '是否删除',\r\n");
						sb.append("\tdataIndex: '" + get + "',\r\n");
						sb.append("\t},");
					} else if (get.endsWith("CreateTime")) {
						sb.append("\t{\r\n");
						sb.append("\ttitle: '创建时间',\r\n");
						sb.append("\tdataIndex: '" + get + "',\r\n");
						sb.append("\twidth: '10%',\r\n");
						sb.append("\trender: {\r\n");
						sb.append("\t\tformat: '" + date + "',\r\n");
						sb.append("\t\t},\r\n");
						sb.append("\t},\r\n");
					} else if (get.endsWith("ModifyTime")) {
						sb.append("\t{\r\n");
						sb.append("\ttitle: '修改时间',\r\n");
						sb.append("\tdataIndex: '" + get + "',\r\n");
						sb.append("\twidth: '10%',\r\n");
						sb.append("\trender: {\r\n");
						sb.append("\t\tformat: '" + date + "',\r\n");
						sb.append("\t\t},\r\n");
						sb.append("\t},\r\n");
					} else {
						sb.append("\t{\r\n");
						sb.append("\ttitle: '" + commentMap.get(rsd.getColumnName(i).toLowerCase()) + "',\r\n");
						sb.append("\tdataIndex: '" + get + "',\r\n");
						sb.append("\t},");
					}
				}
			}
		}
		System.out.println(sb.toString());
		return sb.toString();

	}

	public static String readFile(String filename) {
		StringBuffer buffer = new StringBuffer();
		try {
			FileInputStream inputStream = new FileInputStream(new File(filename));
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader reader = new BufferedReader(inputStreamReader);
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				buffer.append(tempString + "\n");
			}
			reader.close();
		} catch (Exception e) {

		}
		return buffer.toString();
	}

	public static String tuofeng(String str) {
		str = str.replace("tb_", "");
		if (str.indexOf("_") > -1) {
			String c = str.substring(str.indexOf("_"));
			String ss = str.substring(0, str.indexOf("_")) + c.substring(1, 2).toUpperCase() + c.substring(2);
			if (ss.indexOf("_") > -1) {
				String cc = ss.substring(ss.indexOf("_"));
				return ss.substring(0, ss.indexOf("_")) + cc.substring(1, 2).toUpperCase() + cc.substring(2);
			} else {
				return ss;
			}
		} else {
			return str;
		}
	}

	public static void main(String[] args) throws Exception {

		createReactConfig("tb_user_type");

	}

	public static void buildClass(String actionTempContent, String newFilepath, String table) throws SQLException {
		Connection con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		Map<String, String> map = getParam(table, con);
		actionTempContent = actionTempContent.replaceAll("\\[table\\]", getTable(table, con)).replaceAll("\\[api\\]", api)
				.replaceAll("\\[host\\]", host).replaceAll("\\[projectName\\]", projecteName).replaceAll("\\[date\\]", date)
				.replaceAll("\\[primaryKey\\]", map.get("primaryKey")).replaceAll("\\[sort\\]", map.get("sort"))
				.replaceAll("\\[title\\]", map.get("title")).replaceAll("\\[dateFields\\]", map.get("dateFields"))
				.replaceAll("\\[fields\\]", map.get("fields")).replaceAll("\\[item\\]", map.get("item"));
		writeFileByLine(actionTempContent, newFilepath);
	}

	private static void writeFileByLine(String actionTempContent, String filename) {
		File file = new File(filename);
		PrintWriter write = null;

		try {
			write = new PrintWriter(new FileOutputStream(file));
			write.print(actionTempContent);
			write.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (write != null) {
				try {
					write.close();
				} catch (Exception e) {
				}
			}
		}

	}

	public static Map<String, String> getParam(String tableName, Connection connection) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		ResultSetMetaData rsd = null;
		StringBuffer sb = new StringBuffer();
		Map<String, String> map = new HashMap<String, String>();
		Map<String, String> nullMap = new HashMap<String, String>();
		StringBuffer fields = new StringBuffer();
		StringBuffer dateFields = new StringBuffer();
		StringBuffer item = new StringBuffer();
		boolean dataFeilds = false;
		boolean feilds = false;
		// 查询时没有数据，只返回表头信息
		pst = connection.prepareStatement("select * from " + tableName + " where 1=2");
		rsd = pst.executeQuery().getMetaData();

		// 查询主键
		String primaryKey = null;
		pst = connection
				.prepareStatement("SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE WHERE CONSTRAINT_NAME='PRIMARY' and TABLE_NAME = ?");
		pst.setString(1, tableName.toUpperCase());
		rs = pst.executeQuery();
		if (rs.next()) {
			primaryKey = rs.getString(1);
		}
		map.put("primaryKey", tuofeng(primaryKey));

		// 查询列备注
		pst = connection.prepareStatement("select column_name, column_comment from information_schema.columns where table_name = ?");
		pst.setString(1, tableName.toUpperCase());
		rs = pst.executeQuery();

		// 先将注释放入到map再获取，防止有些列没有注释获取不对应的问题
		Map<String, String> commentMap = new HashMap<String, String>();

		while (rs.next()) {
			commentMap.put(rs.getString("COLUMN_NAME"), rs.getString("column_comment"));
		}

		pst = connection.prepareStatement("SHOW FULL COLUMNS from "+tableName);
		rs = pst.executeQuery();
		while (rs.next()) {
			nullMap.put(rs.getString("Field").toLowerCase(), rs.getString("Null"));
		}

		System.out.println(nullMap.toString());

		for (int i = 1; i <= rsd.getColumnCount(); i++) {
			String comment = commentMap.get(rsd.getColumnName(i).toLowerCase());
			String isNullStr = nullMap.get(rsd.getColumnName(i).toLowerCase());
			boolean isNull = false;
			if(isNullStr.equals("YES")){
				isNull = true;
			}
			if (i == 2) {
				map.put("title", commentMap.get(rsd.getColumnName(i).toLowerCase()));
			}

			String name = tuofeng(rsd.getColumnName(i).toLowerCase());
			String dbType = rsd.getColumnTypeName(i);
			String javaT = "";
			if (!"id".equals(name.toLowerCase())) {
				if ("VARCHAR".equals(dbType.toUpperCase()) || "VARCHAR2".equals(dbType.toUpperCase()) || "CHAR".equals(dbType.toUpperCase())) {
					javaT = "String";
				} else if ("DATETIME".equals(dbType.toUpperCase()) || "DATE".equals(dbType.toUpperCase())
						|| "timestamp".equals(dbType.toLowerCase())) {
					javaT = "Date";
				} else if ("int".equals(dbType.toUpperCase()) || "INT".equals(dbType.toUpperCase()) || "INTEGER".equals(dbType.toUpperCase())
						|| "tinyint".equals(dbType.toLowerCase())) {
					javaT = "Integer";
				} else if ("decimal".equals(dbType.toUpperCase()) || "DECIMAL".equals(dbType.toUpperCase())) {
					javaT = "BigDecimal";
				} else if ("BIGINT".equals(dbType.toUpperCase()) || "bigint".equals(dbType.toUpperCase())) {
					javaT = "Long";
				} else if ("bit".equals(dbType.toUpperCase()) || "BIT".equals(dbType.toUpperCase())) {
					javaT = "Boolean";
				} else {
					javaT = "Long";
				}

				String get = name;
				if ("DATETIME".equals(dbType.toUpperCase()) || "DATE".equals(dbType.toUpperCase()) || "timestamp".equals(dbType.toLowerCase())) {
					if (dataFeilds) {
						dateFields.append("\t\t");
					}
					dateFields.append("'" + get + "',\r\n");
					dataFeilds = true;
					if(i!=1){
						item.append("{\r\n");
						item.append("\t\tlabel: '"+comment+"',\r\n");
						item.append("\t\tname: '"+get+"',\r\n");
						item.append("\t\ttype: 'datepicker',\r\n");
						item.append("\t\tformat: DATE_FORMAT_STRING,\r\n");
						item.append("\t\trequired: 'true',\r\n");
						item.append("\t\tmessage: '"+comment+"不能为空',\r\n");
						item.append("\t\tshowIn: {\r\n");
						item.append("\t\t\tCREATE: false,\r\n");
						item.append("\t\t\tUPDATE: false,\r\n");
						item.append("\t\t\tQUERY: true\r\n");
						item.append("\t\t},\r\n");
						item.append("\t\tconfig: {\r\n");
						item.append("\t\t\tshowTime: true,\r\n");
						item.append("\t\t\tstyle: {\r\n");
						item.append("\t\t\twidth: 197.33\r\n");
						item.append("\t\t}\r\n");
						item.append("\t\t}\r\n");
						item.append("\t\t},\r\n");
						
						
						
					}
				} else {
					if (feilds) {
						fields.append("\t\t");
					}
					fields.append("'" + get + "',\r\n");
					feilds = true;
					
					if(i!=1){
						item.append("{\r\n");
						item.append("\t\tlabel: '"+comment+"',\r\n");
						item.append("\t\tname: '"+get+"',\r\n");
						item.append("\t\ttype: 'input',\r\n");
						if(!isNull){
							item.append("\t\trequired: 'true',\r\n");
							item.append("\t\tmessage: '"+comment+"不能为空',\r\n");
						}
						item.append("\t\tshowIn: {");
						item.append("\t\t\tCREATE: true,\r\n");
						item.append("\t\t\tUPDATE: true,\r\n");
						item.append("\t\t\tQUERY: true");
						item.append("\t\t},\r\n");
						item.append("\t\tconfig: {");
						item.append("\t\t\tplaceholder: '請輸入"+comment+"'\r\n");
						item.append("\t\t}\r\n");
						item.append("\t\t},\r\n");
					}
					
				}
				if (i == 2) {
				} else {

					if (get.endsWith("IsRemove")) {
					} else if (get.endsWith("CreateTime")) {
					} else if (get.endsWith("ModifyTime")) {
						map.put("sort", get + ",desc");
					} else {
					}
				}
			}
		}
		map.put("dateFields", dateFields.toString());
		map.put("fields", fields.toString());
		map.put("item", item.toString());
		return map;

	}

}
