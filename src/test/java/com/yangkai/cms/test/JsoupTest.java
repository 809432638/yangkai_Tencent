package com.yangkai.cms.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

/**
 * 
 * @ClassName: JsoupTest 
 * @Description: 网页抓去文章测试类
 * @author: 阿里人:杨某 
 * @date: 2019年8月21日 下午1:52:42
 */
public class JsoupTest {

	@Test
	public void test_jsoup_get_article() throws IOException {
		int count = 0;
		// 文档对象获取                  
		//Document document = Jsoup.connect("https://www.qb5200.tw/xiaoshuo/2/2301/").get();
		Document document = Jsoup.connect("https://www.qb5200.tw/xiaoshuo/2/2217/").get();
		// 获取该文档中的超链接
		Elements links = document.select("a[href]");
		// 遍历该文档中的连接元素
		for (Element link : links) {
			// 获取连接
			String href = link.attr("href");
			// 获取连接 文本内容 ---标题&& href.contains("news")
			//https://www.qb5200.tw/xiaoshuo/2/2301/483369947.html
			
			System.out.println(href);
			if (href != null  && href.contains(".html")) {
				// 一篇文章
				Document doc = null;
				try {
				//	doc = Jsoup.connect("https://www.qb5200.tw/xiaoshuo/2/2301/"+href).get();
					doc = Jsoup.connect("https://www.qb5200.tw/xiaoshuo/2/2217/"+href).get();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (doc != null) {
					System.out.println(doc.title());
					// 获取内容元素
					Elements cc = doc.select("h1");
					String title = cc.text();
					Element contentElement = doc.getElementById("content");
					if (contentElement != null) {
						String text = contentElement.text();
						System.out.println(title);
						System.out.println(text);
						count++;
						writeFile(title,text);
					
					}
					
				}
			}
		}
	}

	private void writeFile(String title, String text) throws IOException {
		title = title.replace("?", "").replace(" ", "").replace("|", "").replace(",", "").replace("\"", "");
		//创建文件对象
		File file = new File("D:\\1703ajsoup", title+".txt");
		//创建文件的输出流
		BufferedWriter bw =new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf8"));
		bw.write(text);
		bw.flush();
		bw.close();
	}


}
