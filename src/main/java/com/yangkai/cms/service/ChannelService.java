package com.yangkai.cms.service;

import java.util.List;
import java.util.Map;

import com.yangkai.cms.domain.Channel;

/**
 * 
 * @ClassName: ChannelService 
 * @Description: 栏目和分类service层接口
 * @author: charles
 * @date: 2019年7月23日 上午11:03:15
 */
public interface ChannelService {

	
	/** 
	 * @Title: selectObjects 
	 * @Description:所有栏目(存储到redis中)
	 * @return
	 * @return: List<Channel>
	 */
	List<Channel> selectObjects();
	
	
	/**
	 * 
	 * @Title: selectChannels 
	 * @Description: 所有栏目
	 * @return
	 * @return: List<Map>
	 */
	List<Map> selectChannels();
	/**
	 * 
	 * @Title: selectCategorys 
	 * @Description: 根据栏目查询其下分类
	 * @param channelId
	 * @return
	 * @return: List<Map>
	 */
	List<Map> selectCategorys(Integer channelId);
}
 