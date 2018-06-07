package com.ds.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ds.demo.dao.UserDao;
import com.ds.demo.pojo.Users;

@CacheConfig(cacheNames = "users")
@Service
public class BusiService {

	@Autowired
	private UserDao userDao;

	@CachePut(key="#users.id")
	public Users insertUser(Users users) {
		userDao.insert(users);
		
		return users;
	}

	@Cacheable(key="#id")
	public Users getUserById(long id) {
		return userDao.getById(id);
	}

	@CachePut(key="#id")
	public Users updateUser(long id,String newName,String newRemark) {
		userDao.update(1,"update name2","update remark2");
		
		return getUserById(id);
	}
	
	@Cacheable
	public List<Users> getUserList(){
		return userDao.listUsers();
	}
}
