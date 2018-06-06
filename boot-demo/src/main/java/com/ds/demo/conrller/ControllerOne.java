package com.ds.demo.conrller;

import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(value="测试服务",tags= {"接口说明"})
@Controller
public class ControllerOne {

	//返回数据
	@ApiOperation("欢迎使用Spring boot")
	@ApiImplicitParam(name = "name", value = "入参名", dataType = "string", paramType = "query")
	@GetMapping("hello")
	@ResponseBody
	public String hello(){
		return "hello world";
	}

	@ApiOperation("测试freemarker")
	@GetMapping("page")
    public String gotoFm(Model model) {
		model.addAttribute("msg","from controller");
		
        return "fmpage1";
    }

	@ApiOperation("测试异常方案")
	@GetMapping("geterror")
    public String gotoError(Model model) {
		int num = 1/0;
		
        return num + "";
    }
}
