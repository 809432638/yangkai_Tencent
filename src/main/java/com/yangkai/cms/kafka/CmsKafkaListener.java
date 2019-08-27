package com.yangkai.cms.kafka;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.log4j.Logger;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.kafka.listener.MessageListener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yangkai.cms.domain.Article;
import com.yangkai.cms.service.ArticleService;
import com.yangkai.cms.utils.ESUtils;

/**
 * 
 * @ClassName: CmsKafkaListener 
 * @Description:消费者监听消息类
 * @author: 阿里人:杨某 
 * @date: 2019年8月22日 上午8:17:48
 */
public class CmsKafkaListener implements MessageListener<String, String>{

	private Logger logger=Logger.getLogger("CmsKafkaListener.class");
	
	private ObjectMapper objectMapper=new ObjectMapper();
	
	@Resource
	private ArticleService articleService;
	
	@Resource
	private ElasticsearchTemplate elasticsearchTemplate;
	
	@Override
	public void onMessage(ConsumerRecord<String, String> data) {
		// TODO Auto-generated method stub
		String key = data.key();
		String value = data.value();
		if(key!=null&&key.contains("yangkai_cms_article")) {
			try {
				//执行kafka
				Article article = objectMapper.readValue(value, Article.class);
				articleService.insert(article);
				//执行elasticSearch
				ESUtils.saveObject(elasticsearchTemplate, article.getId(), article);
				logger.info("@@@@@@@@@@@@@@@@@@@@@通过kafka接收文章到数据库");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}/*else if(key!=null&&key.contains("yangkai_cms_hitkafka")) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			articleService.updateObjectHit(Integer.valueOf(value));
			logger.info("@@@@@@@@@@@@@@@@@@@@@@点击量走kafka流量削锋成功!");
		}*/
	}
}
