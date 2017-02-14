package com.vds.app.util.sms;

import com.vds.app.util.HttpRequest;

import net.sf.json.JSONObject;

public class Sms {

	private final static String api = "http://www.basoft.cn/vds5s2/v2/open/sms/sendSmsBySmsCfId";

	
	public static String send(String din) {
		JSONObject jo = JSONObject.fromObject(din);
		din = "key=" + jo.get("key");
		din += "&phone=" + jo.get("phone");
		din += "&json=" +jo.get("json");
		return HttpRequest.sendPost(api, din);
	}
	
	
	public static String send2(String phone,String code) {
		JSONObject ob = new JSONObject();
		ob.put("key", "11");
		ob.put("phone", phone);
		JSONObject json = new JSONObject();
		json.put("code", code);
		ob.put("json", json);
		
		return send(ob.toString());
	}
	
	

	public static void main(String[] args) {

		long starTime = System.currentTimeMillis();
		JSONObject ob = new JSONObject();
		ob.put("name", "Plug 插件V1.0.0");
		
		
		
		System.out.println(send(ob.toString()));
		long endTime = System.currentTimeMillis();
		System.out.println(endTime-starTime+"Ms");
	}

}
