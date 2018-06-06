package com.ds.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.ds.demo.spring.TestInterceptor;
import com.ds.demo.web.TestFilter;
import com.ds.demo.web.TestSCListener;
import com.ds.demo.web.TestServlet;

@Configuration
public class BootWebConfig {
	
	/**
	 * 用户转换json对象，所有url请求总的对象属性进行转换
	 * @return
	 */
	@Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
		System.out.println("===json convert===");
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();

        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        
        return new HttpMessageConverters(fastJsonHttpMessageConverter);
    }
	
	/**
	 * 注册自定义servlet
	 * @return
	 */
	@Bean
	public ServletRegistrationBean<TestServlet> servletRegistrationBean() {
	    return new ServletRegistrationBean<TestServlet>(new TestServlet(),"/st");
	}
	
	/**
	 * 自定义filter
	 * @return
	 */
	@Bean
	public FilterRegistrationBean<TestFilter> timeFilter() {
	    FilterRegistrationBean<TestFilter> registrationBean = new FilterRegistrationBean();
	    
	    TestFilter filter = new TestFilter();
	    registrationBean.setFilter(filter);
	    
	    List<String> urls = new ArrayList<>();
	    urls.add("/*");
	    registrationBean.setUrlPatterns(urls);
	    
	    return registrationBean;
	}
	
	/**
	 * 全局监听器
	 * @return
	 */
	@Bean
	public ServletListenerRegistrationBean<TestSCListener> servletListenerRegistrationBean() {
	    return new ServletListenerRegistrationBean<TestSCListener>(new TestSCListener("in config"));
	}

	/**
	 * 跨域提交（粗粒度路径范围）
	 * @return
	 */
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
          @Override
          public void addCorsMappings(CorsRegistry registry) {
              registry.addMapping("/fastjson/**")
                      .allowedOrigins("http://localhost:8088");// 允许 8088 端口访问
          }
        };
    }
}
