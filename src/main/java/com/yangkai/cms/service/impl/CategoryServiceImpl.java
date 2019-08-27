package com.yangkai.cms.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.yangkai.cms.dao.CategoryMapper;
import com.yangkai.cms.domain.Category;
import com.yangkai.cms.service.CategoryService;
import com.yangkai.cms.utils.CMSConstant;

/**
 * 
 * @ClassName: CategoryServiceImpl 
 * @Description: 栏目下分类的service层接口实现类
 * @author: 阿里人:杨某 
 * @date: 2019年8月22日 上午8:24:19
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

	private  Logger logger=Logger.getLogger(CategoryServiceImpl.class);
	
	@Resource
	private CategoryMapper categoryMapper;
	
	
	@Resource
	private RedisTemplate<String, Category> redisTemplate;
	

	@Override
	public List<Category> selectObjectsByChannelId(Integer channelId) {
		// TODO Auto-generated method stub
		
		//为防止空指针定义list集合
		List<Category> list = new ArrayList<>();
		
		// 检测redis中存在栏目的hash缓存吗
		if (redisTemplate.hasKey(CMSConstant.REDIS_KEY_CHANNEL_HASH)) {
			// 检测redis的hash中存在指定的栏目的分类缓存吗
			if (redisTemplate.opsForHash().hasKey(CMSConstant.REDIS_KEY_CHANNEL_HASH,
					CMSConstant.REDIS_KEY_CHANNEL_HASH_KEY + channelId)) {
				// 造型集合
				list = (List<Category>) redisTemplate.opsForHash().get(CMSConstant.REDIS_KEY_CHANNEL_HASH,CMSConstant.REDIS_KEY_CHANNEL_HASH_KEY + channelId);
		           	logger.info("*************************栏目下的分类数据从redis中取出");
			} else {
				// 从数据库查询中当前栏目所有的分类信息
				list = categoryMapper.selectObjectsByChannelId(channelId);
				// 存入到hash中
				redisTemplate.opsForHash().put(CMSConstant.REDIS_KEY_CHANNEL_HASH,
						CMSConstant.REDIS_KEY_CHANNEL_HASH_KEY + channelId, list);
			}
		} else {
			// 从数据库查询中当前栏目所有的分类信息
			list = categoryMapper.selectObjectsByChannelId(channelId);
			// 存入到hash中
			redisTemplate.opsForHash().put(CMSConstant.REDIS_KEY_CHANNEL_HASH,
					CMSConstant.REDIS_KEY_CHANNEL_HASH_KEY + channelId, list);
		}

		return list;
	}

}