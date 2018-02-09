package webPackage.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import webPackage.bean.User;

import java.sql.SQLException;

/*
知识点：如何使用c3p0连接池去对数据库进行增删改查
    1. 先创建ComboPooledDataSource 拿到连接池中的数据源
    2. 需要创建一个相对应的QueryRunner用的时候用这个对象在
       去调用你需要的查询方式(如果是插入需要返回值就是影响的行数)

一、如何在dao层取到数据库中的信息和用户的信息进行比对，看数据库中是否有
    1. 需要用到c3p0连接池的有关配置需要配合QueryRunner对象使用
*
*
* */
public class UserDao {

    //需要在这个类的里面写一个方法来判断用户的数据和数据库中的数据进行比对
    public boolean checkUser(String name){

        try {
            ComboPooledDataSource dataSource= new ComboPooledDataSource();
            QueryRunner queryRunner= new QueryRunner(dataSource);
            String sql= "select name from user where name=?";
            User user = queryRunner.query(sql, new BeanHandler<User>(User.class),name);
//如果在数据库中没有查询到用户的信息就说明可以注册，否则 就不能注册提示已注册
            if (user==null){//说明么有查询到该用户
                return true;
            }
            else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean register(User user) {

        try {
            ComboPooledDataSource dataSource= new ComboPooledDataSource();
            QueryRunner queryRunner = new QueryRunner(dataSource);
            String sql = "insert into user values(null,?,?,?)";
            //插入数据库的时候返回值是影响的行数
            int row = queryRunner.update(sql, user.getName(),user.getPassword(), user.getEmail());
            if (row>0){ //如果返回影响的行数大于0说插入数据库成功
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
    /*
     * 这个是利用和数据库对比实现的登录的方法（返回的是一个user对象）
     *   1. 先取到数据库的资源，然后和数据库进行比对（需要用到JDBC的知识）
     *   2. 插入SQL语句，然后返回给service对象一个值
     * */
    public User login(String name, String password) throws SQLException {
        ComboPooledDataSource dataSource= new ComboPooledDataSource();
        QueryRunner queryRunner= new QueryRunner(dataSource);
        String sql = "select * from user where name=? and password=?";
        User user = queryRunner.query(sql, new BeanHandler<User>(User.class),name,password);

        return user;

    }
}
