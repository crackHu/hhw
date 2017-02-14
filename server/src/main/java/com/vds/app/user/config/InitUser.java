package com.vds.app.user.config;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.vds.app.plug.config.InitPlug;
import com.vds.app.util.SyncThread;

@Component
@Order(11)
public class InitUser implements ServletContextListener {

	private final String name = "user";
	private final String version = "V1.0.0";

	@Inject
	InitPlug initPlug;

	// 插件自检查 , 把数据存储在 tb_plus_in 表中
	public void execute() {
		SyncThread syncThread = new SyncThread() {
			public synchronized void running() {
				initPlug.startInit(name, version);
			}
		};
		syncThread.start();

	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		execute();
	}

}