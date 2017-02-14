package com.vds.sql.port;

import java.util.Map;

import com.vds.util.enums.SqlType;

public interface Operate {
	
	//操作数据库
	public void op_os(String sql,SqlType st);
	
	public Map<String,Object> op_select(String sql);

}
