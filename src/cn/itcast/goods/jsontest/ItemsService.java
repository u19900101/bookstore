package cn.itcast.goods.jsontest;

import java.util.List;
public interface ItemsService {
	
	//商品查询列表
	public List<cn.itcast.goods.jsontest.ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;

	public cn.itcast.goods.jsontest.ItemsCustom findItemsById(Integer id) throws Exception;
	
	//修改商品信息

	public void updateItems(Items itemsCustom) throws Exception;

	public void deleteItems(Integer id) throws Exception;
}
