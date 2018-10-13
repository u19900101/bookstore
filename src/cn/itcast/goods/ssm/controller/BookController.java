package cn.itcast.goods.ssm.controller;

import cn.itcast.goods.pager.PageBean;
import cn.itcast.goods.ssm.mapper.BookMapper;
import cn.itcast.goods.ssm.po.Book;
import cn.itcast.goods.ssm.service.impl.BookServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;

/**
 * Created by liupanbangbangda on 2018/9/10.
 */
/*注解模式*/
@Controller
@RequestMapping("/book")
public class BookController {

    private BookServiceImpl bookServiceImpl = new BookServiceImpl();
    Book book=new Book();

    public ModelAndView findByMethod(Book book,String pc,HttpServletRequest req) throws SQLException, UnsupportedEncodingException {

        int pcc = getPc(pc);
        String url = getUrl(req);
        PageBean<Book> pb =new PageBean<Book>();

        pb= bookServiceImpl.findByCriteria(pcc,book);
        pb.setUrl(url);
        ModelAndView modelAndView = new ModelAndView();

        if(book.getBid()!=null){
            modelAndView.addObject("book", pb.getBeanList().get(0));
            modelAndView.setViewName("/book/desc");
        }else {
            modelAndView.addObject("pb", pb);
            modelAndView.setViewName("/book/list");
        }

        return modelAndView;

    }

    @RequestMapping("/findByCategory")
    public ModelAndView findByCategory(String pc,HttpServletRequest req) throws IOException, SQLException {
        book=getDecoder(req,"cid");
        return findByMethod(book,pc,req);
    }

    @RequestMapping("/findByPress")
    public ModelAndView findByPress(String press,String pc,HttpServletRequest req) throws ServletException, IOException, SQLException {
        book=getDecoder(req,"press");
        return findByMethod(book,pc,req);
    }


    @RequestMapping("/findByAuthor")
    public ModelAndView findByAuthor(HttpServletRequest req,String pc) throws ServletException, IOException, SQLException {
        book=getDecoder(req,"author");
        return findByMethod(book,pc,req);
    }


    @RequestMapping("/findByBid")
    public ModelAndView findByBid(HttpServletRequest req,String pc) throws ServletException, IOException, SQLException {
        book=getDecoder(req,"bid");
        return findByMethod(book,pc,req);
    }

    private Book getDecoder(HttpServletRequest req,String neededParam) throws UnsupportedEncodingException {
        Book book=new Book();
        String url=getUrl(req);
        String decode= URLDecoder.decode(url.substring(url.lastIndexOf("=")+1), "UTF-8");
        switch (neededParam){
            case "press":
                book.setPress(decode);
                break;
            case "author":
                book.setAuthor(decode);
                break;
            case "cid":
                book.setCid(decode);
                break;
            case "bname":
                book.setBname(decode);
                break;
            case "bid":
                book.setBid(decode);
                break;
        }
        return book;
    }

    @Test
    public void testDecode() throws UnsupportedEncodingException {
        String url="?cid=A你好";
        String decode= URLDecoder.decode(url.substring(url.lastIndexOf("=")+1), "UTF-8");
        System.out.println(decode);
    }

    private String getUrl(HttpServletRequest req) {
        String url = req.getRequestURI() + "?" + req.getQueryString();
		/*
		 * 如果url中存在pc参数，截取掉，如果不存在那就不用截取。
		 */
        int index = url.lastIndexOf("&pc=");
        if(index != -1) {
            url = url.substring(0, index);
        }
        return url;
    }

    private int getPc(String pc) {
        int pcc = 1;
        if(pc != null && !pc.trim().isEmpty()) {
            try {
                pcc = Integer.parseInt(pc);
            } catch(RuntimeException e) {}
        }
        return pcc;
    }

}
