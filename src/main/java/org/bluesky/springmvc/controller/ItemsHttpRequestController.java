package org.bluesky.springmvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bluesky.springmvc.po.Items;
import org.springframework.web.HttpRequestHandler;

/**
 * 实现HttpRequestHandler接口的处理器
 * @author: liuyuefeng
 * @date: 2015-5-29 下午2:39:58
 * @version: V1.0
 * 
 */
public class ItemsHttpRequestController implements HttpRequestHandler {

	@Override
	public void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//调用service查找数据库，查询商品列表，这里使用静态数据模拟
		List<Items> itemsList = new ArrayList<Items>();
		//向list中填充静态数据
		
		Items items_1 = new Items();
		items_1.setName("联想笔记本");
		items_1.setPrice(6000f);
		items_1.setDetail("ThinkPad T430 联想笔记本电脑！");
		
		Items items_2 = new Items();
		items_2.setName("苹果手机");
		items_2.setPrice(5000f);
		items_2.setDetail("iphone6苹果手机！");
		
		itemsList.add(items_1);
		itemsList.add(items_2);
		//设置模型数据
		request.setAttribute("itemsList", itemsList);
		//设置转发的视图
		//此处不能使用在视图解析器中配置的jsp路径的前缀和jsp路径的后缀
		request.getRequestDispatcher("/WEB-INF/jsp/items/itemsList.jsp").forward(request, response);
		
		//使用此方法可以通过修改response，设置响应的数据格式，比如响应json数据
/*		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write("json串");
*/	}
	
	//定义其它的方法
	//商品添加
	//商品修改

}
