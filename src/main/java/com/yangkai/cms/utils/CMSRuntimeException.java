package com.yangkai.cms.utils;
/**
 * 
 * @ClassName: CMSRuntimeException 
 * @Description: 自定义系统异常类
 * @author: 阿里人:杨某 
 * @date: 2019年7月18日 下午3:02:16
 */
public class CMSRuntimeException extends RuntimeException{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;

	public CMSRuntimeException() {
		super();
	}
	
	public CMSRuntimeException(String message) {
		super(message);
	}
}
