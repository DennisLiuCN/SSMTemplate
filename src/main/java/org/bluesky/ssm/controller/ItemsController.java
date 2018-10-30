package org.bluesky.ssm.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.bluesky.ssm.po.ItemsCustom;
import org.bluesky.ssm.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * 注解开发的处理器
 * @author: liuyuefeng
 * @date: 2015-6-3 上午8:30:36
 * @version: V1.0
 *
 */
@Controller
//为了对url进行分类管理 ，可以在这里定义根路径，最终访问url是根路径+子路径
//比如：商品列表：/items/queryItems.action
@RequestMapping("/items")
public class ItemsController {
	
	@Autowired
	private ItemsService itemsService;
	
	// 商品分类
	//itemtypes表示最终将方法返回值放在request中的key
	@ModelAttribute("itemtypes")
	public Map<String, String> getItemTypes() {

		Map<String, String> itemTypes = new HashMap<String, String>();
		itemTypes.put("101", "数码");
		itemTypes.put("102", "母婴");

		return itemTypes;
	}
	
	// 商品查询
	@RequestMapping("/queryItems")
	public ModelAndView queryItems(ItemsCustom itemsCustom) throws Exception {
		
		// 调用service查找 数据库，查询商品列表
		List<ItemsCustom> itemsList = itemsService.findItemsList(itemsCustom);
		
		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		// 相当于request的setAttribut，在jsp页面中通过itemsList取数据
		modelAndView.addObject("itemsList", itemsList);

		// 指定视图
		modelAndView.setViewName("items/itemsList");

		return modelAndView;
	}
	
	// 商品信息修改页面显示
	@RequestMapping(value="/editItems",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView editItems(Integer id) throws Exception {
		
		//调用service根据商品id查询商品信息
		ItemsCustom itemsCustom = itemsService.findItemsById(id);
		
		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		
		//将商品信息放到model
		modelAndView.addObject("itemsCustom", itemsCustom);
		
		//商品修改页面
		modelAndView.setViewName("items/editItems");
		
		return modelAndView;
	}
	
	// 商品信息修改提交
	@RequestMapping("/editItemsSubmit")
	public String editItemsSubmit(HttpServletRequest request, Integer id, ItemsCustom itemsCustom, MultipartFile items_pic) throws Exception {

		// 测试数据回显
		if (id == 1)
			return "items/editItems";
		
		// 原始名称
		String originalFileName = items_pic.getOriginalFilename();
		// 上传图片
		if (items_pic != null && originalFileName != null && originalFileName.length() > 0) {

			// 存储图片的物理路径
			String pic_path = "D:\\Program Files\\apache-tomcat-7.0.65\\upload\\temp\\";

			// 新的图片名称（UUID+原始文件名称后缀）
			String newFileName = UUID.randomUUID() + originalFileName.substring(originalFileName.lastIndexOf("."));
			// 新图片
			File newFile = new File(pic_path + newFileName);

			// 将内存中的数据写入磁盘
			items_pic.transferTo(newFile);

			// 将新图片名称写到itemsCustom中
			itemsCustom.setPic(newFileName);
		}
		
		// 调用service更新商品信息，页面需要将商品信息传到此方法
		itemsService.updateItems(id, itemsCustom);

		// 重定向到商品查询列表
		// return "redirect:queryItems";
		// 页面转发
		// return "forward:queryItems";
		return "success";
	}
	
	// 批量删除商品信息
	@RequestMapping("/deleteItems")
	public String deleteItems(Integer[] items_id) throws Exception {

		// 调用service批量删除商品
		// ...

		return "success";
	}

}