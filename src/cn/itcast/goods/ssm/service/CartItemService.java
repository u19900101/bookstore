package cn.itcast.goods.ssm.service;


import cn.itcast.goods.ssm.po.Cartitem;
import cn.itcast.goods.ssm.po.Orderitem;

import java.util.List;

/**
 * Created by liupanbangbangda on 2018/9/20.
 */
public interface CartItemService {
    List<Cartitem> findByCartitem(Cartitem cartitem);

    void insertCartitem(Cartitem cartitem);

    void updateCartitem(Cartitem cartitem);

    void batchDelete(String[] cartitemidArray);

    List<Cartitem> batchload(String[] cartitemidArray);
}
