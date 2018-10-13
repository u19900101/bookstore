package cn.itcast.goods.ssm.controller;

import cn.itcast.goods.ssm.mapper.AdminMapper;
import cn.itcast.goods.ssm.mapper.BookMapper;
import cn.itcast.goods.ssm.po.Admin;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by liupanbangbangda on 2018/10/2.
 */
@Controller
@RequestMapping("/adminLogin")
public class AdminLoginController{
    private ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:applicationContext-dao.xml");
    AdminMapper adminMapper = (AdminMapper) applicationContext.getBean("adminMapper");

    @RequestMapping("/adminLogin")
    public ModelAndView adminLogin(Admin admin,HttpServletRequest req, HttpServletResponse res){
        ModelAndView modelAndView=new ModelAndView();

        Admin adminFind=adminMapper.selectByAdmin(admin);

        if(adminFind==null){
            modelAndView.addObject("msg", "用户名或密码错误！！");
            modelAndView.setViewName("/adminjsps/msg");
        }else{
            req.getSession().setAttribute("admin", admin);
            modelAndView.setViewName("/adminjsps/admin/index");
        }

        return modelAndView;
    }
}
