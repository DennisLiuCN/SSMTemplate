package org.bluesky.ssm.service.impl;

import java.util.List;

import org.bluesky.ssm.dao.ItemsCustomMapper;
import org.bluesky.ssm.exception.CustomException;
import org.bluesky.ssm.dao.auto.ItemsMapper;
import org.bluesky.ssm.po.ItemsCustom;
import org.bluesky.ssm.po.auto.Items;
import org.bluesky.ssm.service.ItemsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品业务类
 * @author: liuyuefeng
 * @date: 2015-6-2 下午7:28:49
 * @version: V1.0
 * 
 */
/**
 * 注解方式@Component、@Service、@Controller和@Repository为自动检测标注bean的区别：
 * 1：@Component泛指组件，当组件不好归类时，使用这个注解进行标注，不推荐使用
 * 2：@Controller用于标注控制层组件
 * 3：@Service用于标注业务层组件
 * 4：@Repository用于标注数据访问组件，即DAO组件
 * spring配置文件中使用<context:component-scan base-package="package name..." />完成自动检测和定义bean
 * bean的id默认为类名（首字母小写），可以指定id名称，例如@Service("xxxService");
 * 通过注解@Scope(“prototype”)指定作用域范围，作用域分类：singleton、prototype、request、session、global session
 */
// 通过@Service("itemsService")注解配置spring中该类bean的id为itemsServiceImpl
@Service("itemsService")
public class ItemsServiceImpl implements ItemsService {
	
	// 通过@Autowired注解注入依赖的bean itemsCustomMapper
	/** 
	 * 注解方式@Autowired、@Inject和@Resource进行装配区别：
	 * 1：@Autowired默认按类型装配。当Spring上下文中存在不止一个或不存在所要装配类型的bean时，就会抛出BeanCreationException异常。可以使用@Qualifier配合@Autowired来解决这些问题；
	 * 2：@Inject默认按类型装配。与@Autowired使用方式基本一致，区别@Inject是JSR330规范的实现，@Autowired是Spring实现，@Resource是JSR250实现；
	 * 3：@Resource默认按名称装配，当找不到名称匹配的bean才会按类型装配。@Resource有两个属性，@Resource(name="myMovieFinder")和@Resource(type="MovieFinder")。
	 * */
	@Autowired
	private ItemsCustomMapper itemsCustomMapper;
	
	@Autowired
	private ItemsMapper itemsMapper;
	
	@Override
	public List<ItemsCustom> findItemsList(ItemsCustom itemsCustom) throws Exception {
		//通过ItemsMapperCustom查询数据库
		return itemsCustomMapper.findItemsList(itemsCustom);
	}

	@Override
	public ItemsCustom findItemsById(Integer id) throws Exception {
		Items items = itemsMapper.selectByPrimaryKey(id);
		// 自定义异常测试
		if (items == null)
			throw new CustomException("修改的商品信息不存在！");
		
		// 中间对商品信息进行业务处理
		// ....
		
		// 返回ItemsCustom
		ItemsCustom itemsCustom = new ItemsCustom();
		// 将items的属性值拷贝到itemsCustom
		BeanUtils.copyProperties(items, itemsCustom);
		
		return itemsCustom;
	}

	@Override
	public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception {
		//添加业务校验，通常在service接口对关键参数进行校验
		//校验id是否为空，如果为空抛出异常
		
		//更新商品信息使用updateByPrimaryKeyWithBLOBs根据id更新items表中所有字段，包括大文本类型字段
		//updateByPrimaryKeyWithBLOBs要求必须转入id
		itemsCustom.setId(id);
		itemsMapper.updateByPrimaryKeyWithBLOBs(itemsCustom);
	}

}
