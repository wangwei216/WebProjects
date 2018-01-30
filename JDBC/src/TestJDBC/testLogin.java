package TestJDBC;

import java.sql.*;
/*
这部分是测试一下如何可以登录成功
    1. 如果不想每次都在创建一个main函数的话  可以用Junit单元测试来进行
*
* */
public class testLogin {

    public void login(String id,String password) throws ClassNotFoundException, SQLException {
        //1. 登录之前需要连接数据库
        Class.forName("com.mysql.jdbc.Driver");
            String url= "jdbc:mysql://localhost:3306/mall";
        Connection connection= DriverManager.getConnection(url,"wangwei","wang5872256");
        Statement statement =connection.createStatement();
            String sql ="SELECT * FROM user  WHERE id='"+id+"'AND password='"+password+"'";
        ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()){

                    String name= resultSet.getString("name");

                System.out.println(name+"登录成功");
            }
            else {
                System.out.println("登录失败");
            }
                resultSet.close();
                statement.close();
                connection.close();

    }

    public  static  void  main(String [] args) throws SQLException, ClassNotFoundException {

        testLogin login= new testLogin();
        login.login("2","202");
//        login.login("a' or 'a'='a","a' or 'a'='a");
    }
}
