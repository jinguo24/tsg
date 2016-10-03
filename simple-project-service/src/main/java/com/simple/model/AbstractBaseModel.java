package com.simple.model;

import com.simple.common.config.EnvPropertiesConfiger;

public class AbstractBaseModel implements java.io.Serializable{

	private static final String FILE_PREFIX = EnvPropertiesConfiger.getValue("fileUrl");
	
	private static final long serialVersionUID = -522832123597167225L;
	
	protected int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public AbstractBaseModel() {
	}
	
	public String getImagePath(String path) {
		if (null != path) {
			if (!path.startsWith("http://")) {
				path = path.replace("\\", "/").replaceAll("/{2,}", "/");
				if (path.startsWith("/")) {
					path = path.substring(1);
				}
				path = FILE_PREFIX+path;
			}else {
				path = path.replace("http://", "");
				path = path.replace("\\", "/").replaceAll("/{2,}", "/");
				if (path.startsWith("/")) {
					path = path.substring(1);
				}
				path = "http://"+path;
			}
		}
		return path;
	}
}
