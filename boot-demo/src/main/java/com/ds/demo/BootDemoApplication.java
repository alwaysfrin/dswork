package com.ds.demo;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.ds.demo.web.TestFilter;
import com.ds.demo.web.TestSCListener;
import com.ds.demo.web.TestServlet;

@EnableCaching
@SpringBootApplication
public class BootDemoApplication extends SpringBootServletInitializer implements ServletContextInitializer{
	
	/**
	 * 启动时初始化
	 */
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		//此处可加载servlet、filter、以及listener
		// 配置 Servlet
        servletContext.addServlet("ts2",new TestServlet())
                      .addMapping("/ts2");
        // 配置过滤器
        servletContext.addFilter("timeFilter",new TestFilter())
                      .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST),true,"/filter/*");
        // 配置监听器
        servletContext.addListener(new TestSCListener("in main"));
	}

	//打war包
	//集成springbootservletinitalizer，重写configure
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BootDemoApplication.class);
    }
	
	/**
	 * 服务启动
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(BootDemoApplication.class, args);
	}
}
