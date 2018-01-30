
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class jdbc {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // 1.注册一个数据库的驱动，但是其实也不需要，因为只要这个类一加载的话，
//        Driver driver = new com.mysql.jdbc.Driver();
//        DriverManager.registerDriver(driver);
        Class.forName("com.mysql.jdbc.Driver");
        // 2.获取连接（1，先有一个url是固定的  jdbc:mysql://localhost:3306/mall  2, 用连接的方法第一个是固定的url，第二个参数是数据库的用户名，第三个是密码）

        String url="jdbc:mysql://localhost:3306/mall";
        Connection connection =DriverManager.getConnection(url,"wangwei","wang5872256");
        connection.close();


        //3. 关闭流
    }
}
