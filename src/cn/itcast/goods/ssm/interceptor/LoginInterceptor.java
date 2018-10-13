package cn.itcast.goods.ssm.interceptor;

import cn.itcast.goods.ssm.po.Admin;
import cn.itcast.goods.ssm.po.User;
import org.junit.Test;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by liupanbangbangda on 2018/9/7.
 */
public class LoginInterceptor implements HandlerInterceptor {
   @Test
   public void testContain(){
       String url="http://localhost:8080/category/findAll.action";
       boolean b=url.contains("category/findAll.action");
       System.out.println(b);
   }


    //只拦截 购物车功能的使用，只有登陆之后才可以使用购物车功能
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //判断session
        HttpSession session  = request.getSession();
        //从session中取出用户身份信息
        User user = (User) session.getAttribute("sessionUser");
        Admin admin = (Admin) session.getAttribute("admin");
        if(user!=null||admin!=null){
            //身份存在，放行
            return true;
        }else {
            //游客模式
            //获取请求的url
            String url = request.getRequestURI();
            //判断url是否是公开 地址（实际使用时将公开 地址配置配置文件中）
            //这里公开地址是登陆提交的地址
            if(url.contains("cartitem")||url.contains("order")){
                request.setAttribute("code", "error");
                request.setAttribute("msg","您还未登录，请先登录");
                request.getRequestDispatcher("/jsps/msg.jsp").forward(request, response);
                //不能重定向，不然掉了显示错误信息
                // response.sendRedirect(request.getContextPath() +"/jsps/main.jsp");
                return false;
            }
            //后台管理员登录拦截action
            else if(url.contains("Admin")){
                request.setAttribute("msg","您还未登录，请先登录");
                request.getRequestDispatcher("/jsps/adminjsps/login.jsp").forward(request, response);
                //不能重定向，不然掉了显示错误信息
                // response.sendRedirect(request.getContextPath() +"/jsps/main.jsp");
                return false;
            }else{
                return true;
            }
            //执行到这里表示用户身份需要认证，跳转登陆页面
            //request.getRequestDispatcher("/jsps/main.jsp").forward(request, response);
        }
      //  return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
