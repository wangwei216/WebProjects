package ListenerTest;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TestAttributeListenerServlet",urlPatterns = "/testlistener")
public class TestAttributeListenerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//第一步先拿到servletcontext对象
        ServletContext servletContext = request.getServletContext();
        servletContext.setAttribute("name","wangwei"); //这一步就相当于触发监听器的设置属性的方法
        servletContext.setAttribute("name", "wangwei");//这一步相当于触发对之前设置的属性进行修改的方法
        servletContext.removeAttribute("name");//触发监听器的删除方法

    }
}
