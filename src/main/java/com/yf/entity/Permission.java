package com.yf.entity;

import java.io.Serializable;
import java.util.List;

public class Permission implements Serializable {
    private Integer id;

    private String permission;

    private Integer roleid;

    
	public Permission() {
	}


	public Permission(Integer id, String permission, Integer roleid) {
		this.id = id;
		this.permission = permission;
		this.roleid = roleid;
	}


	public Permission(String permission, Integer roleid) {
		this.permission = permission;
		this.roleid = roleid;
	}

	

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getPermission() {
		return permission;
	}


	public void setPermission(String permission) {
		this.permission = permission;
	}


	public Integer getRoleid() {
		return roleid;
	}


	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}


	@Override
	public String toString() {
		return "Permission [id=" + id + ", permission=" + permission + ", roleid=" + roleid + "]";
	}
    
    
	
}