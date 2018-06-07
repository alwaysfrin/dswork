package com.ds.demo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

	/**
	 * 出现异常后的处理
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public String defaultExceptionHandler(Exception e) {
		e.printStackTrace();
		//to do
        return "error:" + e.getMessage();
    }

}
