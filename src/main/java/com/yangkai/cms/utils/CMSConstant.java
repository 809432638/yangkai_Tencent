package com.yangkai.cms.utils;

/**
 * 
 * @ClassName: CMSConstant 
 * @Description: 自定义系统常量类
 * @author: 阿里人:杨某 
 * @date: 2019年8月22日 上午8:18:32
 */
public class CMSConstant {
	                         //管理员
	public  static final String RULE_ADMIN="1";
	
	                       // 普通用户
	public static final String RULE_GENERAL="0";
	
	                                // 用户可用状态 
	public static final Integer USER_STATUS_UNLOCKED=0;
	
	                                 // 用户不可用
	public static final Integer USER_STATUS_LOCKED=1;
	
	                              //管理员Sessionkey
	public static final String LOGIN_ADMIN="LOGIN_ADMIN";
	
	                      // 普通用户的的sessionkey
	public static final String LOGIN_GENERAL="LOGIN_GENERAL";
	
	                               //管理员的url
	public static final String URL_ADMIN="/admin";
	
	                             //普通用户的的url
	public static final String URL_GENERAL="/my";
	
	                               // 文章刚发布
	public static final Integer ARTICLE_STATUS_NEW=0;
	
									// 文章已审核
	public static final Integer ARTICLE_STATUS_CHECKED=1;
	
										// 文章未审核通过
	public static final Integer ARTICLE_STATUS_UNCHECKED=-1;
	                                  
	                                      //栏目用list存储的key
	public static final String REDIS_KEY_CHANNEL_LIST="yangkai_cms_channel_list";
	
	                               //栏目用hash存储的key
	public static final String REDIS_KEY_CHANNEL_HASH="yangkai_cms_channel_hash";
	
	                                   //定义栏目的hash中字段的key的前缀
	public static final String REDIS_KEY_CHANNEL_HASH_KEY="yangkai_cms_channel_hash_key";
	
}
