package com.ds.demo.pojo;

public class Users extends BaseBean {

	private static final long serialVersionUID = 3323807299875457102L;
	private String name;
	private String remark;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "id:" + getId() + ",name=" 
				+ getName() + ",remark="+getRemark();
	}
}
