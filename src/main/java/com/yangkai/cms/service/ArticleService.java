package com.yangkai.cms.service;

import java.util.List;
import java.util.Map;

import com.yangkai.cms.domain.Article;

/**
 * 
 * @ClassName: ArticleService 
 * @Description: 发怒文章service层接口
 * @author: 阿里人:杨某 
 * @date: 2019年8月22日 上午8:21:30
 */
public interface ArticleService {
	
	
	/**
	 * 
	 * @Title: updateObjectHit
	 * @Description: TODO
	 * @param article
	 * @return
	 * @return: int
	 */
	int updateObjectHit(Integer id);
	
	/**
	 * @Title: insert 
	 * @Description: 发布文章
	 * @param article
	 * @return
	 * @return: int
	 */
	int insert(Article article);
	
	
	
	/**
	 * @Title: getTitles 
	 * @Description: 按照条件查询所有文章的标题
	 * @param Article article
	 * @return
	 * @return: List<Map>
	 */
	List<Map> getTitles(Article article);
	
	
	
	/**
	 * @Title: get 
	 * @Description: 文章详情
	 * @param id
	 * @return
	 * @return: Map
	 */
	Map get(Integer id);
	
	
	/**
	 * @Title: update 
	 * @Description:  更新文章
	 * @param article
	 * @return
	 * @return: int
	 */
	int update(Article article);



}
