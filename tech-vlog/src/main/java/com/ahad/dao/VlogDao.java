package com.ahad.dao;

import java.util.List;

import com.ahad.entity.Vlog;

public interface VlogDao {
	int addVlog(Vlog vlog);
	List<Vlog> getVlogs(String email);
}
