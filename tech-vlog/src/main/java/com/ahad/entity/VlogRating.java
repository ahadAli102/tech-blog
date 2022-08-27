package com.ahad.entity;

/*
 * Author MD. AHAD ALI
 * Email: linkonahad10@gmail.com
 */

public class VlogRating {
	private int vlogId;
	private String raterEmail;
	private int vlogRating;

	public VlogRating(int vlogId, String raterEmail, int vlogRating) {
		super();
		this.vlogId = vlogId;
		this.raterEmail = raterEmail;
		this.vlogRating = vlogRating;
	}

	public VlogRating() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getVlogId() {
		return vlogId;
	}

	public void setVlogId(int vlogId) {
		this.vlogId = vlogId;
	}

	public String getRaterEmail() {
		return raterEmail;
	}

	public void setRaterEmail(String raterEmail) {
		this.raterEmail = raterEmail;
	}

	public int getVlogRating() {
		return vlogRating;
	}

	public void setVlogRating(int vlogRating) {
		this.vlogRating = vlogRating;
	}

	@Override
	public String toString() {
		return "VlogRating [vlogId=" + vlogId + ", raterEmail=" + raterEmail + ", vlogRating=" + vlogRating + "]";
	}

}
