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
 * 携程酒店爬虫
 */
public class CrawlerXeiChengHotelUtil {
	
	private static Logger logger = Logger.getLogger(CrawlerXeiChengHotelUtil.class);
	
	public static void main(String[] args) {
		String url = "http://hotels.ctrip.com/hotel/nanjing12#ctm_ref=hod_hp_sb_lst";
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
		Document doc = Jsoup.parse(htmlStr);
//		logger.info("Document:\n"+doc.toString());
		Elements results = new Elements();
		results = doc.getElementsByClass("searchresult_info");
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (Element ele : results){
			Map<String,Object> map = new HashMap<String,Object>();
			Elements imgs = ele.getElementsByTag("img");
			String src = imgs.attr("src");
			String title = imgs.attr("alt");
			String content = ele.getElementsByClass("searchresult_htladdress").text();
			String price = ele.getElementsByClass("J_price_lowList").text();
			map.put("hotel_img_src", src);
			map.put("hotel_title", title);
			map.put("hotel_content", content);
			map.put("hotel_price", price);
			
			list.add(map);
//			logger.info(map.toString());
//			logger.info(src+" "+title+" "+content);
		}
//		logger.info("results:\n"+results.toString());
		logger.info(list.toString());
		return list;
	}
}
