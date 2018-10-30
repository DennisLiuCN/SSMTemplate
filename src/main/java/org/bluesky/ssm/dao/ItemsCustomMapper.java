package org.bluesky.ssm.dao;

import java.util.List;

import org.bluesky.ssm.po.ItemsCustom;

/**
 * 商品信息DAO扩展类接口
 * @author: liuyuefeng
 * @date: 2015-6-3 上午8:06:00
 * @version: V1.0
 * 
 */
public interface ItemsCustomMapper {
	
	//商品列表
	public List<ItemsCustom> findItemsList(ItemsCustom itemsCustom) throws Exception;

}
