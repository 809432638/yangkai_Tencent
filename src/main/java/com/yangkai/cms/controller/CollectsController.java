package com.yangkai.cms.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yangkai.cms.domain.Article;
import com.yangkai.cms.domain.Collects;
import com.yangkai.cms.domain.User;
import com.yangkai.cms.service.CollectsService;
import com.yangkai.cms.utils.CMSConstant;
import com.yangkai.cms.utils.PageUtil;

@Controller
public class CollectsController {

	
	@Resource
	private CollectsService collectsService;
	
	@RequestMapping("/collectArticle")
	@ResponseBody
	public boolean collectArticle(Collects entity) {
		return collectsService.insertObject(entity)>0;
	}
	
	
	
	@RequestMapping("/disCollectArticle")
	@ResponseBody
	public boolean disCollectArticle(Collects entity) {
		return collectsService.deleteObjectByUserIdAndArticleId(entity.getUser().getId(),
				entity.getArticle().getId()) > 0;
	}
	
	
	
	@RequestMapping("toCollectList")
	public String toCollectList(Integer userId,HttpServletRequest request,
			@RequestParam(defaultValue="1")Integer Page,@RequestParam(defaultValue="3")Integer pageSize,Model model){
		User user = (User) request.getSession().getAttribute(CMSConstant.LOGIN_GENERAL);
		userId=user.getId();
		PageHelper.startPage(Page, pageSize);
		 List list = collectsService.selectCollectsByID(userId);
		PageInfo info=new PageInfo(list);
		String pages = PageUtil.page(Page, info.getPages(), "/toCollectList", pageSize);
		model.addAttribute("pages", pages);
		model.addAttribute("list", list);
		return "my/collects";

	}
	
	
	
	
}
