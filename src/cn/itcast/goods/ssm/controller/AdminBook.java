package cn.itcast.goods.ssm.controller;

import cn.itcast.commons.CommonUtils;
import cn.itcast.goods.pager.PageBean;
import cn.itcast.goods.ssm.mapper.BookMapper;
import cn.itcast.goods.ssm.mapper.CategoryMapper;
import cn.itcast.goods.ssm.po.Book;
import cn.itcast.goods.ssm.po.Cartitem;
import cn.itcast.goods.ssm.po.Category;
import cn.itcast.goods.ssm.service.impl.BookServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

/**
 * Created by liupanbangbangda on 2018/10/3.
 */
@Controller
@RequestMapping("/AdminBook")
public class AdminBook {

    private ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:applicationContext-dao.xml");
    CategoryMapper categoryMapper = (CategoryMapper) applicationContext.getBean("categoryMapper");
    BookMapper bookMapper = (BookMapper) applicationContext.getBean("bookMapper");


    private BookServiceImpl bookServiceImpl = new BookServiceImpl();
    Book book=new Book();


    @RequestMapping("/findAll")
    public ModelAndView findAll(){

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

        modelAndView.setViewName("/adminjsps/admin/book/left");

        return modelAndView;
    }


    public ModelAndView findByMethod(Book book,String pc,HttpServletRequest req) throws SQLException, UnsupportedEncodingException {

        int pcc = getPc(pc);
        String url = getUrl(req);
        PageBean<Book> pb =new PageBean<Book>();

        pb= bookServiceImpl.findByCriteria(pcc,book);
        pb.setUrl(url);

        ModelAndView modelAndView = new ModelAndView();
        if(book.getBid()!=null){
            modelAndView.addObject("book", pb.getBeanList().get(0));

            //当前的二级分类
            Book book1=pb.getBeanList().get(0);
            Category secondLevel=categoryMapper.selectByPrimaryKey(book1.getCid());
            modelAndView.addObject("children",secondLevel);

            //所有的一级分类
            List<Category> parents=categoryMapper.selectParent("parent");
            for(Category pars:parents){
                List<Category> categoryList=categoryMapper.selectParent(pars.getCid());
                pars.setChildren(categoryList);
            }
            modelAndView.addObject("categoryList",parents);
            modelAndView.addObject("bookFirstCategory",secondLevel.getPid());
            modelAndView.setViewName("/adminjsps/admin/book/desc");
            return modelAndView;
        }else {
            modelAndView.addObject("pb", pb);
            modelAndView.setViewName("/adminjsps/admin/book/list");
            return modelAndView;
        }

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

        return findByMethod(book,pc,req);
    }


    @RequestMapping("/findByBid")
      public ModelAndView findByBid(HttpServletRequest req,String pc) throws ServletException, IOException, SQLException {
        book=getDecoder(req,"bid");
        return findByMethod(book,pc,req);
    }

    @RequestMapping("/editOrdelBook")
    public ModelAndView editOrdelBook(Book book,String method,HttpServletRequest req,String pc) throws ServletException, IOException, SQLException {

        ModelAndView modelAndView = new ModelAndView();

        //点击修改按钮
        if(method.equals("editBook")){
            bookMapper.updateByPrimaryKeySelective(book);
        //  modelAndView.addObject("msg", "修改成功！！");
            modelAndView.addObject("book",book);
            modelAndView.setViewName("/adminjsps/admin/book/desc");
            return modelAndView;
        }

        //点击修删除按钮
        else{
            bookMapper.deleteByPrimaryKey(book.getBid());
            modelAndView.addObject("msg", "删除成功！！");
            modelAndView.setViewName("/adminjsps/admin/msg");
            return modelAndView;
        }


    }

    //通过一级分类  来获取二级分类
    @RequestMapping(value={"/loadChildren"},method={RequestMethod.POST,RequestMethod.GET})
    public @ResponseBody ArrayList<Category> loadChildren(@RequestBody String cid){
       List<Category> categoryList=categoryMapper.selectParent(cid);
        ArrayList<Category>arr=new ArrayList<>();
       for(Category cate:categoryList){
           arr.add(cate);
       }
        return arr;
    }



