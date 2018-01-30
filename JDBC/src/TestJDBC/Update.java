package TestJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
一、如何用JDBC实现插入(修改、删除)数据库的数据的步骤
    1, 注册驱动
    2. 连接数据库
    3. 获取操作数据库的对象
    4. 向数据库插入数据 （1. 定义一个字符串把要插入SQL语句保存进去
        2. 用executeUpdate（）方法去修改也就是插入数据《记得返回的是一个int》）
    5. 输出结果，然后关闭每一个自己新创建的对象
总结：1. 其实插入、修改和删除都是一个类型的，只需要把你要插入的语句改成相对应的数据库语句
      2，与查询语句相比，增删改只是比查询语句少了一个循环遍历的步骤（用的都是数据库语句的知识）

* */
public class Update {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //1, 注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2. 连接数据库
        String url="jdbc:mysql://localhost:3306/mall";
        Connection connection= DriverManager.getConnection(url,"wangwei","wang5872256");
        //3. 获取操作数据库的对象
        Statement statement=connection.createStatement();
        //4. 向数据库插入数据 （1. 定义一个字符串把要插入SQL语句保存进去 2. 用executeUpdate（）方法去修改也就是插入数据《记得返回的是一个int》）
//插入        String sql= "insert into user Values(7,'小美女','女',99)";
//删除         String sql = "DELETE FROM user WHERE id =8";
        String  sql="UPDATE user SET age =66 WHERE name  ='小美女'"; //修改
        int  result =statement.executeUpdate(sql);
        //5. 输出结果，然后关闭每一个自己新创建的对象
        System.out.println(result);
        connection.close();
        statement.close();


    }
}
