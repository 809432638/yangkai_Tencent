package com.yangkai.cms.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.yangkai.cms.VO.ArticlePicVO;
import com.yangkai.cms.domain.Article;
import com.yangkai.cms.domain.Category;
import com.yangkai.cms.domain.Channel;
import com.yangkai.cms.service.ArticlePicService;
import com.yangkai.cms.service.ArticleService;
import com.yangkai.cms.service.CategoryService;
import com.yangkai.cms.service.ChannelService;
import com.yangkai.cms.service.StylesService;
import com.yangkai.cms.utils.CMSConstant;
import com.yangkai.cms.utils.ESUtils;

/**
 * 
 * @ClassName: IndexController 
 * @Description: 首页控制器
 * @author: 阿里人:杨某 
 * @date: 2019年8月22日 上午8:26:09
 */
@Controller
public class IndexSearchController {
	
	
	@Resource
	private ChannelService channelService;
	
	@Resource
	private  ArticleService articleService;
	
	@Resource
	private ArticlePicService articlePicService;
    
	@Resource
	private StylesService stylesService;
	
	
	@Resource
	private CategoryService categoryService;
	
	@Resource
	private ElasticsearchTemplate elasticsearchTemplate;
	
	/**
	 * @Title: toIndex
	 * @Description: 进入首页
	 * @return
	 * @return: String
	 * @throws InterruptedException
	 */
	@GetMapping({ "/search" })
	public String toIndex(@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="5")Integer rows,String key,Integer channelId,Article article, Model model) throws InterruptedException {
		//查询条件 所有显示的文章都是审核过的
		article.setStatus(CMSConstant.ARTICLE_STATUS_CHECKED);
		 //封装到model
		model.addAttribute("article", article);
		
				Map map = stylesService.selects();
				model.addAttribute("styles", map);
	
				//定义6个线程
				Thread t1 = null;
				Thread t2 = null;
				Thread t3 = null;
				Thread t4 = null;
				Thread t5 = null;
				Thread t6 = null;
				
				t1=new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						// 返回所有栏目
						 List<Channel> channels = channelService.selectObjects();
						    //存储到Model中
							model.addAttribute("channels", channels);
					}
				});
				
	   t2=new Thread(new Runnable() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
		//调用ElasticSearch
			AggregatedPage<?> pageinfo = ESUtils.selectObjects(elasticsearchTemplate, Article.class, page, rows, new String[] {"title","content"}, key);
		model.addAttribute("pageinfo", pageinfo);
		model.addAttribute("page", page);
		model.addAttribute("key", key);
		}
	});
			
	
		
		t3=new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println(channelId+"================================");
				   // 查询栏目下的所有分类
		 		if (null!=channelId && channelId!=0) {
		 			List<Category> categorys = categoryService.selectObjectsByChannelId(channelId);
					model.addAttribute("categorys", categorys);
				}
			}
		});
		
		 	
		t4=new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
	             //  具体栏目或分类下的文章
					List<Map> articles = articleService.getTitles(article);
					model.addAttribute("articles", articles);
			}
		});
		

	    t5=new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				  //获取最新文章
				int pageSize = 10;
				PageHelper.startPage(0, pageSize);
				List<Map> lasts = articleService.getTitles(null);
				model.addAttribute("lasts", lasts);
			}
		});
		    

		    
	    t6 =new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub

	              // 获取最新图片
					//引入gosn.它可以把java对象转为json,也可以把json转为对象
					 Gson gson = new Gson();
					List<ArticlePicVO> pics = new ArrayList<>();
					 //获取所有的图片集
					List<Map> maps = articlePicService.selects();
					
				   //遍历图片集
					for (Map m : maps) {
						//获取图片集的内容 即:mysql表中 centent字段存储的json数据
						String str = (String) m.get("content");
						//解析为json对象
						JsonElement jsonElement = new JsonParser().parse(str);
						JsonArray jsonArray = jsonElement.getAsJsonArray();
						 for(JsonElement element :jsonArray) {
							 //把json转为 java对象
							ArticlePicVO vo = gson.fromJson(element, ArticlePicVO.class);
							//获取图片集的ID
							vo.setId((Integer)m.get("id"));
							//封装到集合
							pics.add(vo);
							break;//只获取图片集的第一个
						 }
					}
					model.addAttribute("pics", pics);
			}
		});

	  //运行线程
	  		t1.start();
	  		t2.start();
	  		t3.start();
	  		t4.start();
	  		t5.start();
	  		t6.start();
	  		
	 //让线程都执行完,主线程最后执行
	  		t1.join();
	  		t2.join();
	  		t3.join();
	  		t4.join();
	  		t5.join();
	  		t6.join();
	    
		return "index/index_search";
	}
	 

}
