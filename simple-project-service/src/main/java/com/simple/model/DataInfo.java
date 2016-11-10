package com.simple.model;

import io.searchbox.annotations.JestId;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.simple.common.util.DateUtil;

public class DataInfo implements Serializable{

	private static final long serialVersionUID = -4749935976851932407L;

	@JestId
	private Integer id;

    private String name;//资料名称
    
    private String bookName;//书名

    private String desc;//描述
    
    private String image;//封面地址
    
    private Integer type;//类型 0:外部资料 1：内部资料

    private String fileUrl;//资料地址
    
    private String periodicalTime;//期刊时间
    
    private String authors;//作者

    private String tags;//标签

    private Date createTime;//创建时间

    private Integer readCount;//阅读数量

    private Integer downloadCount;//下载数量
    
    private String category;//分类
    
    private Date publishDate;//出版时间
    private String commitDate;//提交的出版时间
    private String pulishDateStr;
    
    
    private String[] authorList;
    
    private String[] tagsList;
    
    
    
	public String[] getAuthorList() {
		authorList = new String[]{};
		if(StringUtils.isNotEmpty(authors)){
			authorList = authors.split(",");
		}
		return authorList;
	}

	public String[] getTagsList() {
		tagsList = new String[]{};
		if(StringUtils.isNotEmpty(tags)){
			tagsList = tags.split(tags);
		}
		return tagsList;
	}



	public Integer getId() {
        return id;
    }

    public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getPeriodicalTime() {
		return periodicalTime;
	}

	public void setPeriodicalTime(String periodicalTime) {
		this.periodicalTime = periodicalTime;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
		if ( null != publishDate) {
			this.pulishDateStr = DateUtil.date2String(publishDate);
		}
	}

	public String getPulishDateStr() {
		return pulishDateStr;
	}

	public String getCommitDate() {
		return commitDate;
	}

	public void setCommitDate(String commitDate) {
		this.commitDate = commitDate;
	}
}