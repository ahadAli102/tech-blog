package com.ahad.util;

import com.ahad.dao.UserDao;
import com.ahad.dao.UserDaoImpl;
import com.ahad.dao.VlogDao;
import com.ahad.dao.VlogDaoImpl;
import com.ahad.service.UserService;
import com.ahad.service.UserServiceImpl;
import com.ahad.service.VlogService;
import com.ahad.service.VlogServiceImpl;

public class ServiceProvider {
	private static UserDao userDao;
	private static UserService userService;
	private static VlogDao vlogDao;
	private static VlogService vlogService;

	public static UserDao getUserDao() {
		System.out.println("get user dao");
		if (userDao == null) {
			userDao = new UserDaoImpl();
		}
		return userDao;
	}

	public static UserService getUserService() {
		System.out.println("GET user service");
		if (userService == null) {
			userService = new UserServiceImpl();
		}
		return userService;
	}

	public static VlogDao getVlogDao() {
		System.out.println("get vlog dao");
		if (vlogDao == null) {
			vlogDao = new VlogDaoImpl();
		}
		return vlogDao;
	}

	public static VlogService getVlogService() {
		System.out.println("GET vlog service");
		if (vlogService == null) {
			vlogService = new VlogServiceImpl();
		}
		return vlogService;
	}

}
