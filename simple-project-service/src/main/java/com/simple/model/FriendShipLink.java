package com.simple.model;

import java.io.Serializable;
import java.util.Date;
/**
 * 友情链接
 * @author Administrator
 *
 */
public class FriendShipLink implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -8412674482244894253L;

	private Integer id;

    private String name;

    private String url; 
    
    private String link; 

    private Date createTime;
    
    

    public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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