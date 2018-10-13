package cn.itcast.goods.ssm.service.impl;

import cn.itcast.commons.CommonUtils;

import cn.itcast.goods.pager.PageBean;
import cn.itcast.goods.pager.PageConstants;
import cn.itcast.goods.ssm.mapper.BookMapper;
import cn.itcast.goods.ssm.mapper.CartitemMapper;
import cn.itcast.goods.ssm.mapper.OrderMapper;
import cn.itcast.goods.ssm.mapper.OrderitemMapper;
import cn.itcast.goods.ssm.po.*;
import cn.itcast.goods.ssm.service.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by liupanbangbangda on 2018/9/19.
 */
public class OrderServiceImpl implements OrderService {

    private ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:applicationContext-dao.xml");
    OrderMapper orderMapper = (OrderMapper) applicationContext.getBean("orderMapper");
    OrderitemMapper orderitemMapper = (OrderitemMapper) applicationContext.getBean("orderitemMapper");
    CartitemMapper cartitemMapper = (CartitemMapper) applicationContext.getBean("cartitemMapper");
    BookMapper bookMapper = (BookMapper) applicationContext.getBean("bookMapper");
    int ps = PageConstants.BOOK_PAGE_SIZE;//每页记录数


    @Override
    public PageBean<Order> loadOrders(String uid, int pc) {
        //得到当前显示订单记录

        OrderVo orderVo=new OrderVo();
        orderVo.setFrom((pc - 1)*ps);
        orderVo.setTo(ps);
        orderVo.setUid(uid);
        List<Order> orderList=orderMapper.selectByOrderVo(orderVo);
        for(Order order: orderList){
            List<Orderitem> orderItemList=orderitemMapper.selectOrderitemByOid(order.getOid());
            for(Orderitem orderItem:orderItemList){
                Book book=bookMapper.selectByPrimaryKey(orderItem.getBid());
                orderItem.setBook(book);
            }
            order.setOrderItemList(orderItemList);
        }

        //总订单数
        int tr=orderMapper.selectOrderCount(orderVo);

        PageBean <Order> pb=new PageBean<Order>();
        pb.setBeanList(orderList);
        pb.setPc(pc);
        pb.setPs(ps);
        pb.setTr(tr);

        return pb;
    }


    //管理员来加载所有的订单
    @Override
    public PageBean<Order> loadOrders(int pc) {
        //得到当前显示订单记录

        OrderVo orderVo=new OrderVo();
        orderVo.setFrom((pc - 1)*ps);
        orderVo.setTo(ps);
        List<Order> orderList=orderMapper.selectByOrderVo(orderVo);
        for(Order order: orderList){
            List<Orderitem> orderItemList=orderitemMapper.selectOrderitemByOid(order.getOid());
            for(Orderitem orderItem:orderItemList){
                Book book=bookMapper.selectByPrimaryKey(orderItem.getBid());
                orderItem.setBook(book);
            }
            order.setOrderItemList(orderItemList);
        }

        //总订单数
        int tr=orderMapper.selectOrderCount(orderVo);

        PageBean <Order> pb=new PageBean<Order>();
        pb.setBeanList(orderList);
        pb.setPc(pc);
        pb.setPs(ps);
        pb.setTr(tr);

        return pb;
    }
    @Override
    public Order loadOrderByOid(String oid) {

        Order order=orderMapper.selectByPrimaryKey(oid);
        List<Orderitem> orderItemList=orderitemMapper.selectOrderitemByOid(oid);
        for(Orderitem orderItem:orderItemList){
            Book book=bookMapper.selectByPrimaryKey(orderItem.getBid());
            orderItem.setBook(book);
        }
        order.setOrderItemList(orderItemList);
        return order;
    }

    @Override
    public int getStatus(String oid) {
        return orderMapper.selectByPrimaryKey(oid).getStatus();
    }


    @Override
    public void alterStatus(String oid, int i) {
        Order order=new Order();
        order.setStatus(i);
        order.setOid(oid);
        orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public Order CreateOrder(String []itemids, BigDecimal total, String address, String uid) throws UnsupportedEncodingException {
        Order order = new Order();


        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        order.setOid(CommonUtils.uuid());
        order.setTotal(total);
        order.setAddress(address);
        order.setUid(uid);
        order.setStatus(1);
        order.setOrdertime(date);

        orderMapper.insert(order);
        //创建每一个订单条目
        for (String orderid:itemids){

            Cartitem cartitem=cartitemMapper.selectByPrimaryKey(orderid);
            Book book=bookMapper.selectByPrimaryKey(cartitem.getBid());
            cartitem.setBook(book);
            Orderitem orderitem=new Orderitem();

            orderitem.setOrderitemid(CommonUtils.uuid());
            orderitem.setQuantity(cartitem.getQuantity());
            orderitem.setSubtotal(BigDecimal.valueOf(cartitem.getSubtotal()));
            orderitem.setBid(cartitem.getBid());
            orderitem.setBname(book.getBname());
            orderitem.setCurrprice(book.getCurrPrice());
            orderitem.setImageB(book.getImage_b());
            orderitem.setOid(order.getOid());
            orderitem.setBook(book);
            orderitemMapper.insert(orderitem);
        }

        return order;
    }

    //取消
    @Override
    public void writeIntoCartItemList(List<Orderitem> orderItemList, User user) {

        for(Orderitem orderitem:orderItemList){
            Cartitem cartitem=new Cartitem();
            CartItemServiceImpl cartItemServiceImpl=new CartItemServiceImpl();
            cartitem.setUid(user.getUid());
            cartitem.setBid(orderitem.getBid());

            //此时cartitem中只有uid和bid，没有cartitemid，所以能够按照条件进行查询
            //用户名和书名匹配的有且仅有一个
            List<Cartitem> cartitemList=cartItemServiceImpl.findByCartitem(cartitem);

            if(cartitemList.size()==0){//为空则添加新的书籍条目
                //设置cartitemid
                cartitem.setCartitemid(CommonUtils.uuid());
                cartitem.setQuantity(orderitem.getQuantity());
                cartItemServiceImpl.insertCartitem(cartitem);
            }else{//不为空，则进行与原来数量的图书相加
                Cartitem cartitem1=cartitemList.get(0);
                cartitem1.setQuantity(cartitem1.getQuantity() + orderitem.getQuantity());
                cartItemServiceImpl.updateCartitem(cartitem1);
            }
        }
    }

}
