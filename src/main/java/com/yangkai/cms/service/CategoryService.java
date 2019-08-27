package com.yangkai.cms.service;

import java.util.List;

import com.yangkai.cms.domain.Category;

/**
 * 
 * @ClassName: CategoryService 
 * @Description: 栏目下面的所有的分类信息的service层接口
 * @author: 阿里人:杨某 
 * @date: 2019年8月22日 上午8:21:49
 */
public interface CategoryService {

	/**
	 * 
	 * @Title: selectObjectsByChannelId 
	 * @Description: 根据栏目的id查询该栏目下面的所有的分类信息
	 * @param channelId
	 * @return
	 * @return: List<Category>
	 */
	List<Category> selectObjectsByChannelId(Integer channelId);
}
