package cn.itcast.goods.ssm.service.impl;

import cn.itcast.goods.ssm.mapper.CartitemMapper;
import cn.itcast.goods.ssm.po.Cartitem;
import cn.itcast.goods.ssm.po.Orderitem;
import cn.itcast.goods.ssm.service.CartItemService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liupanbangbangda on 2018/9/20.
 */
public class CartItemServiceImpl implements CartItemService {
    private ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:applicationContext-dao.xml");
    CartitemMapper cartitemMapper= (CartitemMapper) applicationContext.getBean("cartitemMapper");

    @Override
    public List<Cartitem> findByCartitem(Cartitem cartitem) {
        return cartitemMapper.findBySelective(cartitem);
    }

    @Override
    public void insertCartitem(Cartitem cartitem) {
        cartitemMapper.insert(cartitem);
    }

    @Override
    public void updateCartitem(Cartitem cartitem) {
        cartitemMapper.updateByPrimaryKey(cartitem);
    }

    @Override
    public void batchDelete(String[] cartitemidArray) {
        for (String cartid:cartitemidArray) {
            cartitemMapper.deleteByPrimaryKey(cartid);
        }
    }

    @Override
    public List<Cartitem> batchload(String[] cartitemidArray) {
        List <Cartitem>cartitemList=new ArrayList<Cartitem>();
        for (String cartid:cartitemidArray) {
            cartitemList.add( cartitemMapper.selectByPrimaryKey(cartid));
        }
        return cartitemList;
    }

}
