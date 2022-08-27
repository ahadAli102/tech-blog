package com.ahad.service;

import com.ahad.entity.Image;
import com.ahad.entity.User;

import java.util.Map;

import javax.servlet.http.Part;

/*
 * Author MD. AHAD ALI
 * Email: linkonahad10@gmail.com
 */

public interface UserService {
	int addUser(User user);

	User getUser(String email, String password);

	int insertProfileImage(Part part, String email);

	Image getProfileImage(String email);

	void rateAuthor(String authorEmail, String raterEmail, int rating);

	Map<String, Object> getAuthorRating(String email);
}
