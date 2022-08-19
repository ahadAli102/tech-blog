package com.ahad.entity;

public class Vlog {
	private int id;
	private String title;
	private String description;
	private String email;
	private String lastUpdate;

	public Vlog(int id, String title, String description, String email) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.email = email;
	}

	public Vlog(String title, String description, String email) {
		super();
		this.title = title;
		this.description = description;
		this.email = email;
	}

	public Vlog() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Override
	public String toString() {
		return "Vlog [id=" + id + ", title=" + title + ", description=" + description + ", email=" + email
				+ ", lastUpdate=" + lastUpdate + "]";
	}

}
