package com.yash.OnlineTest.service;

import java.util.Optional;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.OnlineTest.dao.UserDao;
import com.yash.OnlineTest.model.User;

@Service
public class UserService {
	
	Logger logger = LoggerFactory.getLogger(UserService.class);

	private final UserDao userDao;
	@Autowired
	public UserService(UserDao userDao)
	{
		this.userDao = userDao;
		
	}
	
	public boolean doLogin(String userName,String password)
	{
		Optional<User> u= userDao.findByUsername(userName);
		boolean isValidUser = false;
		if(u.isPresent())
		{
			if(u.get().getPassword().equals(password) && u.get().getUsername().equals(userName))
				isValidUser = true;
			else 
				isValidUser = false;
		}
		logger.info("u:{},userName:{},password:{}",u,userName,password);
		return isValidUser;
	}
	
	public User register(User user)
	{
		return userDao.save(user);
	}
	public User fetchUserByusername(String userName) {
		return userDao.findByusername(userName);
	}
	
}
