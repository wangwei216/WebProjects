package webPackage.web;


import org.apache.commons.beanutils.BeanUtils;
import webPackage.bean.Category;
import webPackage.bean.Page;
import webPackage.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

/*
一、如何添加生鲜的种类
    1. 先创建一个CategoryServlet类
        1）。先创建一个category实体类
        2）。在获取所有的参数集合，通过beanUtils封装给category实体类
        3）。需要创建一个categoryService层的类来做逻辑判断
            1. 因为你需要和数据库层联系，所有先建立一个dao层的CategoryDao类
                1）。你需要先创建一个addCategory方法，用来插入数据（参数则是category类对象）
                2）。然后就需要用到数据连接池，QueryRunner对象来操作数据库
                3）。创建SQL语句，用queryRunner对象去调用对应的方法,而且传进来的参数是利用bean的category对象来去得到对象的属性
                （查询的时候返回的是String，增删改返回的都是影响的行数int）
                4）。判断返回的行数是否大于0来返回相对应的结果（回到service逻辑层）
            2. 创建一个addCategory的Boolean返回值的方法
            3. 然后使用dao层的对象去调用addCategory方法把Boolean类型的返回值返回(回到servlet层)
   2. 然后在用创建好的service层的对象去调用service层的逻辑判断方法把结果返回出一个Boolean类型的值
   3. 再根据返回的Boolean类型的值去判断最后返回给浏览器的的页面（要么是服务器直接打印给浏览器，要么是请求转发给另一个界面）
总结：在service层和dao层的异常尽量直接抛出去更好，用try catch的方法反而不好
* */
@WebServlet(name = "CategoryServlet",urlPatterns = "/category")
public class CategoryServlet extends BaseServlet {



        //这是一个查询商品表的功能


        //这个是一个增加商品表的功能，也就相当于一个单独的servlet的映射


    public void addCategory(HttpServletRequest request, HttpServletResponse response)  {

        try {
            //先得到所有的参数集合通过beanUtils封装给实体类
            Map<String, String[]> parameterMap = request.getParameterMap();
            Category category = new Category();
            // 需要用到beanUtils的populate方法把刚刚得到的所有参数的map集合给对象
            BeanUtils.populate(category, parameterMap);
            //还需要在创建一个当前的时间
            category.setCreatetime(new Date());
            CategoryService categoryService = new CategoryService();
            boolean b = categoryService.addCategory(category);
            if (b) {
                response.setStatus(201);
                request.getRequestDispatcher("/category-add.jsp").forward(request, response);
            } else {
                response.setStatus(600);
                request.getRequestDispatcher("/category-add.jsp").forward(request, response);
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
/*
一、如何实现管理系统中的查询数据列表？
    1. 先在servlet功能中调用service的查询方法（去service层去创建一个findCategory查询方法）
        1），然后在service层中调用dao层的方法（然后到dao层去创建一个queryCategoryList方法）
            a。用c3p0的知识链接数据库，写SQL语句，然后去调用query方法（第一个穿进去的是SQL，第二个传进去的是new出来的beanListHandler）
            b。得到的返回值是一个beanListHandler的集合对象
        2）。返回到service层，得到的是一个List（category）的集合，然后返回给servlet
    2. 对拿到的数据集合进行判断，看是不是为null且大于0，这就说明拿到了categoryList的数据
    3. 如果拿到了数据，就把数据设置给request域中的list使用（setAttribute方法）,然后把页面重定向到jsp的前端列表界面
    4. 最后是当你在登录进来的时候，要先把登录成功的那个界面跳转到category页面获取数据，然后提供method方法
*
*
* */
    public void getCategoryList(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {

        //为了是实现分页功能，这里需要从前端页面得到两个参数currentPage,currentCount 然后转为int类型
        int  currentPage = Integer.parseInt(request.getParameter("currentPage"));
        int  currentCount = Integer.parseInt(request.getParameter("currentCount"));
        //最后如果网页第一次没有给我们传入参数值的时候，我们需要自己设置默认值
        if (currentPage==0){
            currentPage=1;
        }
        if (currentCount==0){
            currentCount=10;
        }
        // 1 调用service中的查询方法
        CategoryService service = new CategoryService();
        Page page= service.findPageCategory(currentPage,currentCount);//这个地方返回的也是一个page类
        if (page!=null) {
            //这个部分肯定有问题
            request.setAttribute("page",page);
            request.getRequestDispatcher("/category-list.jsp").forward(request,response);
        }else {
            request.getRequestDispatcher("/category-list.jsp").forward(request,response);
        }
    }

    /*修改生鲜信息的功能*/
    public void updateCategory(HttpServletRequest request, HttpServletResponse response)  {

        try {
            //第一步是先获取参数的集合，然后把参数封装给实体（category）
            Map<String, String[]> parameterMap = request.getParameterMap();
            Category category= new Category();
            BeanUtils.populate(category,parameterMap);//意识就是先去到参数的map集合，然后通过映射封装给bean
            CategoryService service = new CategoryService();
            boolean updateCategory = service.updateCategory(category);
            if (updateCategory) {
                //如果是真的话 说明修改成功，然后重定向到category-list。jsp界面
                response.sendRedirect(request.getContextPath()+
                        "/category?method=getCategoryList&currentPage=1&currentCount=10");
            }
            else {
                response.getWriter().write("说明修改失败");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*删除生鲜的功能*/
    public void deleteCategory(HttpServletRequest request, HttpServletResponse response)  {

        try {

            Map<String, String[]> parameterMap = request.getParameterMap();
            Category category= new Category();
            BeanUtils.populate(category,parameterMap);
            //然后去调用service层的方法
            CategoryService categoryService = new CategoryService();
            boolean deleteCategory = categoryService.deleteCategory(category);
            if (deleteCategory){

                    response.sendRedirect(request.getContextPath()+
                            "/category?method=getCategoryList&currentPage=1&currentCount=10");
            }
            else {
                response.getWriter().write("删除失败");
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




}