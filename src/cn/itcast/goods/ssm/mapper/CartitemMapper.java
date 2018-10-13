package cn.itcast.goods.ssm.mapper;
import cn.itcast.goods.ssm.po.Cartitem;
import cn.itcast.goods.ssm.po.CartitemExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartitemMapper {
    int countByExample(CartitemExample example);

    int deleteByExample(CartitemExample example);

    int deleteByPrimaryKey(String cartitemid);

    int insert(Cartitem record);

    int insertSelective(Cartitem record);

    List<Cartitem> selectByExample(CartitemExample example);

    Cartitem selectByPrimaryKey(String cartitemid);

    int updateByExampleSelective(@Param("record") Cartitem record, @Param("example") CartitemExample example);

    int updateByExample(@Param("record") Cartitem record, @Param("example") CartitemExample example);

    int updateByPrimaryKeySelective(Cartitem record);

    int updateByPrimaryKey(Cartitem record);





    List<Cartitem> findBySelective(Cartitem cartitem);
}