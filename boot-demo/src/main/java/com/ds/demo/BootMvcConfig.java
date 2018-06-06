package com.ds.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.ds.demo.spring.TestInterceptor;

@Configuration
public class BootMvcConfig extends WebMvcConfigurerAdapter{

	
	@Autowired
	private TestInterceptor interceptor;
	
	/**
	 * 注册拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptor);
	}
	
	/**
	 * 细粒度跨域提交 
	 */
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/fastjson/**")
              .allowedOrigins("http://localhost:8088");// 允许 8088 端口访问
    }
}
