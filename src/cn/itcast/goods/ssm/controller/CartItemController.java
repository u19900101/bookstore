package cn.itcast.goods.ssm.controller;


import cn.itcast.commons.CommonUtils;
import cn.itcast.goods.ssm.po.Book;
import cn.itcast.goods.ssm.po.Cartitem;
import cn.itcast.goods.ssm.po.User;
import cn.itcast.goods.ssm.service.impl.BookServiceImpl;
import cn.itcast.goods.ssm.service.impl.CartItemServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by liupanbangbangda on 2018/9/20.
 */
@Controller
@RequestMapping("/cartitem")
public class CartItemController {

    private CartItemServiceImpl cartItemServiceImpl=new CartItemServiceImpl();
    BookServiceImpl bookServiceImpl=new BookServiceImpl();

   public List<Cartitem> loadCartItem(HttpServletRequest req){
       User formUser = (User)req.getSession().getAttribute("sessionUser");

      //得到只有uid属性的Cartitem对象
       Cartitem uid_cartitem=new Cartitem();
       uid_cartitem.setUid(formUser.getUid());
       List<Cartitem> cartItemList = cartItemServiceImpl.findByCartitem(uid_cartitem);

       for (Cartitem cartitem:cartItemList){
           BookServiceImpl bookServiceImpl=new BookServiceImpl();
           Book book=bookServiceImpl.selectByBid(cartitem.getBid());
           cartitem.setBook(book);
       }
       return cartItemList;
   }

    @RequestMapping("/loadCartItem")
    public ModelAndView loadCartItem(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("cartItemList", loadCartItem(req));
        modelAndView.setViewName("/cart/list");
        return modelAndView;
    }

    @RequestMapping("/addCartItem")
    public ModelAndView  addCartItem(String bid,Integer quantity,HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, SQLException {

        User user = (User)req.getSession().getAttribute("sessionUser");
        Cartitem cartitem=new Cartitem();
        cartitem.setUid(user.getUid());
        cartitem.setBid(bid);
        Book book=bookServiceImpl.selectByBid(bid);
        cartitem.setBook(book);

        //此时cartitem中只有uid和bid，没有cartitemid，所以能够按照条件进行查询
        //用户名和书名匹配的有且仅有一个
        List<Cartitem> cartitemList=cartItemServiceImpl.findByCartitem(cartitem);

        if(cartitemList.size()==0){//为空则添加新的书籍条目
            //设置cartitemid
            cartitem.setCartitemid(CommonUtils.uuid());
            cartitem.setQuantity(quantity);
            cartItemServiceImpl.insertCartitem(cartitem);
        }else{//不为空，则进行与原来数量的图书相加
            Cartitem cartitem1=cartitemList.get(0);
            cartitem1.setQuantity(cartitem1.getQuantity() + quantity);
            cartItemServiceImpl.updateCartitem(cartitem1);
        }

        ModelAndView modelAndView=new ModelAndView();
        //只通过uid进行查询所有的购物车条目
        Cartitem uid_cartitem=new Cartitem();
        uid_cartitem.setUid(cartitem.getUid());

        List<Cartitem> cartItemList=cartItemServiceImpl.findByCartitem(uid_cartitem);
        for (Cartitem cartitemm:cartItemList){
            BookServiceImpl bookServiceImpl=new BookServiceImpl();
            Book bookk=bookServiceImpl.selectByBid(cartitemm.getBid());
            cartitemm.setBook(bookk);
        }
        modelAndView.addObject("cartItemList",cartItemList);
        modelAndView.setViewName("/cart/list");
        return modelAndView;
    }

    @RequestMapping(value={"/updateQuantity"},method={RequestMethod.POST,RequestMethod.GET})
    public @ResponseBody Cartitem updateQuantity(@RequestBody Cartitem cartitem){
        //@ResponseBody将itemsCustom转成json输出
        System.out.println(cartitem);

        //通过cartid修改原始条目的数量
        List<Cartitem> cartItemList=cartItemServiceImpl.findByCartitem(cartitem);
        Cartitem cartitem_new=cartItemList.get(0);
        cartitem_new.setQuantity(cartitem.getQuantity());
        cartitem_new.setBook(bookServiceImpl.selectByBid(cartitem_new.getBid()));

        //更新条目
        cartItemServiceImpl.updateCartitem(cartitem_new);
        return cartitem_new;
    }

    @RequestMapping("/batchDelete")
    public ModelAndView batchDelete(String cartitemids,HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        System.out.println(cartitemids);

        //将字符串拆分为数组
        String []cartitemidArray=cartitemids.split(",");
        cartItemServiceImpl.batchDelete(cartitemidArray);


        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("cartItemList", loadCartItem(req));
        modelAndView.setViewName("/cart/list");
        return modelAndView;
    }

    @RequestMapping("/jiesuan")
    public ModelAndView payPre(String cartitemids, double total, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String []cartitemidArray=cartitemids.split(",");

        List<Cartitem> cartItemList = cartItemServiceImpl.batchload(cartitemidArray);
        for (Cartitem cartitem:cartItemList){
            BookServiceImpl bookServiceImpl=new BookServiceImpl();
            Book book=bookServiceImpl.selectByBid(cartitem.getBid());
            cartitem.setBook(book);
        }


        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("cartItemList",cartItemList);
        modelAndView.addObject("total",total);
        modelAndView.addObject("orderItemIds",cartitemids);
        modelAndView.setViewName("/cart/showitem");
        return modelAndView;
    }


    /*CartItemService cartitemService=new CartItemService();

    public String  loadCartItemByIds(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String cartItemIds=req.getParameter("cartItemIds");
        List<CartItem> cartItemList = cartitemService.loadCartItemByIds(cartItemIds);
        req.setAttribute("total",req.getParameter("total"));
        req.setAttribute("cartItemList",cartItemList);
        req.setAttribute("orderItemIds", cartItemIds);
        return "f:/jsps/cart/showitem.jsp";
    }

    public String batchDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String cartItemIds=req.getParameter("cartItemIds");
        cartitemService.batchDelete(cartItemIds);
        return findCartItem(req, res);
    }



  */
}
