package com.simple.model;
/**
 * 用户
 * @author Administrator
 *
 */
public class User {
    private Integer id;//id

    private String code;//工号
    
    private String name;//姓名

    private String password;//密码

    private Integer isSuperUser;//是否是超级用户  0：否   1:是

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
    
    
}