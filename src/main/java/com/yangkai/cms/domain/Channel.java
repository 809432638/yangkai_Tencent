package com.yangkai.cms.domain;

import java.io.Serializable;
/**
 * 
 * @ClassName: Channel 
 * @Description: 栏目类
 * @author: 阿里人:杨某 
 * @date: 2019年7月25日 下午1:57:34
 */
public class Channel implements Serializable{
  
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String name;

    private String description;

    private String icon;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }
}