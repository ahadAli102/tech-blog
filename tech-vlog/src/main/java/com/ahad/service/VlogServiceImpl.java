package com.ahad.service;

import java.util.List;
import java.util.Map;

import com.ahad.dao.VlogDao;
import com.ahad.entity.User;
import com.ahad.entity.Vlog;
import com.ahad.entity.VlogRating;
import com.ahad.util.ServiceProvider;

public class VlogServiceImpl implements VlogService {
	private VlogDao vlogDao;

	@Override
	public int addVlog(String title, String description, String email) {
		if (vlogDao == null)
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
	public List<Vlog> getAuthorVlogs(String email) {
		if (vlogDao == null)
			vlogDao = ServiceProvider.getVlogDao();
		List<Vlog> vlogs = vlogDao.getAuthorVlogs(email);
		return vlogs;
	}

	@Override
	public Vlog getVlog(int id) {
		if (vlogDao == null)
			vlogDao = ServiceProvider.getVlogDao();
		return vlogDao.getVlog(id);
	}

	@Override
	public void rateVlog(int vlogId, String raterEmail, int vlogRating) {
		if (vlogDao == null)
			vlogDao = ServiceProvider.getVlogDao();
		try {
			VlogRating vr = new VlogRating(vlogId, raterEmail, vlogRating);
			if (!vlogDao.isRatingExist(vr)) {
				vlogDao.rateVlog(vr);
			} else {
				throw new RuntimeException("You have already rated this vlog");
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

	}

	@Override
	public Map<String, Object> getVlogRating(int id) {
		if (vlogDao == null)
			vlogDao = ServiceProvider.getVlogDao();
		Map<String,Object> rating = vlogDao.getVlogRating(id);
		if(rating.get("avg_rating") == null) {
			rating.put("avg_rating", "Not rated");
		}
		return rating;
	}

	@Override
	public User getVlogAuthor(String email) {
		if (vlogDao == null)
			vlogDao = ServiceProvider.getVlogDao();
		return vlogDao.getVlogAuthor(email);
	}

	@Override
	public void deleteVlog(int vlogId) {
		if (vlogDao == null)
			vlogDao = ServiceProvider.getVlogDao();
		if (vlogDao.deleteVlog(vlogId) != -1) {
			
		} else {
			throw new RuntimeException("Vlog is not deleted");
		}
	}

	@Override
	public void editvlog(int vlogId, String title, String description) {
		if (vlogDao == null)
			vlogDao = ServiceProvider.getVlogDao();
		if(vlogDao.editvlog(vlogId,title,description) ==-1) {
			throw new RuntimeException("Failed to update vlog");
		}
	}

	@Override
	public List<Vlog> getVlogs() {
		if (vlogDao == null)
			vlogDao = ServiceProvider.getVlogDao();
		List<Vlog> vlogs = vlogDao.getVlogs();
		return vlogs;
	}

	@Override
	public List<Vlog> getVlogs(String query) {
		if (vlogDao == null)
			vlogDao = ServiceProvider.getVlogDao();
		List<Vlog> vlogs = vlogDao.getVlogs(query);
		return vlogs;
	}

}
