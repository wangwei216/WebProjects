package webPackage.web;

import org.apache.commons.beanutils.BeanUtils;
import webPackage.bean.User;
import webPackage.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/*
知识点总结：什么是三层架构？
    1. web层 就是先从静态的前端页面拿到你想得到的值 （其实也就是servlet的使用）
    2. service层 专门处理业务逻辑的（比如看是不是这个用户被注册了，
        如果被注册了需要保存数据就需要调用dao层去跟数据库打交道）
    3. dao层 也就是专门负责跟所有的数据库打交道的（通常都会用到JDBC的知识）
    4. 为什么还要建一个bean的文件层，因为当你想要操作数据库的时候每一张表也就是一个实体
       所以你也需要去建一个实体类的对象，这样能比较容易去操作每个实体的属性

一、如何实现一个项目的注册功能？
    1. 先获取前端的值（这还只是web层
    2. 现在需要创建一个UserService类，吧数据传到service层的架构
    3. service层就是专门来处理业务的逻辑的
        a。先判断用户是否存在，如果存在就不可以注册
            （1）如何判断用户是不是存在呢
                1，需要用到dao层来和数据库里面的信息进行比对，先创建一个UserDao的类在
                2. 在UserDao类中写一个checkUser方法，来从数据库中进行比对
                3. 如果数据库中没有这个用户，就可以注册（回到service层）
        b。如果不存在就把用户注册信息存储到数据库中（也就是调用dao层去实现注册方法）
                1. 在userDao层写一个register方法，先拿到连接池的数据源
                2. 然后调用QuaryRunner对象去调用对象的增删改查方法（需要注意返回值）
                3. 写SQL语句 是取数据还是修改数据的语句，
                4. 对返回的数据进行判断
        c。在service层去调用UserDao层的方法，并且返回的register的boolan值类型给servlet
    4. 在servlet中去调用service层做出的逻辑判断是判断在给浏览器做出一些回复，是注册失败还是成功
       （成功就重定向到登录界面）

二、如何实现记住密码的功能?
    1. 在前端页面上添加一个复选框，并设置一个value参数
    2. 在servlet中去得到remeber的参数，判断是否跟value参数的值相等，
    3. 然后创建如果相等创建一个相对应的cookie对象，
    4. 需要对cookie进行持久化，并且添加到本地的cookie中
    5. 然后用EL表达式从本地的课客户端获取到name，password的value值(有一个缺点就是如果不勾选记住密码的话会报错)

三、如何使用beanUtils对象的方法来设置属性值（还可以设置成int、date类型的）
    1. BeanUtils.setProperty(User,name,name);第一个参数是你要设置的对象，第二个是将要设置的属性，第三个是属性名
         第三个是value是你从配置文件读取到的字符串
    2. BeanUtils.populate(user, parameterMap);map中的key必须和目标对象中的属性名相同，不然不能实现拷贝
       使用之前也必须先用getparameterMap方法返回一个map集合，用于将刚才describe出的Map再装配成一个对象。
    3. 映射的过程就是将页面中的内容先用request获得，然后再将之转换为Map(这里用request.getParameterMap()）
最后使用BeanUtils.populate(user，map)方法将页面各个属性映射到bean中。之后我们就可以这样使用bean.getXxxx()来取值了

四、如何抽取servlet使其变得更加简单？
 (1). 主要实现功能的servlet类的抽取
    1. 在一个主要的板块里面，填写对应的方法，然后还需要在前端页面中添加一个method的方法属性名
    2. 删除
 (2).在doGet和doPost中的逻辑servlet抽取，把逻辑部分代码放到base类中
    1. 因为需要在主servlet中，用到if语句，所以在主对象的servlet中更加简化，需要进行二次抽取servlet
    2.

* */
@WebServlet(name = "RegisterServlet" ,urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
//下一步是请求重定向，如果注册成功，就response给浏览器一个登陆的界面
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
