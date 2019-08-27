package com.yangkai.cms.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yangkai.cms.service.StylesService;
import com.yangkai.cms.utils.TileEnum;

/**
 * 
 * @ClassName: StylesController 
 * @Description: 样式设置控制器
 * @author: 阿里人:杨某 
 * @date: 2019年7月31日 上午11:04:39
 */
@RequestMapping("styles")
@Controller
public class StylesController {
	
	@Resource
	private StylesService stylesService;
	/**
	 * 进入样式设置页面
	 * @Title: settings 
	 * @Description: TODO
	 * @param model
	 * @return
	 * @return: String
	 */
	@GetMapping("settings")
	public String settings(Model model) {
		//获取枚举类的所有值
		TileEnum[] styles = TileEnum.values();
		
		model.addAttribute("styles", styles);
		
		//从数据库获取当前已有的样式
		Map map = stylesService.selects();
		model.addAttribute("map", map);
		
		return "admin/settings";
	}
	
	
	
	/**
	 * 设置样式
	 * @Title: save 
	 * @Description: TODO
	 * @param styles
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@PostMapping("settings")
	public boolean save(String styles) {
		 return stylesService.setStyles(styles);
	}
	

	
}
