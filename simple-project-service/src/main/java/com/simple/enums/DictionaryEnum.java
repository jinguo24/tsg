package com.simple.enums;

public enum DictionaryEnum {

	TEMPLATE_TYPE("t_type","模版分类"),TEMPLATE_CATEGORY("t_category","模版类型");
	
	private DictionaryEnum(String key, String name) {
		this.key = key;
		this.name = name;
	}
	
	private String key;
	private String name;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
