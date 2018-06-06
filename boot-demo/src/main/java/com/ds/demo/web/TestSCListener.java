package com.ds.demo.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class TestSCListener implements ServletContextListener {
	
	private String listenerName;
	
	public TestSCListener(String name) {
		this.listenerName = name;
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//System.out.println("==init sclistener==" + listenerName);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		//System.out.println("==destory sclistener==" + listenerName);
	}

}
