package com.proyecto.prueba_datatools;

public class UserLoginInfo {
	private int id;
	private int user_id;
	private String username;
	private String password;
	private boolean hashed = false;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isHashed() {
		return hashed;
	}
	public void setHashed(boolean hashed) {
		this.hashed = hashed;
	}
	
}
