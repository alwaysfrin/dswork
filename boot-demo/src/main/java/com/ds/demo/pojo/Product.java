package com.ds.demo.pojo;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class Product {

	private String name;
	private int count;
	
	@JSONField(format="yyyy月dd日")
	private Date date;
	
	//不序列化，不进行json封装
	@JSONField(serialize=false)
	private double price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
