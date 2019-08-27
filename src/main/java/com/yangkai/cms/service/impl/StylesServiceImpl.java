package com.yangkai.cms.service.impl;

import java.awt.color.CMMException;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yangkai.cms.dao.StylesMapper;
import com.yangkai.cms.service.StylesService;
import com.yangkai.cms.utils.CMSRuntimeException;
/**
 * 
 * @ClassName: StylesServiceImpl 
 * @Description: 枚举样式service层接口实现类
 * @author: 阿里人:杨某 
 * @date: 2019年7月31日 上午10:40:22
 */
@Service
public class StylesServiceImpl implements StylesService {

	@Resource
	private StylesMapper stylesMapper;

	@Override
	public boolean setStyles(String value) {
		// TODO Auto-generated method stub
		try {
			//先删除已有的样式
			stylesMapper.deletes();
			//在添加新的样式
			stylesMapper.insert(value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new CMSRuntimeException("设置失败");
		}
	}

	@Override
	public Map selects() {
		// TODO Auto-generated method stub
	    return stylesMapper.selects();
	}
	
		
	}




