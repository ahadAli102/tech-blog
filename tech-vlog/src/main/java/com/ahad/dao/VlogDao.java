package com.ahad.dao;

import java.util.List;

import com.ahad.entity.Vlog;
import com.ahad.entity.VlogRating;

public interface VlogDao {
	int addVlog(Vlog vlog);
	List<Vlog> getVlogs(String email);
	Vlog getVlog(int id);
	boolean isRatingExist(VlogRating vr);
	void rateVlog(VlogRating vr);
}
