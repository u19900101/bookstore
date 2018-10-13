package cn.itcast.goods.ssm.controller;

import cn.itcast.commons.CommonUtils;
import cn.itcast.goods.pager.PageBean;
import cn.itcast.goods.ssm.po.*;
import cn.itcast.goods.ssm.service.impl.BookServiceImpl;
import cn.itcast.goods.ssm.service.impl.CartItemServiceImpl;
import cn.itcast.goods.ssm.service.impl.OrderServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by liupanbangbangda on 2018/10/3.
 */
@Controller
@RequestMapping("/AdminOrder")
public class AdminOrder  {
    OrderServiceImpl orderServiceImpl=new OrderServiceImpl();
    @RequestMapping("/loadOrders")
    public ModelAndView loadOrders(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int pc = getPc(req);
        String url = getUrl(req);
        PageBean<Order> pb=orderServiceImpl.loadOrders(pc);
        req.setAttribute("pb",pb);
        pb.setUrl(url);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/adminjsps/admin/order/list");
        return modelAndView;
    }


    //显示订单详情
    @RequestMapping("/loadOrderByOid")
    public ModelAndView loadOrderByOid(String oid,String btn,HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Order order=orderServiceImpl.loadOrderByOid(oid);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("order", order);
        modelAndView.addObject("btn", btn);
        modelAndView.setViewName("/adminjsps/admin/order/desc");
        return modelAndView;
    }

    //取消订单
    // 如果 状态为2--准备发货，则下一状态变更为 6--准备退款，
    // 如果 状态为1--等待付款，则下一状态变更为 5--订单已取消
    @RequestMapping("/canelOrderByOid")
    public ModelAndView canelOrderByOid(String oid,String btn,HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        ModelAndView modelAndView = new ModelAndView();
        //修改订单的状态为 取消状态————5
        int status=orderServiceImpl.getStatus(oid);
        //未付款  直接取消
        if(status==1){
            orderServiceImpl.alterStatus(oid,5);
            modelAndView.addObject("code", "success");
            modelAndView.addObject("msg", "取消成功");
            modelAndView.setViewName("/msg");
            return modelAndView;
        }

        //已付款
        //修改订单的状态为 准备退款————6
        //将项目重新 加入购物车里
        if(status==2){
            Order order=orderServiceImpl.loadOrderByOid(oid);
            //加入购物车里
            List<Orderitem> orderItemList =order.getOrderItemList();

            for(Orderitem orderitem:orderItemList){
                Cartitem cartitem=new Cartitem();
                cartitem.setUid(order.getUid());
                cartitem.setBid(orderitem.getBid());
                //此时cartitem中只有uid和bid，没有cartitemid，所以能够按照条件进行查询
                //用户名和书名匹配的有且仅有一个
                CartItemServiceImpl cartItemServiceImpl=new CartItemServiceImpl();
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

            orderServiceImpl.alterStatus(oid,6);
            modelAndView.addObject("code", "success");
            modelAndView.addObject("msg", "取消成功，已退款！");
            modelAndView.setViewName("/msg");
            return modelAndView;
        }

        modelAndView.addObject("code", "error");
        modelAndView.addObject("msg", "状态错误！！");
        modelAndView.setViewName("/msg");
        return modelAndView;
    }

    @RequestMapping("/sendoutOrderByOid")
    public ModelAndView sendoutOrderByOid(String oid,HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        ModelAndView modelAndView = new ModelAndView();
        //修改订单的状态为 已发货————3
        int status=orderServiceImpl.getStatus(oid);

        if(status==2){
            Order order=orderServiceImpl.loadOrderByOid(oid);
            orderServiceImpl.alterStatus(oid,3);
            modelAndView.addObject("code", "success");
            modelAndView.addObject("msg", "发货成功！！");
            modelAndView.setViewName("/msg");
            return modelAndView;
        }

        modelAndView.addObject("code", "error");
        modelAndView.addObject("msg", "状态错误！！");
        modelAndView.setViewName("/msg");
        return modelAndView;
    }

    private int getPc(HttpServletRequest req){
        int pc=1;
        String param=req.getParameter("pc");
        if(param!=null && !param.trim().isEmpty()){
            pc=Integer.parseInt(param);
        }
        return pc;
    }

    private String getUrl(HttpServletRequest req) {
        String url=null;
        if(req.getQueryString()!=null){
            url = req.getRequestURI() + "?" + req.getQueryString();
        }else {
            url = req.getRequestURI()+"?";
        }

        int index = url.lastIndexOf("&pc=");
        if(index != -1) {
            url = url.substring(0, index);
        }
        return url;
    }

}
