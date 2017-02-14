package com.vds.util;

import java.util.ArrayList;
import java.util.List;

public class BaseModel {
	private List<String> colnames = new ArrayList<String>(); // 列名数组
	private List<String> orgcolnames = new ArrayList<String>();; // 原列名数组
	private List<String> colTypes = new ArrayList<String>();; // 列名类型数组
	private List<String> memos = new ArrayList<String>();; // 列名类型数组
	private int[] colSizes; // 列名大小数组
	private boolean futil = false; // 是否需要导入包java.util.*
	private boolean fsql = false; // 是否需要导入包java.sql.*
	private String pname;// 项目名称
	private String moduleName;// 模块名称
	private String tableName;// 表名称
	private String packageUrl;// 项目路径

	public List<String> getColnames() {
		return colnames;
	}

	public void setColnames(List<String> colnames) {
		this.colnames = colnames;
	}

	public List<String> getOrgcolnames() {
		return orgcolnames;
	}

	public void setOrgcolnames(List<String> orgcolnames) {
		this.orgcolnames = orgcolnames;
	}

	public List<String> getColTypes() {
		return colTypes;
	}

	public void setColTypes(List<String> colTypes) {
		this.colTypes = colTypes;
	}

	public List<String> getMemos() {
		return memos;
	}

	public void setMemos(List<String> memos) {
		this.memos = memos;
	}

	public int[] getColSizes() {
		return colSizes;
	}

	public void setColSizes(int[] colSizes) {
		this.colSizes = colSizes;
	}

	public boolean isFutil() {
		return futil;
	}

	public void setFutil(boolean futil) {
		this.futil = futil;
	}

	public boolean isFsql() {
		return fsql;
	}

	public void setFsql(boolean fsql) {
		this.fsql = fsql;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getPackageUrl() {
		return packageUrl;
	}

	public void setPackageUrl(String packageUrl) {
		this.packageUrl = packageUrl;
	}
}
