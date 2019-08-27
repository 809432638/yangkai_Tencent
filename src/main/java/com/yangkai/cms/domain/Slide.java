package com.yangkai.cms.domain;

import java.io.Serializable;
/**
 * 
 * @ClassName: Slide 
 * @Description: TODO
 * @author: 阿里人:杨某 
 * @date: 2019年7月25日 下午1:57:53
 */
public class Slide implements Serializable {
    /**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String title;

    private String picture;

    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
}