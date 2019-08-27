package com.yangkai.cms.utils;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * 
 * @ClassName: Md5Util 
 * @Description: Md5加密类
 * @author: 阿里人:杨某 
 * @date: 2019年8月22日 上午8:19:43
 */
public class Md5Util {
	/**
	 * 直接对密码进行散列，那么黑客可以对通过获得这个密码散列值，
	 * 然后通过查散列值字典（例如MD5密码破解网站），得到某用户的密码。
	 * 加Salt可以一定程度上解决这个问题
	 */
	//加盐值 :
	private static String salt="1a2b3c4d";
	
	public static String md5Encoding(String password) {
		return  DigestUtils.md5Hex(password +salt);
	}

}
