package com.simple.model;

import java.util.Date;

public class Picture  extends AbstractBaseModel{

	private static final long serialVersionUID = 6024785169809433293L;

    private String name;//名称

    private String url;//图片地址

    private Date createTime;//创建时间

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}