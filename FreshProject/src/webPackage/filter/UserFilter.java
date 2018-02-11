package webPackage.filter;

import webPackage.bean.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/*
一、如何用过滤器实现一个登录的权限控制呢？
    1. 先从服务器端得到session对象
    2. 然后得到session中你需要的的数据
    3. 判断你有没有得到这个对象的session数据，进行控制或者给你跳转到登录界面，让你重新登录
*
* */
@WebFilter(filterName = "UserFilter",urlPatterns = "/category")//这个不是一般的映射，而是过滤器过滤的范围，也就是过滤整个项目
public class UserFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
       //获取session，效验用户信息（需要把req。resp强转为request，response,因为他们本来就是同一哥对象）
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //获取session，效验用户信息
        HttpSession session = request.getSession();
        //然后获取session中的数据
        User user = (User) session.getAttribute("user");
        //判断是否从服务器中得到了这个user对象，来进行过滤,如果等于空的话，就让其跳转到登录界面重新去登陆，否则直接返回
        if (user==null) {
            response.sendRedirect(request.getContextPath()+"/login.jsp");
            return;
        }
        //user 不为空的话，就会直接放行到下面的语句
        chain.doFilter(req, resp);
    }
    public void init(FilterConfig config) throws ServletException {

    }

}
