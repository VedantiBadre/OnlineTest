package com.yash.OnlineTest.controller;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.OnlineTest.model.User;
import com.yash.OnlineTest.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

	private final UserService userService;

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/login")
	 @CrossOrigin( origins =  "http://localhost:4200")
	public ResponseEntity<User> doLogin(@RequestBody User user) {
		System.out.println("userName-="+user.getUsername()+"password="+user.getPassword());
		String userName=user.getUsername();
		String password=user.getPassword();
		//logger.info("username:{} password:{}", userName, password);
		if (userService.doLogin(userName, password))
			return new ResponseEntity<User>(HttpStatus.ACCEPTED);
		else
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);

	}

	@PostMapping("/register")
	@CrossOrigin("http://localhost:4200")
	public /* ResponseEntity<User> */ User register(@RequestBody User user) throws Exception {
		String tempUserName = user.getUsername();
		if (tempUserName != null && !"".equals(tempUserName)) {
			User userObj = userService.fetchUserByusername(tempUserName);
			if (userObj != null) {
				throw new Exception("User with " + tempUserName + " is already exist");
			}

		}
		// return new
		// ResponseEntity<User>(userService.register(user),HttpStatus.CREATED);
		User userObj = null;
		userObj = userService.register(user);
		return userObj;
	}
}
