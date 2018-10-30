package org.bluesky.ssm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登录的处理器
 * @author: liuyuefeng
 * @date: 2015-6-4 下午2:34:03
 * @version: V1.0
 * 
 */
@Controller
@RequestMapping("/loginout")
public class LoginController {

	// 登录
	@RequestMapping("/login")
	public String login(HttpSession session, String username, String password) throws Exception {
		if (username == null || username == "" || password == null || password == "")
			// 重定向到登录界面
			return "redirect:/main/login.jsp";
		
		// 调用service进行用户身份验证
		// ...

		// 在session中保存用户身份信息
		session.setAttribute("username", username);
		
		// 重定向到商品列表页面
		return "redirect:/items/queryItems";
	}
	
	// 退出
	@RequestMapping("/logout")
	public String logout(HttpSession session) throws Exception {
		// 清除session
		session.invalidate();
		
		// 重定向到商品列表页面
		return "redirect:/items/queryItems";
	}
}
