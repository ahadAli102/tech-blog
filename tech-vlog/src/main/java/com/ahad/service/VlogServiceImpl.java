package com.ahad.service;

import com.ahad.dao.VlogDao;
import com.ahad.entity.Vlog;
import com.ahad.util.ServiceProvider;

public class VlogServiceImpl implements VlogService {
	private VlogDao vlogDao;

	@Override
	public int addVlog(String title, String description, String email) {
		if(vlogDao == null)
			vlogDao = ServiceProvider.getVlogDao();
		if (title != null && !title.isEmpty() && description != null && !description.isEmpty()) {
			Vlog vlog = new Vlog(title, description, email);
			System.out.println("Adding form");
			return vlogDao.addVlog(vlog);
		} else {
			throw new RuntimeException("Please fill all place");
		}
	}

}
