package com.ahad.service;

import com.ahad.entity.User;

public interface UserService {
	int addUser(User user);
	User getUser(String email,String password);
}
