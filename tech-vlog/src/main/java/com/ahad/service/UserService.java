package com.ahad.service;

import com.ahad.entity.User;
import javax.servlet.http.Part;

public interface UserService {
	int addUser(User user);

	User getUser(String email, String password);

	int insertProfileImage(Part part,String email);
}
