package bean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/*
一、步骤：
    1. 先获取一个session对象；
    2. 创建一个刚刚建的user对象
    3. 为user对象设置属性
    4. 把user对象的值和user名都设置给session
    5.
*
* */
@WebServlet(name = "TestBindingServlet",urlPatterns = "/testblind")
public class TestBindingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user= new User();
        user.setId(1);
        user.setName("wangwei");
        //绑定对象
        session.setAttribute("user",user);
        //解绑对象那个
        session.removeAttribute("user");
    }
}
