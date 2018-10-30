package org.bluesky.ssm.service;

import java.util.List;

import org.bluesky.ssm.po.ItemsCustom;

/**
 * 商品业务类接口
 * @author: liuyuefeng
 * @date: 2015-6-2 下午7:18:42
 * @version: V1.0
 * 
 */
public interface ItemsService {
	
	/**
	 * 商品信息查询
	 * @param itemsCustom
	 * @return
	 * @throws Exception
	 * @return: List<ItemsCustom>
	 * @throws:
	 */
	public List<ItemsCustom> findItemsList(ItemsCustom itemsCustom) throws Exception;
	
	/**
	 * 根据id查询商品信息
	 * @param id
	 * @return
	 * @throws Exception
	 * @return: ItemsCustom
	 * @throws:
	 */
	public ItemsCustom findItemsById(Integer id) throws Exception;
	
	/**
	 * 修改商品信息
	 * @param id
	 * @param itemsCustom
	 * @throws Exception
	 * @return: void
	 * @throws:
	 */
	public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception;
}
