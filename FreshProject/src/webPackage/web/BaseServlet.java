package webPackage.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*
一、如何实现一个base类?
    1. 删去doPost和doGet方法，重写service方法
    2. 在删去user中servlet的doPost和doGet方法，其原理就是(当客户端再去访问userservlet的话，
    就会找不到doPost和doGet方法，这样的话它只能去找他的父类BaseServlet)
*
* */
@WebServlet(name = "BaseServlet")
public class BaseServlet extends HttpServlet {

        @Override
        protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            req.setCharacterEncoding("utf-8");
            try {
                // 1 获取方法名字
                String method = req.getParameter("method");
                // 2 获取到当前对象的字节码文件
                Class clazz=this.getClass();
                // 3 拿到字节码对象中的方法
                Method clazzMethod = clazz.getMethod(method, HttpServletRequest.class, HttpServletResponse.class);
                // 4 执行方法
                clazzMethod.invoke(this,req,resp);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

/*        try {
            //1. 获取方法名
            String method = req.getParameter("method");
            //2. 获取当前对象的字节码文件（这个当前对象就是谁去访问的userServlet指的就是谁）
            Class  clazz = this.getClass();
            //3. 拿到字节码对象中的方法
            Method clazzMethod = clazz.getMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            //4. 执行方法
            clazzMethod.invoke(this,req,resp);//第一个参数是当前对象，第二个是要传进来的是实参具体要传进来的参数

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }*/
}
