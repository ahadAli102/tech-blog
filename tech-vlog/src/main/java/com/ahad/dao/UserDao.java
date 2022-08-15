package com.ahad.dao;

import com.ahad.entity.User;

public interface UserDao {
	int addUser(User user);
	User getUser(String email,String password);
}
