package com.yf.entity;

import java.io.Serializable;
import java.util.List;

public class Role implements Serializable {

    private Integer id;
    // 角色标识程序中判断使用,如"admin"
    private String role;
    
    private Integer userid;
    
    
    
	public Role() {
	}


	public Role(String role, Integer userid) {
		this.role = role;
		this.userid = userid;
	}


	public Role(Integer id, String role, Integer userid) {
		this.id = id;
		this.role = role;
		this.userid = userid;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public Integer getUserid() {
		return userid;
	}


	public void setUserid(Integer userid) {
		this.userid = userid;
	}


	@Override
	public String toString() {
		return "Role [id=" + id + ", role=" + role + ", userid=" + userid + "]";
	}
    
	
    
}
