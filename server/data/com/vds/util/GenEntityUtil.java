package com.vds.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vds.app.exception.MyException;

public class GenEntityUtil {
	private BaseModel baseModel = new BaseModel();

	enum Status {
		Table, Database
	}

	public GenEntityUtil(String tableName, String url) throws Exception {
		Connection conn = DatabaseUtils.openConnection(url); // 得到数据库连接
		PreparedStatement pstmt = null;
		String strsql = "select * from " + tableName;
		try {
			pstmt = conn.prepareStatement(strsql);
			DatabaseMetaData rsmd = (DatabaseMetaData) conn.getMetaData();
			ResultSet resultSet = rsmd.getColumns(null, "%", tableName, "%");
			List<String> colnames = new ArrayList<String>(); // 列名数组
			List<String> orgcolnames = new ArrayList<String>();
			; // 原列名数组
			List<String> colTypes = new ArrayList<String>();
			; // 列名类型数组
			List<String> memos = new ArrayList<String>();
			; // 列名类型数组
			int[] colSizes = null; // 列名大小数组
			boolean f_util = false; // 是否需要导入包java.util.*
			boolean f_sql = false; // 是否需要导入包java.sql.*
			while (resultSet.next()) {
				colnames.add(DatabaseUtils.getCamelStr(resultSet.getString("COLUMN_NAME")));
				orgcolnames.add(resultSet.getString("COLUMN_NAME"));
				colTypes.add(resultSet.getString("TYPE_NAME"));
				memos.add(resultSet.getString("REMARKS"));
				if (resultSet.getString("TYPE_NAME").equalsIgnoreCase("datetime")
						|| resultSet.getString("TYPE_NAME").equalsIgnoreCase("timestamp")) {
					f_util = true;
				}
				if (resultSet.getString("TYPE_NAME").equalsIgnoreCase("image")
						|| resultSet.getString("TYPE_NAME").equalsIgnoreCase("text")) {
					f_sql = true;
				}
			}
			baseModel.setColnames(colnames);
			baseModel.setOrgcolnames(orgcolnames);
			baseModel.setColTypes(colTypes);
			baseModel.setFsql(f_sql);
			baseModel.setFutil(f_util);
			baseModel.setColSizes(colSizes);
			baseModel.setMemos(memos);
			baseModel.setTableName(tableName);
			try {
				String packageURl = System.getProperty("user.dir");
				String[] packageNames = packageURl.split("\\\\");
				String packageName = packageNames[packageNames.length - 1];
				String model = tableName.split("_")[0].toLowerCase();
				baseModel.setPackageUrl(packageURl);
				baseModel.setPname(packageName);
				baseModel.setModuleName(model);
				ModelUtil.GenerateModel(baseModel);
				// ServiceUtil.GenerateModel(baseModel);
				// ServiceImplUtil.GenerateModel(baseModel);
				// ActionUtil.GenerateModel(baseModel);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseUtils.closeDatabase(conn, pstmt, null);
		}
	}

	public static List<String> getTables(String url) {
		List<String> list = new ArrayList<String>();
		try {
			Connection conn = DatabaseUtils.openConnection(url);
			// 数据库连接
			DatabaseMetaData metaDate = conn.getMetaData();
			ResultSet rs = metaDate.getTables("", "%", "%", new String[] { "TABLE" });
			while (rs.next()) {
				// System.out.println(rs.getString("TABLE_NAME"));
				for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
					System.out.println("rs (" + i + "):" + rs.getMetaData().getColumnLabel(i + 1));
					System.out.println("rs desc :" + rs.getString(rs.getMetaData().getColumnLabel(i + 1)));

				}
				list.add(rs.getString("TABLE_NAME"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public static ResultSet getSetTables(String url) throws MyException {
		ResultSet rs = null;
		try {
			Connection conn = DatabaseUtils.openConnection(url);
			// 数据库连接
			DatabaseMetaData metaDate = conn.getMetaData();
			rs = metaDate.getTables("", "%", "%", new String[] { "TABLE" });

		} catch (Exception e) {
			throw new MyException("9999", "数据库连接异常");
		}
		return rs;
	}

	public static ResultSet getSetFldByTables(String tableName, String url) {
		ResultSet rs = null;
		try {
			Connection conn = DatabaseUtils.openConnection(url);

			String sql = "select * from " + tableName;
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			ResultSetMetaData data = rs.getMetaData();

			for (int i = 1; i <= data.getColumnCount(); i++) {
				// 获得指定列的列名
				String columnName = data.getColumnName(i);
				// 获得指定列的列值
				int columnType = data.getColumnType(i);
				// 获得指定列的数据类型名
				String columnTypeName = data.getColumnTypeName(i);
				// 所在的Catalog名字
				String catalogName = data.getCatalogName(i);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rs;
	}

	/**
	 * @author Cay
	 * @data 2016年12月29日
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		String tableName = "tb_other_car";
		String url = "";
		// new GenEntityUtil(tableName, url);

		// getSetFldByTables(tableName, url);
		// getTables("");
	}

}
