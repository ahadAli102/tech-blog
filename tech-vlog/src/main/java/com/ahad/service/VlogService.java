package com.ahad.service;

import java.util.List;

import com.ahad.entity.Vlog;

public interface VlogService {
	int addVlog(String title,String description,String email);
	List<Vlog> getVlogs(String email);
}
