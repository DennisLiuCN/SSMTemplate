package org.bluesky.ssm.mybatis;

import java.util.List;

import org.bluesky.ssm.dao.ItemsCustomMapper;
import org.bluesky.ssm.dao.auto.ItemsMapper;
import org.bluesky.ssm.po.ItemsCustom;
import org.bluesky.ssm.po.auto.Items;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Mybatis接口映射测试类
 * @author: liuyuefeng
 * @date: 2015-6-3 上午9:06:08
 * @version: V1.0
 * 
 */
public class MapperTest {
	private static final Logger logger = LoggerFactory.getLogger(MapperTest.class);
	
	private static ApplicationContext ctx;
	private ItemsCustomMapper itemsCustomMapper;
	private ItemsMapper itemsMapper;
	
	@Before
	public void setUp() throws Exception {
		// 在类路径下寻找applicationContext.xml文件
		ctx = new ClassPathXmlApplicationContext("spring/applicationContext-dao.xml");
		
		itemsCustomMapper = (ItemsCustomMapper) ctx.getBean("itemsCustomMapper");
		itemsMapper = (ItemsMapper) ctx.getBean("itemsMapper");
	}
	
	@After
    public void tearDown() throws Exception {
    }
	
	@Test
	public void testFindItemsList() {
		List<ItemsCustom> itemsCustomList;
		try {
			itemsCustomList = itemsCustomMapper.findItemsList(null);
			
			logger.info("查询商品信息列表返回结果：" + String.valueOf(itemsCustomList.size()));
			Assert.assertEquals("台式机", itemsCustomList.get(0).getName());
		} catch (Exception e) {
			logger.error("程序发生错误：" + e.getMessage());
		}
	}

	@Test
	public void testFindItems() {
		logger.info("商品信息映射接口实体类不为空：" + (itemsMapper != null));
		Items items = itemsMapper.selectByPrimaryKey(1);
		
		logger.info("查询商品信息返回结果：" + items.getName() + ":" + items.getPrice() + ";" + items.getDetail());
		Assert.assertEquals("台式机", items.getName());
	}

}
