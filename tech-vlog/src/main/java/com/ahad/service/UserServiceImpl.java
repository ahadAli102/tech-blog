package com.ahad.service;

import com.ahad.dao.UserDao;
import com.ahad.entity.User;
import com.ahad.util.ServiceProvider;

public class UserServiceImpl implements UserService {
	private UserDao userDao;

	@Override
	public int addUser(User user) {
		if (userDao == null)
			userDao = ServiceProvider.getUserDao();

		if (user.getCondition() != null && user.getCondition().equals("accepted")) {
			if (user.getName() != null && !user.getName().isEmpty() 
					&& user.getPassword() != null && !user.getPassword().isEmpty() 
					&& user.getRePassword() != null && !user.getRePassword().isEmpty()) {
				if(user.getPassword().equals(user.getRePassword())) {
					return userDao.addUser(user);
				}else {
					throw new RuntimeException("Passwords didn't matched");
				}
			} else {
				throw new RuntimeException("All form filed not filled");
			}
		} else {
			throw new RuntimeException("Sorry you don't have checked condition");
		}

	}

}
