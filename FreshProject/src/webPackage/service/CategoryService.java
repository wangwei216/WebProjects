package webPackage.service;

import webPackage.bean.Category;
import webPackage.dao.CategoryDao;

import java.sql.SQLException;
import java.util.List;

public class CategoryService {

        public boolean addCategory(Category category) throws SQLException { //这个是判断在service层的逻辑判断有没有错

            CategoryDao categoryDao= new CategoryDao();
            boolean addCategory = categoryDao.addCategory(category);
            return addCategory;
        }

    public List<Category> findCategory() throws SQLException {

            CategoryDao dao = new CategoryDao();
            //拿到DAO层的返回生鲜种类的集合，在service层进行调用，返回的也是一个生鲜的种类集合
        List<Category> categories = dao.queryCategoryList();
        return categories;

    }
}
