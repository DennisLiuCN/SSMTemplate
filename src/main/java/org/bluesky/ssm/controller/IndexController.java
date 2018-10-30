package org.bluesky.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 首页的处理器
 * @author: liuyuefeng
 * @date: 2016年3月8日 下午6:12:01
 * @version: V1.0
 *
 */
@Controller
//@RequestMapping("/index")
public class IndexController {

	// 首页
	// @RequestMapping("/index")
	// 配置拦截多个请求
	@RequestMapping(value={"/","index"})
	public ModelAndView index() throws Exception {  
        ModelAndView mav = new ModelAndView("index/index");  
        return mav;  
    } 	
}
