package com.ds.demo;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import com.ds.demo.dao.UserDao;
import com.ds.demo.pojo.Users;
import com.ds.demo.service.BusiService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RedisTemplate<String, String> redis;
	@Autowired
	private BusiService service;

	@Test
	public void testInsert() {
		Users user = new Users();
		user.setName(new Date().toString());
		user.setRemark(new Random().nextInt() + "");
		
		user = service.insertUser(user);
		logger.info("new user id = " + user.getId());
	}
	
	@Test
	public void testGet() {
		Users user = service.getUserById(1);
		logger.info("===in testUpdate==="+user.toString());
	}
	
	@Test
	public void testList() {
		List<Users> userList = service.getUserList();
		logger.info("=== in testList === ");
		for(Users u : userList) {
			logger.info(u.toString());
		}
	}

	@Test
	public void testUpdate() {
		Users u = service.getUserById(1);
		logger.info(u.toString());
		
		service.updateUser(1,"update name2","update remark2");
		u = service.getUserById(1);
		logger.info(u.toString());
	}
	
	/**
	 * opsForValue()	操作简单属性的数据
		opsForList()	操作含有 list 的数据
		opsForSet()	操作含有 set 的数据
		opsForZSet()	操作含有 zset 的数据
		opsForHash()	操作含有 hash 的数据
	 */
	@Test
	public void testCache() {
		ValueOperations<String, String> vo = redis.opsForValue();
		vo.set("putSelf", "fffffff");
		
		System.out.println(vo.get("putSelf"));
	}
}
