package com.yf.entity;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private Integer uid;
    private String name;
    private String password;
    
    
	public User() {
	}

	public User(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public User(Integer uid, String name, String password) {
		this.uid = uid;
		this.name = name;
		this.password = password;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", name=" + name + ", password=" + password + "]";
	} 
   
    
	
    
}
