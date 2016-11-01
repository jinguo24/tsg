package com.simple.model;

import java.io.Serializable;

/**
 * 用户
 * @author Administrator
 *
 */
public class User implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1555937003009969113L;

	private Integer id;//id

    private String code;//工号
    
    private String name;//姓名

    private String password;//密码

    private Integer isSuperUser;//是否是超级用户  0：否   1:是
    
    private Integer isAdmin;//是否后台用户 0:否 1:是
     
    private Integer isAdminSuper;//是否后台超级用户 0:否 1:是
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getIsSuperUser() {
        return isSuperUser;
    }

    public void setIsSuperUser(Integer isSuperUser) {
        this.isSuperUser = isSuperUser;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Integer getIsAdminSuper() {
		return isAdminSuper;
	}

	public void setIsAdminSuper(Integer isAdminSuper) {
		this.isAdminSuper = isAdminSuper;
	}
}