package cn.itcast.goods.ssm.controller;

import cn.itcast.goods.ssm.mapper.CategoryMapper;
import cn.itcast.goods.ssm.po.Category;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by liupanbangbangda on 2018/9/10.
 */
/*注解模式*/
@Controller
@RequestMapping("/category")
public class CategoryController{

    private ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:applicationContext-dao.xml");
    CategoryMapper categoryMapper = (CategoryMapper) applicationContext.getBean("categoryMapper");

    @RequestMapping("/findAll")
    public ModelAndView findAll() throws Exception {

        System.out.println("进入了controller中.....");

        //设置一级分类的pid叫做  "parent"
        List<Category> parents=categoryMapper.selectParent("parent");
        for(Category pars:parents){
            List<Category> categoryList=categoryMapper.selectParent(pars.getCid());
            pars.setChildren(categoryList);
        }
        // 返回ModelAndView
        ModelAndView modelAndView = new ModelAndView();
        // 相当 于request的setAttribut，在jsp页面中通过parents取数据
        modelAndView.addObject("parents", parents);

        modelAndView.setViewName("left");

        return modelAndView;
    }
/*非注解模式*/
/*public class CategoryController implements org.springframework.web.servlet.mvc.Controller{
    @Override
    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

        System.out.println("进入了controller中.....");

        // 调用service查找 数据库，查询所有图书列表
        CategoryService categoryService=new CategoryService();
        List<Category> parents = categoryService.findAll();

        // 返回ModelAndView
        ModelAndView modelAndView = new ModelAndView();
        // 相当 于request的setAttribut，在jsp页面中通过parents取数据
        modelAndView.addObject("parents", parents);

        // 指定视图
        // 下边的路径，如果在视图解析器中配置jsp路径的前缀和jsp路径的后缀，修改为
        // modelAndView.setViewName("/WEB-INF/jsp/items/itemsList.jsp");
        // 上边的路径配置可以不在程序中指定jsp路径的前缀和jsp路径的后缀
        modelAndView.setViewName("left");

        return modelAndView;
    }*/

}
