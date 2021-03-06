package com.yangkai.cms.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.helpers.MDCKeySetExtractor;
import org.springframework.stereotype.Service;

import com.yangkai.cms.VO.UserVO;
import com.yangkai.cms.dao.UserMapper;
import com.yangkai.cms.domain.User;
import com.yangkai.cms.service.UserService;
import com.yangkai.cms.utils.CMSConstant;
import com.yangkai.cms.utils.Md5Util;
import com.yangkai_Assset.utils.AsssetYangkai;
import com.yangkai_Assset.utils.CmsException;

/**
 * 
 * @ClassName: UserServiceImpl 
 * @Description: 用户service层接口实现类
 * @author: 阿里人:杨某 
 * @date: 2019年8月22日 上午8:25:14
 */
@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;

	
	
	@Override
	public boolean insert(UserVO userVO) {
		// TODO Auto-generated method stub
		//具体业务
				//1两次密码是否相同
				if(!userVO.getPassword().equals(userVO.getRepassword()))
				  throw new CmsException("两次密码不一致");
				//校验用户名是否唯一
				User user = userMapper.selectByUsername(userVO.getUsername());
				if(null!=user)
				throw new CmsException("用户名已经存在");	
				
				
				userVO.setLocked(CMSConstant.USER_STATUS_UNLOCKED);//默认账户正常0   1:禁用
				userVO.setRole(CMSConstant.RULE_GENERAL);//默认注册账户为普通用户 0    1:为管理员
				//如果用户昵称为空,则默认为用户名
				if(null==userVO.getNickname()){
					userVO.setNickname(userVO.getUsername());	
				}
				
				userVO.setPassword(Md5Util.md5Encoding(userVO.getPassword()));
				return  userMapper.insert(userVO)>0;
	}

	
	
	@Override
	public User selectByUsername(String username) {
		// TODO Auto-generated method stub
        User user = userMapper.selectByUsername(username);
		return user;
	}

	

	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		AsssetYangkai.hasText(username, "用户名不能为空");
		AsssetYangkai.hasText(password, "密码不能为空");
		
		//用登录名找对象.
		User user = userMapper.selectByUsername(username);
		
		//调用断言工具栏进行判断
		AsssetYangkai.notNull(user,"用户名不存在");
		AsssetYangkai.isTrue(user.getLocked()==0, "账号被禁用");
		AsssetYangkai.isTrue(user.getPassword().equals(Md5Util.md5Encoding(password)), "密码错误!");

		return user;
	}
	
	
	@Override
	public List<Map> selects(Map map) {
		// TODO Auto-generated method stub
		//只查询普通注册用户
				map.put("RULE_GENERAL", CMSConstant.RULE_GENERAL);
				return userMapper.selects(map);
	}

	
	
	@Override
	public int updateLocked(Integer id, Integer locked) {
		// TODO Auto-generated method stub
	    return userMapper.updateLocked(id, locked);
	}

	
	

	
	
}
