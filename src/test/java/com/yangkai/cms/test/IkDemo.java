package com.yangkai.cms.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.yangkai.cms.utils.IKWord;

/**
 * 
 * @ClassName: IkDemo 
 * @Description: 分词测试类
 * @author: 阿里人:杨某 
 * @date: 2019年8月21日 下午3:47:26
 */
public class IkDemo {

	public static void main(String[] args) throws IOException {
		
		String content="华为明细GalaxyNote4N91004手机G手幻影白明细双卡双待 公开版+施华洛世奇水手机晶后壳（瑰金落日）套装手机";
		Map<String,Integer> frequencies =new HashMap<>();
		//统计词
		IKWord.count(frequencies, content);
		
		Set<Entry<String,Integer>> entrySet = frequencies.entrySet();
		Iterator<Entry<String, Integer>> iterator = entrySet.iterator();
		while(iterator.hasNext()) {
			Entry<String, Integer> entry = iterator.next();
			System.out.println(entry.getValue()+"@@@@@@@@@"+entry.getKey());
		}
		//对分词结果排序
		List<Entry<String,Integer>> list = IKWord.order(frequencies);
		list.forEach(System.out::println);
		
		
		
		
		System.out.println("============================================");
		//分词器的使用
		List<Entry<String,Integer>> list2 = IKWord.order(IKWord.count(new HashMap<String,Integer>(), "双卡双待 公开版+施华洛世奇"));
		list.forEach(System.out::println);
		
	}
}
