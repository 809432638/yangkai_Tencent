package com.yangkai.cms.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * 
 * @ClassName: KafkaProducer 
 * @Description: 启动消费者测试类
 * @author: 阿里人:杨某 
 * @date: 2019年8月21日 下午3:52:01
 */

public class KafkaCounsumer {
    
	
	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext sc = new ClassPathXmlApplicationContext("classpath:spring-beans.xml","classpath:spring-consumer.xml");
	}

}
