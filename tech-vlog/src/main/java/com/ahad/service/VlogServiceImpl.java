package com.ahad.service;

import java.util.List;

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

	@Override
	public List<Vlog> getVlogs(String email) {
		if(vlogDao == null)
			vlogDao = ServiceProvider.getVlogDao();
		List<Vlog> vlogs = vlogDao.getVlogs(email);
		
		
		System.out.println(vlogs);
		return vlogs;
	}

	@Override
	public Vlog getVlog(int id) {
		if(vlogDao == null)
			vlogDao = ServiceProvider.getVlogDao();
		return vlogDao.getVlog(id);
	}

}
