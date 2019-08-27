package com.yangkai.cms.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yangkai.cms.dao.ArticlePicMapper;
import com.yangkai.cms.domain.ArticlePic;
import com.yangkai.cms.service.ArticlePicService;

/**
 * 
 * @ClassName: ArticlePicServiceImpl 
 * @Description:  发布图片集合的service层接口实现类
 * @author: 阿里人:杨某 
 * @date: 2019年7月31日 上午10:27:39
 */
@Service
public class ArticlePicServiceImpl implements ArticlePicService {

	@Resource
	private ArticlePicMapper articlePicMapper;
	
	@Override
	public List<Map> selects() {
		// TODO Auto-generated method stub
		return articlePicMapper.selects();
	}

	@Override
	public 	Map select(Integer id) {
		return  articlePicMapper.select(id);
	}

	@Override
	public int insert(ArticlePic articlePic) {
		// TODO Auto-generated method stub
		return articlePicMapper.insert(articlePic);
	}

}
