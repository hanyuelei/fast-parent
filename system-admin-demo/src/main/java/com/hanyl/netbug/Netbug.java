package com.hanyl.netbug;


import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 * 爬虫简单测试用例
 * Created by hanyl on 2019年12月19日.
 */
public class Netbug {
	public static void run() {
		// 网页地址
		String url = "http://www.chinadrugtrials.org.cn/eap/clinicaltrials.searchlist";
		try {
			Map map=new HashMap<>();
			map.put("currentpage", "1");
			Connection connection = Jsoup.connect(url).method(Method.POST).data(map);
			Document doc=connection.post(); 
			//get请求
//			Document doc = Jsoup.connect(url).get();
			Element table = doc.getElementsByClass("Tab").get(0);
//			System.out.println(table);
			// 使用选择器选择该table内所有的<tr> <tr/>
			Elements trs = table.select("tr");
			//遍历该表格内的所有的<tr> <tr/>
			for (int i = 0; i < trs.size(); ++i) {
				// 获取一个tr
				Element tr = trs.get(i);
				// 获取该行的所有td节点
				Elements tds = tr.select("td");
				
				// 选择某一个td节点
				for (int j = 1; j < tds.size(); j++) {
					Element td =tds.get(j);
					System.out.println(td);
					
					// 获取td节点的所有a
					Elements divs = td.select("a");
					if(divs.size()>0) {
						//获取a的文本值
						System.out.println("a:"+divs.get(0).text());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		run();
	}
}
