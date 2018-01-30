package TestJDBC;

/*
一、什么事SQL语句的注入
    1. 也就是利用了SQL语句的原理，进行拼接。如：sql ="SELECT * FROM user  WHERE id='《a' or 'a'='a》 ' AND password='《a' or 'a'='a》'";
    2. 括号里面的就是注入的内容这样也可以获取到我们数据库里面的东西
一、如何能够防止SQL语句的注入？
    1. 不使用CreateStatement()方法了，使用prepareStatement();方法
    2. 需要先给出一个SQL语句的模板（在使用creatStatement方法的时候不用先给一个SQL语句也可以创建,返回的也是 PreparedStatement类型），也就是把我们需要传进来的参数换成 ？
        （因为prepareStatement()就是利用了必须要事先绑定一个sql语句进行预编译，所以才能实现防止SQL注入）
    3. 执行的方法也是不需要去传参数的（之前传的事一个sql语句，现在什么都不用）
    4. 但是在其执行方法前需要把参数传进去,用PreparedStatemen对象去调用相对应的方法类型去传对应的参数（那个相对应的方法setString(1,"id");）
        第一个是下标从1开始数，第二个是你穿进来的参数名字
总结：当你重复使用同一个SQL的语句的模板的时候，最好使用prepareStatement方法的，不仅提高效率而且还可以提高安全性
* */

import java.sql.*;

public class testLogin2 {

    public void login(String id,String password) throws ClassNotFoundException, SQLException {
        //1. 登录之前需要连接数据库
        Class.forName("com.mysql.jdbc.Driver");
        String url= "jdbc:mysql://localhost:3306/mall";
        Connection connection= DriverManager.getConnection(url,"wangwei","wang5872256");
        String sql ="SELECT * FROM user  WHERE id= ? AND password= ? ";
        PreparedStatement preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setString(1,id);
            preparedStatement.setString(2,password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){

            String name= resultSet.getString("name");

            System.out.println(name+"登录成功");
        }
        else {
            System.out.println("登录失败");
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();

    }

    public  static  void  main(String [] args) throws SQLException, ClassNotFoundException {

        testLogin2 login= new testLogin2();
//        login.login("3","303");
        login.login("a' or 'a'='a","a' or 'a'='a");
    }
}
