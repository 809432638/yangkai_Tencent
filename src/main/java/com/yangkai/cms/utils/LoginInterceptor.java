package com.yangkai.cms.utils;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;


public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//来自于的url地址
	    String referer = request.getHeader("Referer");
	    //存储到session会话中
	    request.getSession().setAttribute("refererUrl", referer);
	 
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	
}
