package webPackage.web;

import webPackage.bean.User;
import webPackage.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet",urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
                     response.getWriter().write("说明我登录成功了,wenti 是如何转到商品列表");
//                    request.getRequestDispatcher("/category-add.jsp").forward(request,response);
                }
                else {
                    response.setContentType("text/html;charset=utf-8");
                    response.getWriter().write("说明我登录成功了，但是我是自己用密码登录进来的没有选记住密码");
                }

        }else {
            response.setContentType("text/html;charset=utf-8");
                response.getWriter().write("登录失败");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
