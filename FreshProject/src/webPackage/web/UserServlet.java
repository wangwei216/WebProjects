package webPackage.web;

import org.apache.commons.beanutils.BeanUtils;
import webPackage.bean.User;
import webPackage.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;
/*
一、如何抽取servlet把登录和注册放到一个user的servlet中（这个也就是以后框架会帮我们所提供的）
    1.
*
* */
@WebServlet(name = "UserServlet",urlPatterns = "/user")
public class UserServlet extends HttpServlet {
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            //如果要是再doPost中发起的话，需要再调用一下doGet方法
            doGet(request,response);
        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            //需要在前端定一个method方法
            String method = request.getParameter("method");
            if ("login".equals(method)){
                login(request,response);
            } else if ("register".equals(method)){
                register(request,response);
            }

    }

    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        Map<String, String[]> parameterMap = request.getParameterMap();

        User user=new User();
        try {

            //分别将属性设置到对象中，不需要在一个一个区获取了
            BeanUtils.setProperty(user,name,name);
            //把得到的属性map集合封装到对象中去
            BeanUtils.populate(user, parameterMap  );//map中的key必须和目标对象中的属性名相同，不然不能实现拷贝
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        UserService userService= new UserService();
//这一步是通过service层做出的逻辑判断是 否注册成功
        boolean register = userService.register(user);
        //下一步是请求重定向，如果注册成功，就response给浏览器一个登陆的界免
        if (register){
            response.sendRedirect(request.getContextPath()+"login.jsp");
        }
        else {
            //因为需要打印给浏览器含有中文，所以需要设置一下字体
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("注册失败哦");
        }
        //如果失败就在客户端浏览器上打印出注册失败的字样
    }


    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String result= "yes";
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        User user=null;
        UserService userService= new UserService();
        try {
            user = userService.login(name, password);

        } catch (SQLException e) {
            e.printStackTrace();
        }


        if (user!=null){  //如果user不等于空，说明登录成功，否则登录失败
            String remeber = request.getParameter("remeber");
            if (remeber.equals(result)){
                //创建相对应的cookie
                Cookie nameCookie = new Cookie("name",name);
                Cookie passwordCookie = new Cookie("password",password);
                //持久化本地的cookie时间
                nameCookie.setMaxAge(60*60*10);
                passwordCookie.setMaxAge(60*60*10);
                //服务器把cookie发型给客户端
                response.addCookie(nameCookie);
                response.addCookie(passwordCookie);
                response.setContentType("text/html;charset=utf-8");
//               response.sendRedirect("category-add.jsp");
                //当你登录的时候，首先需要先访问getCategoryList获得数据，然后传入生鲜集合
                response.sendRedirect(request.getContextPath()+"/category-list.jsp" );

            }
            else {
                response.setContentType("text/html;charset=utf-8");
//                response.getWriter().write("说明我登录成功了，但是我是自己用密码登录进来的没有选记住密码");
            }

        }else {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("登录失败");
        }
    }
}
