package com.yangkai.cms.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yangkai.cms.domain.Article;
import com.yangkai.cms.domain.Category;
import com.yangkai.cms.domain.Channel;
import com.yangkai.cms.service.ArticleService;
import com.yangkai.cms.service.CategoryService;
import com.yangkai.cms.service.ChannelService;
import com.yangkai.cms.utils.IKWord;
import com.yangkai_Assset.utils.DateUtils;
import com.yangkai_Assset.utils.RandomUtil;

/**
 * 
 * @ClassName: KafkaProducer 
 * @Description: 生产者测试类走卡夫卡
 * @author: 阿里人:杨某 
 * @date: 2019年8月21日 下午3:52:01
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-beans.xml","classpath:spring-producer.xml"})
public class KafkaProducer {
     
	@Resource
	private KafkaTemplate<String, String> kafkaTemplate;
	
    private ObjectMapper objectMapper=new ObjectMapper();
	
	private Logger logger=Logger.getLogger(KafkaProducer.class);
	
	@Resource
	private ChannelService channelService;
	
	@Resource
	private CategoryService categoryService;
	
	@Resource
	private ArticleService articleService;
	
	@Test
	public void test_import() throws IOException {
	
		File f = new File("D:/pic/");
		File[] pics = f.listFiles();
		
		//定义时间变量
         Calendar c = Calendar.getInstance();
           c.set(2018, 0, 1, 0, 0, 0);
		Date times = c.getTime();
		
		//指定目录创建文件
		File src = new File("D:\\1703ajsoup");
		// 遍历目录中的文件
		File[] listFiles = src.listFiles();
		for(int index=0;index<listFiles.length;index++) {
			// 获取具体文件对象
			File file = listFiles[index];
			// 创建文件的输入流
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf8"));
			// 定义缓冲区
			StringBuffer sb=new StringBuffer();
			//定义读取的内容
			String content=null;
			//循环读取
			while((content=br.readLine())!=null) {
				// 加入sb中
				sb.append(content);	
			}
			//读取文章的内容
			String article_content = sb.toString();
			//获取文章的标题
			String article_title = file.getName().substring(0, file.getName().length()-4);
			
			//创建文章对象
			Article article = new Article();
			
			// 设置文章的标题
			article.setTitle(article_title);
			// 设置文章的内容
			article.setContent(article_content);
			// 随机点击量
			article.setHits(RandomUtil.random(0, 10000));
			//随机时间
			article.setCreated(DateUtils.randomDate(new Date(1992, 1, 15)));
			//随机热门
			article.setHot(RandomUtil.random(0, 1));
			
			// 所有栏目
			List<Channel> channes = channelService.selectObjects();
			//随机生成一个栏目集合的下标
			int channel_index = RandomUtil.random(0, channes.size()-1);
			// 获取栏目对象
			Channel channel = channes.get(channel_index);
			
			// 随机的栏目id
		     article.setChannelId(channel.getId());
			
			// 分类随机,建立在栏目的基础上
			// 查出当前栏目下面的所有分类信息
			List<Category> categories = categoryService.selectObjectsByChannelId(channel.getId());
			//栏目下面 判断是否有分类
			if(categories!=null && categories.size()>0) {
				//随机生成一个分类集合的下标
				int category_index = RandomUtil.random(0, categories.size()-1);
				// 获取分类对象
				Category category = categories.get(category_index);
				// 随机栏目下面的随机的分类id
				article.setCategoryId(category.getId());
			}
			
			// 设置删除的状态
			article.setDeleted(0);
			// 设置状态
			article.setStatus(1);

			//设置文章的描述词，统计的设置
			String descriptions=null;
			List<Entry<String,Integer>> list = IKWord.order(IKWord.count(new HashMap<>(),article_content));
			if(list!=null && list.size()>0) {
				if(list.size()>3) {
					descriptions=list.get(0).getKey()+","+list.get(1).getKey()+","+list.get(2).getKey();	
				}else {
					for (int i = 0; i < list.size(); i++) {
						descriptions+=list.get(i).getKey();
						if(list.size()!=i) {
							descriptions+=",";
						}
					}
				}
			}
			//设置描述信息
			article.setDescriptions(descriptions);
			
			
			//设置时间
			 times = DateUtils.augmentDate(times, 7);
			article.setCreated(times);
			article.setUpdated(times);
			
			//设置图票
			article.setPicture((pics[RandomUtil.random(0, pics.length -1)]).getName());
			
			//通过kafak发送消息
			String json = objectMapper.writeValueAsString(article);
			kafkaTemplate.sendDefault("yangkai_cms_article"+System.currentTimeMillis(), json);
			logger.info("@@@@@@@@@@@@@@@@@通过kafak发送文章消息");
			
	
			
			//保存文章
			//articleService.insert(article);
		}
	}

}
