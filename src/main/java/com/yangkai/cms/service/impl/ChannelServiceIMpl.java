package com.yangkai.cms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.yangkai.cms.dao.ChannelMapper;
import com.yangkai.cms.domain.Channel;
import com.yangkai.cms.service.ChannelService;
import com.yangkai.cms.utils.CMSConstant;

/**
 * 
 * @ClassName: ChannelServiceIMpl 
 * @Description: 栏目service层接口实现类
 * @author: 阿里人:杨某 
 * @date: 2019年8月22日 上午8:24:42
 */
@Service
public class ChannelServiceIMpl implements ChannelService {

   private  Logger logger=Logger.getLogger(ChannelServiceIMpl.class);
	
	@Resource
	private ChannelMapper channelMapper;

	
	 @Resource
	 private RedisTemplate<String, Channel> redisTemplate;
	
	
	@Override
	public List<Map> selectChannels() {
		// TODO Auto-generated method stub
		return channelMapper.selectChannels();
	}

	@Override
	public List<Map> selectCategorys(Integer channelId) {
		// TODO Auto-generated method stub
		return channelMapper.selectCategorys(channelId);
	}

	@Override
	public List<Channel> selectObjects() {
		// TODO Auto-generated method stub
		  //查询出所有的栏目
		
		List<Channel> list = new ArrayList<Channel>();
		
		//检测redis中是否存在 栏目的缓存	
		if(redisTemplate.hasKey(CMSConstant.REDIS_KEY_CHANNEL_LIST)) {
			//如果有,从redis中取出来 
			list=redisTemplate.opsForList().range(CMSConstant.REDIS_KEY_CHANNEL_LIST, 0, -1);
			logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@栏目数据从redis中取出");
		}else {
			 //从数据库中查询出栏目
			list = channelMapper.selectObjects();
			//存储到redis中
			redisTemplate.opsForList().leftPushAll(CMSConstant.REDIS_KEY_CHANNEL_LIST, list);
			//设置存储的有效期(一天)
			redisTemplate.expire(CMSConstant.REDIS_KEY_CHANNEL_LIST, 1, TimeUnit.DAYS);
		}
		return list;
	}

}
