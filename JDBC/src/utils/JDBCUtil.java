package utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtil {

    private static String driver;
    private static String username;
    private static String password;
    private static String url;
//用Ctrl+alt + F 把局部变量变成全局变量， 这个是一个工具类的方法，为了以后能够更好的使用
    //因为这个配置信息不需要有人去调用，他只是为了让其他工具类更好的去使用，所以可以设置成一个静态代码块这样就可以一开始初始化的时候就可以加载完成
   static {
        try {
        ClassLoader classLoader= JDBCUtil.class.getClassLoader();
        InputStream resourceAsStream = classLoader.getResourceAsStream("db.propertise");
        Properties properties = new Properties();
            properties.load(resourceAsStream);
            driver = properties.getProperty("driver");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
            url = properties.getProperty("url");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    //链接数据库
    public static Connection getConnection()  {
        Connection connection =null;
        try {
            Class.forName(driver);
          connection= DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    return connection;


    }

    //释放资源
    public static void realease(Connection connection, Statement preparedStatement, ResultSet resultSet)  {
       if (resultSet!=null){
           try {
               resultSet.close();
           } catch (SQLException e) {

           }
       }
        if(preparedStatement!=null){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {


            }
        }
    }

}
