package cn.itcast.goods.jsontest;



import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItemsMapper {


    Items selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Items itemsCustom);

    int updateByPrimaryKey(Items record);


    List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo);
}