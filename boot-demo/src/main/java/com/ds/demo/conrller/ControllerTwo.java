package com.ds.demo.conrller;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ds.demo.pojo.Product;

@RestController
public class ControllerTwo {

	@RequestMapping("product")
	public Product getProduct(){
		Product p = new Product();
		p.setCount(1);
		p.setPrice(99);
		p.setDate(new Date());
		p.setName("testPName");
		
		return p;
	}
}
