package webPackage.web;

import org.apache.commons.beanutils.BeanUtils;
import webPackage.bean.Category;
import webPackage.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet(name = "AddCategoryServlet",urlPatterns = "/addCategory")
public class AddCategoryServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



                try {
                    //先得到所有的参数集合通过beanUtils封装给实体类
                    Map<String, String[]> parameterMap = request.getParameterMap();
                    Category category = new Category();
                    // 需要用到beanUtils的populate方法把刚刚得到的所有参数的map集合给对象
                    BeanUtils.populate(category, parameterMap);

                    CategoryService categoryService = new CategoryService();
                    boolean b = categoryService.addCategory(category);
                    if (b) {
                        response.setContentType("text/html;charset=utf-8");
                        response.getWriter().write("说明添加商品信息成功");
//                response.getWriter().write("wo cheng gong ");
//                response.setStatus(201);
//                request.getRequestDispatcher("categoty-add.jsp").forward(request, response);
                    } else {
                        response.setContentType("text/html;charset=utf-8");
                        response.getWriter().write("说明添加商品信息失败");
//                response.getWriter().write("funck");
//                response.setStatus(600);
//                request.getRequestDispatcher("categoty-add.jsp").forward(request, response);
                    }

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }




    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);

    }
}
