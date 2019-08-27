package com.yangkai.cms.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
/**
 * 
 * @ClassName: StylesMapper 
 * @Description: 枚举接口
 * @author: 阿里人:杨某 
 * @date: 2019年7月31日 上午10:39:42
 */
public interface StylesMapper {
	
	/**
	 * 
	 * @Title: insert 
	 * @Description:添加
	 * @param value
	 * @return
	 * @return: int
	 */
  @Insert("insert into cms_styles(styles) values(#{value})")
	int insert(String value);
	
	/**
	 * 
	 * @Title: selects 
	 * @Description: 查询
	 * @return
	 * @return: Map
	 */
	@Select("select * from cms_styles")
	Map selects();
	
	
	/**
	 * 
	 * @Title: deletes 
	 * @Description:删除
	 * @return
	 * @return: int
	 */
	@Delete("delete from cms_styles")
	int deletes();

}
