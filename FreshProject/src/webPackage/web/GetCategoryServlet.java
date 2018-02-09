package webPackage.web;

import webPackage.bean.Category;
import webPackage.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "GetCategoryServlet",urlPatterns = "/getCategoryList")
public class GetCategoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CategoryService categoryService= new CategoryService();
        try {
            List<Category> categoryList = categoryService.findCategory();
//然后对返回的categoryList集合进行判断（条件是集合的长度是不是大于0且集合的不等于null）
            if (categoryList!=null&&categoryList.size()>0) {
                //把返回的集合设置给list
                request.setAttribute("list",categoryList);
                //然后把页面分发到列表的jsp页面
                request.getRequestDispatcher("category-list.jsp").forward(request,response);
            }else {
                //如果失败的话还是返回空的jsp页面
                request.getRequestDispatcher("category-list.jsp").forward(request,response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
