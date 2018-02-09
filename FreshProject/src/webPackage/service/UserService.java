package webPackage.service;

import webPackage.bean.User;
import webPackage.dao.UserDao;

import java.sql.SQLException;

public class UserService {

    /*
    * 1. 判断用户是否在数据库中
    * 2，如果不存在的话就把用户信息传到数据库中
    * 3. 用dao层持久化数据库，刚user的dao层去调用register方法，然后把返回的值（也就是是否注册）
    * */

    public boolean register(User user){

        boolean register=false;
        UserDao userDao = new UserDao();
        boolean checkUser = userDao.checkUser(user.getName()); //返回的否的话 就说明可以注册
        if (checkUser) {
            register = userDao.register(user);//如果没有就去注册
        }

        //然后把返回值返回给servlet
        return register;
    }

    public User login(String name, String password) throws SQLException {

        UserDao userDao= new UserDao();
        User user = userDao.login(name, password);

        return user;
    }
}
