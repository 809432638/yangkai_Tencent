package com.yangkai.cms.domain;

import java.io.Serializable;
/**
 * 
 * @ClassName: Category 
 * @Description: 栏目下面具体分类的类
 * @author: 阿里人:杨某 
 * @date: 2019年7月25日 下午1:57:14
 */
public class Category implements Serializable{
	
    /**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String name;

    private Integer channelId;

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

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }
}