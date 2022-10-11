package com.yash.OnlineTest.dao;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yash.OnlineTest.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

	Optional<User> findByUsername(String name);
	public User findByusername(String userName);
	
}
