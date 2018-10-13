package cn.itcast.goods.ssm.mapper;

import cn.itcast.goods.ssm.po.Order;
import cn.itcast.goods.ssm.po.OrderExample;
import cn.itcast.goods.ssm.po.OrderVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {

    List<Order> selectByOrderVo(OrderVo orderVo);
    int selectOrderCount(OrderVo orderVo);




    int countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(String oid);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(String oid);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}