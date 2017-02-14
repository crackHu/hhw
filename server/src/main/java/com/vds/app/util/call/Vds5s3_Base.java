package com.vds.app.util.call;

import java.lang.reflect.Method;

import net.sf.json.JSONObject;

public class Vds5s3_Base {

	public Object call(String className, String method, JSONObject jDataIn) {
		Class<?> cls = null;
		System.out.println(className);
		try {
			cls = Class.forName(className);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Class<?> partypes[] = new Class[1];
		partypes[0] = JSONObject.class;
		Method meth = null;
		try {
			meth = cls.getMethod(method, partypes);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Object returnValue = null;
		try {
			returnValue = meth.invoke(SpringUtil.getBean(cls), jDataIn);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			String msg = e.getMessage();
			JSONObject jDataOut = new JSONObject();
			jDataOut.put("resultCode", -99);
			jDataOut.put("resultMsg", msg);

			e.printStackTrace();
			return jDataOut;
		}
		JSONObject jDataOut = (JSONObject) returnValue;
		System.out.println(jDataOut.toString());
		return jDataOut;
	}

	public JSONObject demo(JSONObject dataIn) {
		System.out.println("demo--------");

		JSONObject ob = new JSONObject();

		return ob;
	}

	public static void main(String[] args) {
		JSONObject jDataIn = new JSONObject();
		jDataIn.put("phone", "13711687995");

		// Vds5s3_Base base = new Vds5s3_Base();
		// base.call("com.vds.app.util.call.Vds5s3_Base","demo", jDataIn);
		// base.call("com.vds.app.user.service.UserService", "call", jDataIn);
	}

}
