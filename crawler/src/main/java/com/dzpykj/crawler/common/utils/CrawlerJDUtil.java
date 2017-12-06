package com.dzpykj.crawler.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 京东爬虫
 */
public class CrawlerJDUtil {
	
	private static Logger logger = Logger.getLogger(CrawlerJDUtil.class);
	
	public static void main(String[] args) {
		String url = "https://search.jd.com/Search?keyword=%E7%94%B5%E8%84%91&enc=utf-8&wq=%E7%94%B5%E8%84%91&pvid=4427fa56953c4069ab2199a1da56c21f";
		String encode = "UTF-8";
		logger.info(getWantInfo(url,encode));
	}
	
	/**
	 * 获取网页源代码
	 * @param urlStr 要爬取的网页地址
	 * @param encode 要爬取的网页编码集
	 * @return 网页源码
	 */
	public static String getWebSourceCode(String urlStr,String encode) {
		URL url = null;
		HttpURLConnection connection = null;
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {
			url = new URL(urlStr);
			connection = (HttpURLConnection) url.openConnection();
			int resCode = connection.getResponseCode();
			if(resCode == 200){
				br = new BufferedReader(new InputStreamReader(connection.getInputStream(),encode));
				String boat;
				while ((boat = br.readLine()) != null){
					sb.append(boat+"\n");
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
		return sb.toString();
	}
	
	/**
	 * 获取需要的信息
	 * @param urlStr 要爬取的网页地址
	 * @param encode 要爬取的网页编码集
	 * @return
	 */
	public static Object getWantInfo(String url,String encode){
		String htmlStr = getWebSourceCode(url,encode);
		logger.info(htmlStr);
		Document doc = Jsoup.parse(htmlStr);
//		logger.info("Document:\n"+doc.toString());
		Elements results = new Elements();
		results = doc.getElementsByClass("gl-i-wrap");
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (Element ele : results){
			Map<String,Object> map = new HashMap<String,Object>();
			Elements imgs = ele.getElementsByTag("img");
			String src = imgs.attr("src");
			Elements as = ele.getElementsByTag("a");
			String title = as.attr("title");
			Elements elementsByClass = ele.getElementsByClass("p-shop");
			String price = ele.getElementsByClass("p-price").text();
			if(elementsByClass != null && !"".equals(elementsByClass.toString())){
				Element element = elementsByClass.get(0);
				
				Elements elementsByTag = element.getElementsByTag("span");
				String content = elementsByTag.attr("title");
				map.put("jd_content", content);
			}
			map.put("jd_img_src", src);
			map.put("jd_title", title);
			map.put("jd_price", price);
			list.add(map);
//			logger.info(map.toString());
//			logger.info(src+" "+title+" "+content);
		}
//		logger.info("results:\n"+results.toString());
//		logger.info(list.toString());
		return list;
	}
}
