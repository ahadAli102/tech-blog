package com.ahad.service;

import java.util.List;
import java.util.Map;

import com.ahad.entity.User;
import com.ahad.entity.Vlog;

public interface VlogService {
	int addVlog(String title,String description,String email);
	List<Vlog> getVlogs(String email);
	Vlog getVlog(int id);
	void rateVlog(int vlogId, String raterEmail, int vlogRating);
	Map<String,Object> getVlogRating(int id);
	User getVlogAuthor(String email);
	void deleteVlog(int vlogId);
}
