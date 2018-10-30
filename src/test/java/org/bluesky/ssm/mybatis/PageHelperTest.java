package org.bluesky.ssm.mybatis;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.bluesky.ssm.dao.ItemsCustomMapper;
import org.bluesky.ssm.po.ItemsCustom;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * Mybatis分页插件测试类
 * @author: liuyuefeng
 * @date: 2016年3月22日 上午11:22:00
 * @version: V1.0
 *
 */
public class PageHelperTest {
	private static final Logger logger = LoggerFactory.getLogger(PageHelperTest.class);
	
	private static ApplicationContext ctx;
	private ItemsCustomMapper itemsCustomMapper;
//	private ItemsMapper itemsMapper;
	
	@Before
	public void setUp() throws Exception {
		// 在类路径下寻找applicationContext.xml文件
		ctx = new ClassPathXmlApplicationContext("spring/applicationContext-dao.xml");
		
		itemsCustomMapper = (ItemsCustomMapper) ctx.getBean("itemsCustomMapper");
//		itemsMapper = (ItemsMapper) ctx.getBean("itemsMapper");
	}
	
	@After
    public void tearDown() throws Exception {
    }
	
	@Test
	public void testFindItemsListByPage() {
		List<ItemsCustom> itemsCustomList;
		try {
			// 获取第1页，10条内容，默认查询总数count
			// PageHelper.startPage(1, 10);
			// 排序查询
			PageHelper.startPage(1, 10, "name desc");
			
			itemsCustomList = itemsCustomMapper.findItemsList(null);
			
			// 用PageInfo对结果进行包装
			PageInfo<ItemsCustom> page = new PageInfo<ItemsCustom>(itemsCustomList);
			// 测试PageInfo全部属性
			// PageInfo包含了非常全面的分页属性
			assertEquals(1, page.getPageNum());// 当前页
			assertEquals(10, page.getPageSize());// 每页的数量
			assertEquals(3, page.getSize());// 当前页的数量
			assertEquals(1, page.getStartRow());// 当前页面第一个元素在数据库中的行号
			assertEquals(3, page.getEndRow());// 当前页面最后一个元素在数据库中的行号
			assertEquals(3, page.getTotal());// 总记录数
			assertEquals(1, page.getPages());// 总页数
			assertEquals(1, page.getFirstPage());// 第一页
			assertEquals(1, page.getLastPage());// 最后一页
			assertEquals(true, page.isIsFirstPage());// 是否为第一页
			assertEquals(true, page.isIsLastPage());// 是否为最后一页
			assertEquals(false, page.isHasPreviousPage());// 是否有前一页
			assertEquals(false, page.isHasNextPage());// 是否有下一页
			
			logger.info("查询商品信息列表返回结果：" + String.valueOf(itemsCustomList.size()));
			for (ItemsCustom itemsCustom : itemsCustomList) {
				logger.info("查询商品信息返回结果：" + itemsCustom.getName() + ":" + itemsCustom.getPrice() + ";" + itemsCustom.getDetail());
			}
		} catch (Exception e) {
			logger.error("程序发生错误：" + e.getMessage());
		}
	}
}
