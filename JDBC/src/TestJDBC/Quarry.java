package TestJDBC;

import utils.JDBCUtil;

import java.math.BigDecimal;
import java.sql.*;

public class Quarry {

    private static Connection connection;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

/*

        1. 先注册一个数据库的驱动
        2. 链接数据库
        3. 获取操作数据库的对象（Statement）
        4. 遍历结果取出数据库的数据
        5. 最后要把所有创建的对象都要关闭
*/
//注册数据库驱动
        Connection connection = JDBCUtil.getConnection();
//       //获取数据库的操作对象
       Statement statement= Quarry.connection.createStatement();

        //4. 查询操作, 查询成功之后会遍历一个结果集
            String sql = "select * from user ";
         ResultSet resultSet =statement.executeQuery(sql);
            while (resultSet.next()){
                String name= resultSet.getString("name");

                BigDecimal age= resultSet.getBigDecimal("age");

                //补充： 如果是数字的话就用BigDecimal 对象
                System.out.println("这个是"+name+"年龄"+age);

            }
        resultSet.close();
        statement.close();
        Quarry.connection.close();

    }




    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/mall";
//链接数据库
        return DriverManager.getConnection(url, "wangwei", "wang5872256");
    }
}
