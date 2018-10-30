package org.bluesky.ssm.spring;

import java.util.List;

import org.bluesky.ssm.po.ItemsCustom;
import org.bluesky.ssm.po.auto.Items;
import org.bluesky.ssm.service.ItemsService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Spring业务类测试类
 * @author: liuyuefeng
 * @date: 2015-6-5 下午5:53:13
 * @version: V1.0
 * 
 */
//@RunWith的作用相当于继承extends
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/springmvc.xml",
		"classpath:spring/applicationContext-dao.xml",
		"classpath:spring/applicationContext-service.xml",
		"classpath:spring/applicationContext-transaction.xml"})
public class ServiceTest {
	private static final Logger logger = LoggerFactory.getLogger(ServiceTest.class);
	
	@Autowired
	private ItemsService itemsService;
	
	@Before
	public void setUp() throws Exception {
	}
	
	@After
    public void tearDown() throws Exception {
    }
	
	@Test
	public void testFindItemsList() {
		List<ItemsCustom> itemsCustomList;
		try {
			itemsCustomList = itemsService.findItemsList(null);
			
			logger.info("查询商品信息列表返回结果：" + String.valueOf(itemsCustomList.size()));
			Assert.assertEquals("台式机", itemsCustomList.get(0).getName());
		} catch (Exception e) {
			logger.error("程序发生错误：" + e.getMessage());
		}
	}
	
	@Test
	public void testFindItems() {
		logger.info("商品信息映射接口实体类不为空：" + (itemsService != null));
		Items items;
		try {
			items = itemsService.findItemsById(1);
			
			logger.info("查询商品信息返回结果：" + items.getName() + ":" + items.getPrice() + ";" + items.getDetail());
			Assert.assertEquals("台式机", items.getName());
		} catch (Exception e) {
			logger.error("程序发生错误：" + e.getMessage());
		}
	}

}
