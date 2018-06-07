package com.ds.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;

import com.ds.demo.pojo.Users;

@Mapper
public interface UserDao {

	public List<Users> listUsers();

	public void insert(Users user);
	
	public Users getById(long id);
	
	public void update(
			@Param("id")long id,
			@Param("name")String newName,
			@Param("remark")String newRemark);
	
	public void deleteById(long id);
}
