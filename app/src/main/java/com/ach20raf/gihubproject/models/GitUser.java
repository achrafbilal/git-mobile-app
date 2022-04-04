package com.ach20raf.gihubproject.models;

public class GitUser {
	private int id;
	private String login;
	private String avatar_url;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getAvatar_url() {
		return avatar_url;
	}
	
	public void setAvatar_url(String avatar_url) {
		this.avatar_url = avatar_url;
	}
}
