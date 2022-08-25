package com.ahad.dao;

import com.ahad.entity.Image;
import com.ahad.entity.User;

public interface UserDao {
	int addUser(User user);
	User getUser(String email,String password);
	int insertProfileImage(byte[] image, String fileName, String type,String email);
	Image getImage(String eamil);
	int rateAuthor(String authorEmail, String raterEmail, int rating);
}
