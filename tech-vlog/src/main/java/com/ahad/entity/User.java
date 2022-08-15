package com.ahad.entity;

public class User {
	private String name;
	private String email;
	private String password;
	private String rePassword;
	private String condition;

	public User(String name, String email, String password, String rePassword, String condition) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.rePassword = rePassword;
		this.condition = condition;
	}
	
	public User(String name, String email, String password, String condition) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.condition = condition;
	}
	

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	@Override
	public String toString() {
		return "User [ name=" + name + ", email=" + email + ", password=" + password + "]";
	}

}
