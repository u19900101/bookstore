package cn.itcast.goods.ssm.controller;

import cn.itcast.goods.ssm.po.User;
import cn.itcast.goods.ssm.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by liupanbangbangda on 2018/9/17.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    UserServiceImpl userServiceImpl=new UserServiceImpl();
    @RequestMapping("/login")
    public String login(HttpSession session,Model model,@Validated User user,BindingResult bindingResult) {
        System.out.println("进入login");
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError objectError : allErrors) {
                System.out.println(objectError.getDefaultMessage());
            }
            model.addAttribute("allErrors", allErrors);
            return "user/login";
        }

        User userSelect=new User();

        System.out.println("test user.getUid()  is  "+user.getUid());
        if(user!=null){
            //查询数据库，校验用户名密码
            userSelect= userServiceImpl.selectByUser(user);
            if (userSelect == null) {
            model.addAttribute("msg", "用户名或密码错误");
            return "user/login";
            }
        }
        session.setAttribute("sessionUser", userSelect);
        return "main";
    }


    @RequestMapping("/logout")
    public String logout(HttpSession session)throws Exception{

        //session过期
        session.invalidate();
        return "user/login";
    }
}
