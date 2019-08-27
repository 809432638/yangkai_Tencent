package com.yangkai.cms.dao;

import java.util.List;
import java.util.Map;

import com.yangkai.cms.domain.Channel;

/** 
 * @ClassName: ChannelMapper 
 * @Description: 栏目dao层接口
 * @author: 阿里人:杨某 
 * @date: 2019年8月20日 下午1:36:29  
 */
public interface ChannelMapper {

	
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
 