package cn.itcast.goods.ssm.controller;

import cn.itcast.goods.pager.PageBean;
import cn.itcast.goods.ssm.po.Order;
import cn.itcast.goods.ssm.po.Orderitem;
import cn.itcast.goods.ssm.po.User;
import cn.itcast.goods.ssm.service.impl.CartItemServiceImpl;
import cn.itcast.goods.ssm.service.impl.OrderServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by liupanbangbangda on 2018/9/19.
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @RequestMapping("/test")
    public ModelAndView test() throws IOException, SQLException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/user/login");
        return modelAndView;
    }

    private OrderServiceImpl orderServiceImpl=new OrderServiceImpl();
    private CartItemServiceImpl cartItemServiceImpl=new CartItemServiceImpl();
    @RequestMapping("/paymentPre")
    public ModelAndView paymentPre(String oid,HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("order", orderServiceImpl.loadOrderByOid(oid));
        modelAndView.setViewName("/order/pay");
        return modelAndView;
    }

    @RequestMapping("/afterPayment")
    public ModelAndView afterPayment(String oid,HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //通过Order的id  重新把订单里的东西写进购物车里面
        ModelAndView modelAndView = new ModelAndView();
        //修改订单的状态为 取消状态————4
        int status=orderServiceImpl.getStatus(oid);
        if(status!=1){
            modelAndView.addObject("code", "error");
            modelAndView.addObject("msg", "状态错误，不能支付订单！");
        }

        orderServiceImpl.alterStatus(oid,2);
        modelAndView.addObject("code", "success");
        modelAndView.addObject("msg", "支付成功！！");
        modelAndView.setViewName("/msg");
        return modelAndView;
    }

    @RequestMapping("/loadOrders")
    public ModelAndView loadOrders(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int pc = getPc(req);
        String url = getUrl(req);
        User user=(User)req.getSession().getAttribute("sessionUser");
        PageBean<Order> pb=orderServiceImpl.loadOrders(user.getUid(), pc);
        req.setAttribute("pb",pb);
        pb.setUrl(url);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/order/list");
        return modelAndView;
    }

    //显示订单详情
    @RequestMapping("/loadOrderByOid")
   public ModelAndView loadOrderByOid(String oid,String btn,HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

       Order order=orderServiceImpl.loadOrderByOid(oid);
       ModelAndView modelAndView = new ModelAndView();
       modelAndView.addObject("order", order);
       modelAndView.addObject("btn", btn);
       modelAndView.setViewName("/order/desc");
       return modelAndView;
    }

    @RequestMapping("/canelOrderByOid")
    public ModelAndView canelOrderByOid(String oid,HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //通过Order的id  重新把订单里的东西写进购物车里面

        User user=(User)req.getSession().getAttribute("sessionUser");
        ModelAndView modelAndView = new ModelAndView();
        //修改订单的状态为 取消状态————5
        int status=orderServiceImpl.getStatus(oid);
        if(status!=1){
            modelAndView.addObject("code", "error");
            modelAndView.addObject("msg", "状态错误，不能取消订单！");
            modelAndView.setViewName("/msg");
        }

        Order order=orderServiceImpl.loadOrderByOid(oid);
        List<Orderitem> orderItemList =order.getOrderItemList();
        orderServiceImpl.writeIntoCartItemList(orderItemList, user);

        orderServiceImpl.alterStatus(oid,5);
        modelAndView.addObject("code", "success");
        modelAndView.addObject("msg", "取消成功！！");
        modelAndView.setViewName("/msg");
        return modelAndView;
    }

    @RequestMapping("/confirmOrderByOid")
    public ModelAndView confirmOrderByOid(String oid,HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //通过Order的id  重新把订单里的东西写进购物车里面
        ModelAndView modelAndView = new ModelAndView();
        int status=orderServiceImpl.getStatus(oid);
        //修改订单的状态为 取消状态————4
        if(status!=3){
            modelAndView.addObject("code", "error");
            modelAndView.addObject("msg", "状态错误，不能确认收货！");
        }
        orderServiceImpl.alterStatus(oid,4);
        modelAndView.addObject("code", "success");
        modelAndView.addObject("msg", "取消成功！！");
        modelAndView.setViewName("/msg");
        return modelAndView;
    }

    @RequestMapping("/confirmPay")
    public ModelAndView confirmPay(String oid,HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //通过Order的id  重新把订单里的东西写进购物车里面
        ModelAndView modelAndView = new ModelAndView();
        int status=orderServiceImpl.getStatus(oid);
        //修改订单的状态为 取消状态————4
        if(status!=6){
            modelAndView.addObject("code", "error");
            modelAndView.addObject("msg", "状态错误，不能确认收货！");
        }
        orderServiceImpl.alterStatus(oid,7);
        modelAndView.addObject("code", "success");
        modelAndView.addObject("msg", "收款成功，订单关闭！！");
        modelAndView.setViewName("/msg");
        return modelAndView;
    }

    @RequestMapping("/CreateOrder")
    public ModelAndView CreateOrder(String []orderItemIds,String total,String address,HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, SQLException {

        ModelAndView modelAndView = new ModelAndView();
        //得到订单
        User user=(User)req.getSession().getAttribute("sessionUser");
        double total1=Double.parseDouble(total);
        BigDecimal _total= BigDecimal.valueOf(total1);
        Order order= orderServiceImpl.CreateOrder(orderItemIds,_total,address,user.getUid());
        //写入订单表
        modelAndView.addObject("order",order);

        //删除购物车中的原有条目
        cartItemServiceImpl.batchDelete(orderItemIds);

        modelAndView.addObject("code", "success");
        modelAndView.addObject("msg", "下单成功！！");
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

		/*
		 * 如果url中存在pc参数，截取掉，如果不存在那就不用截取。
		 */
        int index = url.lastIndexOf("&pc=");
        if(index != -1) {
            url = url.substring(0, index);
        }
        return url;
    }
}
