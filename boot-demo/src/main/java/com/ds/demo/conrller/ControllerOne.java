package com.ds.demo.conrller;

import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.ds.demo.message.LocaleMessageSourceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(value="测试服务",tags= {"接口说明"})
@Controller
public class ControllerOne {
	
	private Logger logger = Logger.getLogger(ControllerOne.class);
	
	@Resource
	private LocaleMessageSourceService localeMessageSourceService;

	//返回数据
	@ApiOperation("欢迎使用Spring boot")
	@ApiImplicitParam(name = "name", value = "入参名", dataType = "string", paramType = "query")
	@GetMapping("hello")
	@ResponseBody
	public String hello(){
		logger.info("=====================");
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

	@ApiOperation("国家化")
	@GetMapping("intern")
    public String international(Model model) {
		System.out.println("=======internation========");
		model.addAttribute("msg","后台获取配置资源：" + localeMessageSourceService.getMessage("pro.name"));
		
		return "i18";
    }

	@ApiOperation("更改国际化")
	@GetMapping("changeintern")
    public String changeinternational(HttpServletRequest request,HttpServletResponse response, String lang) {
		LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
		if("zh".equals(lang)) {
			localeResolver.setLocale(request, response, new Locale("zh","CN"));
			//request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,new Locale("zh","CN"));
		}else {
			//request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,new Locale("en", "US"));
			localeResolver.setLocale(request, response, new Locale("en","US"));
		}
		return "redirect:/intern";
    }
}
