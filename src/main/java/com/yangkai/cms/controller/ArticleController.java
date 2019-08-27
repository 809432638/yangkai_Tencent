package com.yangkai.cms.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.yangkai.cms.VO.ArticlePicVO;
import com.yangkai.cms.domain.Article;
import com.yangkai.cms.domain.Collects;
import com.yangkai.cms.domain.User;
import com.yangkai.cms.service.ArticleService;
import com.yangkai.cms.service.CollectsService;
import com.yangkai.cms.utils.CMSConstant;
import com.yangkai.cms.utils.PageUtil;

/**
 * 
 * @ClassName: ArticleController 
 * @Description: 文章控制器
 * @author: 阿里人:杨某 
 * @date: 2019年8月22日 上午8:25:37
 */
@RequestMapping("article")
@Controller
public class ArticleController {

	private Logger logger=Logger.getLogger("ArticleController.class");
	
	/*@Resource
    private KafkaTemplate<String, String> kafkaTemplate;
	private ObjectMapper objectMapper = new ObjectMapper();*/
	
	@Resource
	private RedisTemplate<String, String> redisTemplate;
	
	@Resource
	private ArticleService articleService;

	@Resource
	private CollectsService collectsService;
	
	
	/**
	 * @Title: toPublish
	 * @Description: 去发布文章
	 * @return
	 * @return: String
	 */
	@GetMapping("toPublish")
	public String toPublish() {

		return "my/article/publish";
	}
	
	
	/**
	 * @Title: publish
	 * @Description: 发布文章
	 * @param article
	 * @param file
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@PostMapping("publish")
	public boolean publish(Article article, MultipartFile file,HttpServletRequest request) {
     //首先判断图片
		if (!file.isEmpty()) {
			String path = "d:/pic/";
			// 上传的文件原始名称
			String originalFilename = file.getOriginalFilename();
			// 为防止名称重复.文件改名为uuid
			String filename = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));

			File f = new File(path + filename);

			try {
				file.transferTo(f);// 把文件写入硬盘
				// 封装图片
				article.setPicture(filename);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        //设置文章表里的其他字段值
		article.setStatus(CMSConstant.ARTICLE_STATUS_NEW);
		article.setDeleted(0);
		article.setHot(0);
		article.setHits(0);
		
	
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute(CMSConstant.LOGIN_GENERAL);
		//session域中获取当前登陆人进行发布
		article.setUserId(user.getId());// 
	
		return articleService.insert(article) > 0;
	}
	

	
	
	/**
	 * @Title: getTitles
	 * @Description: 查询所有文章标题
	 * @param id
	 * @param model
	 * @return
	 * @return: String
	 */
	@GetMapping("getTitles")
	public String getTitles(@RequestParam(defaultValue="1")Integer page ,
			@RequestParam(defaultValue="3") Integer pageSize,Article article,
			Model model, HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		
			//查询用户自己文章
		  User user = (User) session.getAttribute(CMSConstant.LOGIN_GENERAL);
		  article.setUserId(user.getId());
		
		PageHelper.startPage(page, pageSize);
		
		List<Map> titles = articleService.getTitles(article);
		
		PageInfo<Map> pageInfo = new PageInfo<>(titles);
		String pages = PageUtil.page(page, pageInfo.getPages(), "/article/getTitles", pageSize);
		
		model.addAttribute("titles", titles);
		model.addAttribute("pages", pages);
		return "my/article/articletitles";
	}
	
	
	/**
	 * @Title: get 
	 * @Description: 文章详情
	 * @param id
	 * @param model
	 * @return
	 * @return: String
	 */
		@RequestMapping("get")
		public String get(Integer id, Model model,HttpServletRequest request) {
			//收藏的对象
			Collects collects =null;
			Map map = articleService.get(id);
			model.addAttribute("map", map);
			//获取session对象
			HttpSession session = request.getSession();
			//获取session中的用户
			User user = (User) session.getAttribute(CMSConstant.LOGIN_GENERAL);
			if(user!=null) {
				//查询当前用户的对应文章id 是否已经被收藏过
			 collects = collectsService.selectObjectByUserIdAndArticleId(user.getId(), id);
			}
			
			model.addAttribute("collects", collects);
			return "my/article/articledetail";
		}
		
		
		/**
		 * 
		 * @Title: getTitlesByAdmin 
		 * @Description: 管理员查询
		 * @param page
		 * @param pageSize
		 * @param model
		 * @param request
		 * @return
		 * @return: String
		 */
		@GetMapping("getTitlesByAdmin")
		public String getTitlesByAdmin(Article article,	@RequestParam(defaultValue="1")Integer page ,
				@RequestParam(defaultValue="5") Integer pageSize,Model model, HttpServletRequest request) {
			
			//若存在会话则返回该会话，   否则返回NULL
			HttpSession session = request.getSession(false);
					
			PageHelper.startPage(page, pageSize);
			
			List<Map> titles = articleService.getTitles(article);
			
			PageInfo<Map> pageInfo = new PageInfo<>(titles);
			String pages = PageUtil.page(page, pageInfo.getPages(), "/article/getTitlesByAdmin", pageSize);
			
			model.addAttribute("titles", titles);
			model.addAttribute("pages", pages);
			model.addAttribute("article", article);//查询条件
			return "admin/article/articletitles";
		}

	
		/**
		 * @Title: get 
		 * @Description: 文章详情
		 * @param id
		 * @param model
		 * @return
		 * @return: String
		 */
			@GetMapping("getByAdmin")
			public String getByAdmin(Integer id, Model model) {
				Map map = articleService.get(id);
				model.addAttribute("map", map);
				return "admin/article/articledetail";
			}
			
			
					
			/**
			 * 更新文章
			 * @Title: update 
			 * @Description: TODO
			 * @param article
			 * @return
			 * @return: boolean
			 */
			@ResponseBody
			@PostMapping("update")
			public  boolean update(Article article) {
			  return 	articleService.update(article)>0;
			}
	
			
		/**
		 * 	
		 * @Title: updateHit 
		 * @Description: 点击量
		 * @param id
		 * @return
		 * @return: boolean
		 */
			@ResponseBody
			@PostMapping("/updateHits")
			public boolean updateHit(Integer id,HttpServletRequest request) {
			/*	String remoteAddr = request.getRemoteAddr();
				if(redisTemplate.hasKey(remoteAddr)) {
			System.out.println("controller没有更新操作" + Thread.currentThread().getName() + "######" + Thread.currentThread().getId());
			    return false;
				}else {
					redisTemplate.opsForValue().set(remoteAddr, id+"", 3, TimeUnit.MINUTES);
					System.out.println("controller" + Thread.currentThread().getName() + "@" + Thread.currentThread().getId());
					return articleService.updateObjectHit(id)>0;
				}*/
		
		
		/*	try {
			kafkaTemplate.sendDefault("yangkai_cms_hitkafka", id+"");	
			return true;
				} catch (Exception e) {
				e.printStackTrace();
				}
				return false;
			}*/
			return articleService.updateObjectHit(id)>0;
			}			
			
}
