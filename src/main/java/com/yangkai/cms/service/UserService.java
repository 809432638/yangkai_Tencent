package com.yangkai.cms.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yangkai.cms.VO.UserVO;
import com.yangkai.cms.dao.UserMapper;
import com.yangkai.cms.domain.User;

/**
 * 
 * @ClassName: UserService 
 * @Description: 用户service层接口
 * @author: 阿里人:杨某 
 * @date: 2019年8月22日 上午8:22:52
 */
@Service
public interface UserService {

	/**
	 * @Title: insert 
	 * @Description: 用户注册
	 * @param user
	 * @return
	 * @return: int
	 */
	boolean insert(UserVO userVO);
	
	
	/**
	 * @Title: selectByUsername 
	 * @Description:  根据用户名查询对象
	 * @param username
	 * @return
	 * @return: User
	 */
	User selectByUsername(String username);
	
	
	/**
	 * @Title: login 
	 * @Description: 登录
	 * @param username
	 * @param password
	 * @return
	 * @return: boolean
	 */
	User login(String username,String password);
	
	
	/**
	 * @Title: selects 
	 * @Description: 根据条件查询用户信息
	 * @param map
	 * @return
	 * @return: List<Map>
	 */
	List<Map> selects(Map map);
	
	
	/**
	 * @Title: updateStatus 
	 * @Description: 改变用户的登录状态 .禁用或启用
	 * @param id
	 * @param locked
	 * @return
	 * @return: int
	 */
	int updateLocked(Integer id,Integer locked);
	
}
