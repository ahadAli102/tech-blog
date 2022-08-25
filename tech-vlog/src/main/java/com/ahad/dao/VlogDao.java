package com.ahad.dao;

import java.util.List;
import java.util.Map;

import com.ahad.entity.User;
import com.ahad.entity.Vlog;
import com.ahad.entity.VlogRating;

public interface VlogDao {
	int addVlog(Vlog vlog);
	List<Vlog> getVlogs(String email);
	Vlog getVlog(int id);
	boolean isRatingExist(VlogRating vr);
	void rateVlog(VlogRating vr);
	Map<String, Object> getVlogRating(int id);
	User getVlogAuthor(String email);
	int deleteVlog(int vlogId);
}
