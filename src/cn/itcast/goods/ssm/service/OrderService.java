package cn.itcast.goods.ssm.service;

import cn.itcast.goods.pager.PageBean;
import cn.itcast.goods.ssm.po.Order;
import cn.itcast.goods.ssm.po.Orderitem;
import cn.itcast.goods.ssm.po.User;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by liupanbangbangda on 2018/9/19.
 */
public interface OrderService {
    PageBean<Order> loadOrders(String uid, int pc);
    PageBean<Order> loadOrders(int pc);
    Order loadOrderByOid(String oid);

    int getStatus(String oid);
    void alterStatus(String oid, int i);

    Order CreateOrder(String []orderItemIds, BigDecimal total, String address, String uid) throws UnsupportedEncodingException;

    void writeIntoCartItemList(List<Orderitem> orderItemList, User user);
}
