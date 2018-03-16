package TestJDBC;

import java.sql.*;

public class testQuery {


    private static Connection connection;
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        /*
        1. 先注册一个数据库的驱动
        2. 链接数据库
        3. 获取操作数据库的对象（Statement）
        4. 遍历结果取出数据库的数据
        5. 最后要把所有创建的对象都要关闭
        * */

      /*
        String url="jdbc:mysql://localhost:3306/mealsystem";
        Connection connection =DriverManager.getConnection(url,"wangwei","wang5872256");
        */
        Driver driver = new com.mysql.jdbc.Driver();
        DriverManager.registerDriver(driver);
        String url="jdbc:mysql://localhost:3306/mealsystem";
        Connection connection = DriverManager.getConnection(url,"wangwei","wang5872256");
        Statement statement = connection.createStatement();

        String sql = "select  * from food";
//        List<User> list = new ArrayList<User>();
        ResultSet resultSet = statement.executeQuery(sql);

        //得到结果集之后需要再把得到的结果集转换为字符串
        while (resultSet.next())
        {
            String price = resultSet.getString("feature");

            System.out.println(price);
        }

        statement.close();
        connection.close();


    }
}
