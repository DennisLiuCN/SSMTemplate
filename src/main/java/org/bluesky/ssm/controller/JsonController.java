package org.bluesky.ssm.controller;

import org.bluesky.ssm.po.ItemsCustom;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Json数据处理器
 * @author: liuyuefeng
 * @date: 2015-6-4 下午12:20:19
 * @version: V1.0
 * 
 */
@Controller
@RequestMapping("/json")
public class JsonController {
	
	// 请求json串(商品信息)，输出json(商品信息)
	// @RequestBody将请求的商品信息的json串转成itemsCustom对象
	// @ResponseBody将itemsCustom转成json输出
	@RequestMapping("/requestJson")
	public @ResponseBody ItemsCustom requestJson(@RequestBody ItemsCustom itemsCustom) throws Exception {
		return itemsCustom;
	}
	
	// 请求key/value，输出json
	@RequestMapping("/responseJson")
	public @ResponseBody ItemsCustom responseJson(ItemsCustom itemsCustom) {
		return itemsCustom;
	}
	
}
