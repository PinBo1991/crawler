package com.dzpykj.crawler.crawler;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dzpykj.crawler.common.utils.CrawlerJDUtil;
import com.dzpykj.crawler.common.utils.CrawlerXeiChengHotelUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
//@RequestMapping("/crawler")
public class CrawlerController {
	
	private static Logger logger = Logger.getLogger(CrawlerController.class);
	
	@RequestMapping(value="/xiecheng",produces="text/html;charset=UTF-8")
	@ResponseBody
	public Object xieChengHotel(HttpServletRequest req){
		String url = req.getParameter("xiecheng_web_url");
		String encode = req.getParameter("xiecheng_web_encode");
		ObjectMapper mapper = new ObjectMapper();
		String resStr = null;
		try {
			resStr = mapper.writeValueAsString(CrawlerXeiChengHotelUtil.getWantInfo(url, encode));
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage(), e);
		}
		return resStr;
	}
	
	@RequestMapping(value="/JD",produces="text/html;charset=UTF-8")
	@ResponseBody
	public Object JD(HttpServletRequest req){
		String url = req.getParameter("JD_web_url");
		String encode = req.getParameter("JD_web_encode");
		ObjectMapper mapper = new ObjectMapper();
		String resStr = null;
		try {
			resStr = mapper.writeValueAsString(CrawlerJDUtil.getWantInfo(url, encode));
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage(), e);
		}
		return resStr;
	}
}
