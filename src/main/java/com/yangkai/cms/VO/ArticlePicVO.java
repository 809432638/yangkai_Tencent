package com.yangkai.cms.VO;


import com.yangkai.cms.domain.ArticlePic;

/**
 * 
 * @ClassName: ArticlePicVO 
 * @Description:  发布图片实现类
 * @author: 阿里人:杨某 
 * @date: 2019年7月31日 上午10:27:55
 */
public class ArticlePicVO extends ArticlePic {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
