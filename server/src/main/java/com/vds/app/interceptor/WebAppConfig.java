package com.vds.app.interceptor;

import javax.inject.Inject;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * web指令 用户配置拦截
 *
 */
@Configuration
@Component
public class WebAppConfig extends WebMvcConfigurerAdapter {

	@Inject
	private LoginInterceptor interceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptor).excludePathPatterns("/v2/open/**").excludePathPatterns("/app/**").excludePathPatterns("/error").excludePathPatterns("/open/**").excludePathPatterns("/v2/admin/**");
		super.addInterceptors(registry);
	}

}
