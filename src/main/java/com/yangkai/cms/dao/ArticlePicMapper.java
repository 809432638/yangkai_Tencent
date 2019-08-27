package com.yangkai.cms.dao;

import java.util.List;
import java.util.Map;

import com.yangkai.cms.domain.ArticlePic;
/**
 * 
 * @ClassName: ArticlePicMapper 
 * @Description: 发布图片集合dao层接口
 * @author: 阿里人:杨某 
 * @date: 2019年7月31日 上午10:26:58
 */
public interface ArticlePicMapper {
	
     /**
      * @Title: insert 
      * @Description:查询多个
      * @return
      * @return: List<Map>
      */
	List<Map> selects();
	
	/**
	 * 
	 * @Title: select 
	 * @Description:查询单个
	 * @param id
	 * @return
	 * @return: List<Map>
	 */
	 Map select(Integer id);
	 
	 /**
	  * 
	  * @Title: insert 
	  * @Description: 添加
	  * @param articlePic
	  * @return
	  * @return: int
	  */
	 int insert(ArticlePic articlePic);
	 
	 
}
