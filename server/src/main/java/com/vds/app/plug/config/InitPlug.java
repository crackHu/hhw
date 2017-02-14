package com.vds.app.plug.config;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.vds.app.plug.jpa.PlugFunctionJpa;
import com.vds.app.plug.jpa.PlugInJpa;
import com.vds.app.plug.model.PlugIn;

@Component
@Order(10)
public class InitPlug implements ServletContextListener {

	@Inject
	private PlugInJpa piJpa;
	@Inject
	private PlugFunctionJpa pfJpa;

	private final String name = "plug";
	private final String version = "V1.0.0";

	public void execute() {
		
		startInit(name, version);
	}

	public void contextDestroyed(ServletContextEvent arg0) {
	}

	public void contextInitialized(ServletContextEvent arg0) {
		execute();
	}

	public synchronized void startInit(final String name, final String version) {
				System.out.println("----------开始初始化 " + name + "----------");
				System.err.println("初始化数据");
				
				pfJpa.deleteAll();
				if(name.equals("plug")){
					piJpa.deleteAll();
				}

				PlugIn pi = new PlugIn(name, version, 0, "注册...");
				

				pi = piJpa.save(pi);

				pi.setPiStatus(1);
				pi.setPiMsg("启动中...");

				piJpa.save(pi);
				System.out.println("----------初始化 " + name + "结束----------");
					pi.setPiStatus(2);
					pi.setPiMsg("启动成功...");

					piJpa.save(pi);
				System.out.println("----------" + name + "插件已成功配置----------");

	}

	
}