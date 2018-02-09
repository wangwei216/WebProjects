package webPackage.web;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class CategoryServlet extends HttpServlet {



        //这是一个查询商品表的功能


        //这个是一个增加商品表的功能，也就相当于一个单独的servlet的映射




    public void addCategory(HttpServletRequest request, HttpServletResponse response)  {

      /*  try {
            //先得到所有的参数集合通过beanUtils封装给实体类
            Map<String, String[]> parameterMap = request.getParameterMap();
            Category category = new Category();
            // 需要用到beanUtils的populate方法把刚刚得到的所有参数的map集合给对象
            BeanUtils.populate(category, parameterMap);

            CategoryService categoryService = new CategoryService();
            boolean b = categoryService.addCategory(category);
            if (b) {
                response.setStatus(201);
                request.getRequestDispatcher("categoty-add.jsp").forward(request, response);
            } else {
                response.setStatus(600);
                request.getRequestDispatcher("categoty-add.jsp").forward(request, response);
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/



    }
    }