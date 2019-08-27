package com.yangkai.cms.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yangkai.cms.service.ChannelService;


/**
 * 
 * @ClassName: ChannelController 
 * @Description: 栏目控制器
 * @author: 阿里人:杨某 
 * @date: 2019年8月22日 上午8:25:56
 */
@Controller
public class ChannelController {
	
	@Resource
	private ChannelService channelService;
	
	/**
	 * 
	 * @Title: selectChannels 
	 * @Description: 返回所有栏目
	 * @return
	 * @return: List<Map>
	 */
	@ResponseBody
	@GetMapping("selectChannels")
	public List<Map> selectChannels(){
		return channelService.selectChannels();
	}
	
	
	/**
	 * @Title: selectCategorys 
	 * @Description: 根据栏目ID返回分类
	 * @param channelId
	 * @return
	 * @return: List<Map>
	 */
	@ResponseBody
	@GetMapping("selectCategorys")
	public List<Map> selectCategorys(Integer channelId){
		return channelService.selectCategorys(channelId);
	}

}
