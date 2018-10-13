package cn.itcast.goods.ssm.controller;

import cn.itcast.commons.CommonUtils;
import cn.itcast.goods.ssm.mapper.CategoryMapper;
import cn.itcast.goods.ssm.po.Category;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by liupanbangbangda on 2018/10/3.
 */
@Controller
@RequestMapping("/AdminCategory")
public class AdminCategory {

    private ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:applicationContext-dao.xml");
    CategoryMapper categoryMapper = (CategoryMapper) applicationContext.getBean("categoryMapper");


    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        return findAllCategory();
    }

    public ModelAndView findAllCategory(){
        //设置一级分类的pid叫做  "parent"
        List<Category> parents=categoryMapper.selectParent("parent");
        for(Category pars:parents){
            List<Category> categoryList=categoryMapper.selectParent(pars.getCid());
            pars.setChildren(categoryList);
        }

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("categoryList",parents);
        modelAndView.setViewName("/adminjsps/admin/category/list");
        return modelAndView;
    }



    @RequestMapping("/addOneLevel")
    public ModelAndView addOneLevel(Category category){
        //设置一级分类的pid叫做  "parent"
        category.setPid("parent");
        category.setCid(CommonUtils.uuid());
        categoryMapper.insertSelective(category);
        return findAllCategory();
    }

    @RequestMapping("/loadTwoLevel")
    public ModelAndView loadTwoLevel(){

        //加载所有的一级分类
        List<Category> categoryList=categoryMapper.selectParent("parent");
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("categoryList", categoryList);
        modelAndView.setViewName("/adminjsps/admin/category/add2");
        return modelAndView;

    }


    @RequestMapping("/addTwoLevel")
    public ModelAndView addTwoLevel(Category category){
        category.setCid(CommonUtils.uuid());
        categoryMapper.insertSelective(category);
        return findAllCategory();
    }


    @RequestMapping("/editOneLevelPre")
    public ModelAndView editOneLevelPre(String cid){

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("category",categoryMapper.selectByPrimaryKey(cid));
        modelAndView.setViewName("/adminjsps/admin/category/edit");
        return modelAndView;
    }

    @RequestMapping("/editOneLevel")
    public ModelAndView editOneLevel(Category category){
        categoryMapper.updateByPrimaryKeySelective(category);
        return findAllCategory();
    }

    @RequestMapping("/editTwoLevelPre")
    public ModelAndView editTwoLevelPre(String cid){

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("category",categoryMapper.selectByPrimaryKey(cid));
        modelAndView.addObject("categoryList",categoryMapper.selectParent("parent"));
        modelAndView.setViewName("/adminjsps/admin/category/edit2");
        return modelAndView;
    }

    @RequestMapping("/editTwoLevel")
    public ModelAndView editTwoLevel(Category category){
        categoryMapper.updateByPrimaryKeySelective(category);
        return findAllCategory();
    }



    @RequestMapping("/deleteOneLevel")
    public ModelAndView deleteOneLevel(String cid){
        List<Category> parentCategory= categoryMapper.selectParent(cid);

        if(parentCategory.size()==0){
            categoryMapper.deleteByPrimaryKey(cid);
        }else {
            //设置一级分类的pid叫做  "parent"
            List<Category> parents=categoryMapper.selectParent("parent");
            for(Category pars:parents){
                List<Category> categoryList=categoryMapper.selectParent(pars.getCid());
                pars.setChildren(categoryList);
            }
            ModelAndView modelAndView=new ModelAndView();
            modelAndView.addObject("categoryList",parents);
            modelAndView.addObject("errorMessage","该一级分类下有二级分类，无法删除");
            modelAndView.setViewName("/adminjsps/admin/category/list");
            return modelAndView;
        }
        return findAllCategory();
    }

    @RequestMapping("/deleteTwoLevel")
    public ModelAndView deleteTwoLevel(String cid){
        categoryMapper.deleteByPrimaryKey(cid);
        return findAllCategory();
    }

}
