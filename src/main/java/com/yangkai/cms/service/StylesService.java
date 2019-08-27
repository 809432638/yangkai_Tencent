package com.yangkai.cms.service;

import java.util.Map;

/**
 * 
 * @ClassName: StylesService 
 * @Description: 设置样式service层接口
 * @author: 阿里人:杨某 
 * @date: 2019年8月22日 上午8:22:32
 */
public interface StylesService {

	/**
	 * 设置样式
	 * @Title: setStyles 
	 * @Description: TODO
	 * @param value
	 * @return
	 * @return: boolean
	 */
	boolean setStyles(String value);
	
	/**
	 * 查询
	 * @Title: selects 
	 * @Description: TODO
	 * @return
	 * @return: Map
	 */
	Map selects();
	
	
} 
