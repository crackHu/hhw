package com.vds.app.order.util;

public enum OrderEnum {
	
	CREATE(0,"新建"),PROCEED(1,"进行中"),FINISH(2,"完成"),CANCEL(3,"取消");
	private int status;
	
	private String name;

	public int getStatus() {
		return status;
	}



	public void setStatus(int status) {
		this.status = status;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	OrderEnum(int status,String name){
		this.status = status;
		this.name = name;
	}
	
}