    @RequestMapping("/addBookPre")
    public ModelAndView addBookPre(HttpServletRequest req,String pc) throws ServletException, IOException, SQLException {

        ModelAndView modelAndView = new ModelAndView();
        List<Category> parents=categoryMapper.selectParent("parent");
        for(Category pars:parents){
            List<Category> children=categoryMapper.selectParent(pars.getCid());
            pars.setChildren(children);
        }
        modelAndView.addObject("categoryList", parents);

        modelAndView.setViewName("/adminjsps/admin/book/add");
        return modelAndView;
    }


    @RequestMapping("/addBook")
    public ModelAndView addBook(Book book,MultipartFile image_ww,MultipartFile image_bb) throws ServletException, IOException, SQLException {
        //原始名称
        String originalFilename = image_ww.getOriginalFilename();
        String originalFilename_b = image_bb.getOriginalFilename();
        //上传图片
        if(image_ww!=null && originalFilename!=null && originalFilename.length()>0){
            String pic_path = "D:\\java\\idea\\springmvc\\goods\\WebRoot\\book_img\\";
            String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
            File newFile = new File(pic_path+newFileName);
            image_ww.transferTo(newFile);
            book.setImage_w("book_img/"+newFileName);
        }

        if(image_bb!=null && originalFilename!=null && originalFilename.length()>0){
            String pic_path = "D:\\java\\idea\\springmvc\\goods\\WebRoot\\book_img\\";
            String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename_b.lastIndexOf("."));
            File newFile = new File(pic_path+newFileName);
            image_bb.transferTo(newFile);
            book.setImage_b("book_img/"+newFileName);
        }
        book.setBid(CommonUtils.uuid());
        bookMapper.insert(book);
        // 保存成功信息转发到msg.jsp
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("msg", "添加图书成功！");
        modelAndView.setViewName("/adminjsps/msg");
        return modelAndView;
    }


    private void setBookImag(FileItem fileItem,HttpServletRequest req, HttpServletResponse res, List<FileItem> fileItemList, Book book, int i) throws ServletException, IOException, SQLException {

        FileItem fileItem_1=fileItemList.get(1);
        String filename=fileItem_1.getName();
        int index=filename.lastIndexOf("\\");
        if(index!=1){
            filename=filename.substring(index+1);
        }

        filename=CommonUtils.uuid()+"_"+filename;

        if(!filename.toLowerCase().endsWith(".jpg")){
           // error("图片不是JPG格式的", req, res);
            return;
        }

        String savepath=req.getServletContext().getRealPath("/book_img");
        System.out.println(savepath);
        System.out.println(filename);

        File destFile=new File(savepath,filename);
        try {
            fileItem.write(destFile);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        ImageIcon icon = new ImageIcon(destFile.getAbsolutePath());
        // 2. 通过ImageIcon得到Image对象
        Image image = icon.getImage();
        // 3. 获取宽高来进行校验
        if(image.getWidth(null) > 4500 || image.getHeight(null) > 4550) {
           // error("您上传的图片尺寸超出了4500*4500！", req, res);
            destFile.delete();//删除图片
            return;
        }
        if(i==1){
            book.setImage_w("book_img/"+filename);
        }else if(i==2){
            book.setImage_b("book_img/"+filename);
        }

    }

   /* private void error(String string, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, SQLException {

        req.setAttribute("msg",string );
        req.setAttribute("categoryList",categoryService.findAll());
        req.getRequestDispatcher("/adminjsps/admin/book/add.jsp").forward(req, res);
    }*/
    private Book getDecoder(HttpServletRequest req,String neededParam) throws UnsupportedEncodingException {
        Book book=new Book();
        String url=getUrl(req);
        String decode= URLDecoder.decode(url.substring(url.lastIndexOf("=") + 1), "UTF-8");
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
