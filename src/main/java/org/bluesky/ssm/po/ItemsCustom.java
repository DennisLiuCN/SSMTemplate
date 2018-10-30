package org.bluesky.ssm.po;

import org.bluesky.ssm.po.auto.Items;

/**
 * 商品信息扩展类
 * @author: liuyuefeng
 * @date: 2015-6-2 下午7:22:08
 * @version: V1.0
 * 
 */
//可以使用注解@Alias("ItemsCustom")指定实体类的别名，不指定时默认使用mybatis批量扫描别名（首字母大写小写均可）
public class ItemsCustom extends Items {
	//添加商品信息的扩展属性
}
