package com.vds.app.util.call;

import java.lang.reflect.Method;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import net.sf.json.JSONObject;

@Component
public class SpringUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext = null;

	@Override

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

		if (SpringUtil.applicationContext == null) {

			SpringUtil.applicationContext = applicationContext;

		}

		System.out.println("---------------------------------------------------------------------");

		System.out.println("---------------------------------------------------------------------");

		System.out.println("---------------com.vds.app.util.call.SpringUtil------------------------------------------------------");

		System.out.println("========ApplicationContext配置成功,在普通类可以通过调用SpringUtils.getAppContext()获取applicationContext对象,applicationContext="
				+ SpringUtil.applicationContext + "========");

		System.out.println("---------------------------------------------------------------------");

	}

	// 获取applicationContext

	public static ApplicationContext getApplicationContext() {

		return applicationContext;

	}

	// 通过name获取 Bean.

	public static Object getBean(String name) {

		return getApplicationContext().getBean(name);

	}

	// 通过class获取Bean.
	public static <T> T getBean(Class<T> clazz) {

		return getApplicationContext().getBean(clazz);

	}

	// 通过name,以及Clazz返回指定的Bean
	public static <T> T getBean(String name, Class<T> clazz) {

		return getApplicationContext().getBean(name, clazz);

	}

	public static Object call(String className, String method, JSONObject jDataIn) {
		
		Class<?> cls = null;
		try {
			cls = Class.forName(className);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Class<?> partypes[] = new Class[1];
		partypes[0] = Object.class;
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
			returnValue = meth.invoke(getBean(cls), jDataIn);
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
		
		return returnValue;
	}

}