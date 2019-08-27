package com.yangkai.cms.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yangkai.cms.dao.ArticleMapper;
import com.yangkai.cms.domain.Article;
import com.yangkai.cms.service.ArticleService;

/**
 *  //线程池使用@Async 
 * @ClassName: ArticleServiceImpl 
 * @Description: 发布文章service层接口实现类
 * @author: 阿里人:杨某 
 * @date: 2019年8月22日 上午8:24:00
 */
@Service
public class ArticleServiceImpl implements ArticleService {

 
	@Resource
	private ArticleMapper articleMapper;
	
	
	//<!-- 发布文章 -->
	@Override
	public int insert(Article article) {
		// TODO Auto-generated method stub
		return articleMapper.insert(article);
	}
	
	
	//查询所有文章
	@Override
	public List<Map> getTitles(Article article) {
		// TODO Auto-generated method stub
		return articleMapper.getTitles(article);
	}
	

	@Override
	public Map get(Integer id) {
		// TODO Auto-generated method stub
		return articleMapper.get(id);
	}
	
	
	@Override
	public int update(Article article) {
		// TODO Auto-generated method stub
		return articleMapper.update(article);
	}

	

	
	@Override
	public int updateObjectHit(Integer id) {
    System.out.println("service"+Thread.currentThread().getName()+"#####"+Thread.currentThread().getId());
		return articleMapper.updateObjectHit(id);
	}


}
