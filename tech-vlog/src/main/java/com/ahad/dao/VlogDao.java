package com.ahad.dao;

import java.util.List;
import java.util.Map;

import com.ahad.entity.User;
import com.ahad.entity.Vlog;
import com.ahad.entity.VlogRating;

/*
 * Author MD. AHAD ALI
 * Email: linkonahad10@gmail.com
 */

public interface VlogDao {
	int addVlog(Vlog vlog);

	List<Vlog> getAuthorVlogs(String email);

	Vlog getVlog(int id);

	boolean isRatingExist(VlogRating vr);

	void rateVlog(VlogRating vr);

	Map<String, Object> getVlogRating(int id);

	User getVlogAuthor(String email);

	int deleteVlog(int vlogId);

	int editvlog(int vlogId, String title, String description);

	List<Vlog> getVlogs();

	List<Vlog> getVlogs(String query);
}
