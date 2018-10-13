package cn.itcast.goods.jsontest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
public class ItemsServiceImpl implements ItemsService{

	@Autowired
	public ItemsMapper itemsMapper;

	@Override
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo)
			throws Exception {

		return itemsMapper.findItemsList(itemsQueryVo);
	}

	@Override
	public ItemsCustom findItemsById(Integer id) throws Exception {

		Items items = itemsMapper.selectByPrimaryKey(id);
		if(items==null){
			throw new Exception("修改的商品信息不存在!");
		}
		//中间对商品信息进行业务处理
		//....
		//返回ItemsCustom
		ItemsCustom itemsCustom = null;
		//将items的属性值拷贝到itemsCustom
		if(items!=null){
			itemsCustom = new ItemsCustom();
			BeanUtils.copyProperties(items, itemsCustom);
		}
		return itemsCustom;

	}


	@Override
	public void updateItems(Items itemsCustom) throws Exception {
		itemsMapper.updateByPrimaryKeySelective(itemsCustom);
	}

	@Override
	public void deleteItems(Integer id) throws Exception {

	}

}
