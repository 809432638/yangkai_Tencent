package com.yangkai.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yangkai.cms.dao.CollectsMapper;
import com.yangkai.cms.domain.Article;
import com.yangkai.cms.domain.Collects;
import com.yangkai.cms.service.CollectsService;

@Service("collectsService")
public class CollectsServiceImpl implements CollectsService{
	
	@Resource
	private CollectsMapper collectsMapper;

	@Override
	public PageInfo<Collects> selectObjectsByUserId(Integer pageNum,Integer pageSize,Integer userId) {
		PageHelper.startPage(pageNum, pageSize);
		List<Collects> list = collectsMapper.selectObjectsByUserId(userId);
		return new PageInfo<>(list);
	}

	@Override
	public Collects selectObjectByUserIdAndArticleId(Integer userId, Integer articleId) {
		// TODO Auto-generated method stub
		return collectsMapper.selectObjectByUserIdAndArticleId(userId, articleId);
	}

	@Override
	public int deleteObjectByUserIdAndArticleId(Integer userId, Integer articleId) {
		// TODO Auto-generated method stub
		return collectsMapper.deleteObjectByUserIdAndArticleId(userId, articleId);
	}

	@Override
	public int insertObject(Collects entity) {
		// TODO Auto-generated method stub
		return collectsMapper.insertObject(entity);
	}

	@Override
	public List selectCollectsByID(Integer userId) {
		// TODO Auto-generated method stub
		return collectsMapper.selectCollectsByID(userId);
	}



}
