package webPackage.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import webPackage.bean.Category;

import java.sql.SQLException;
import java.util.List;


public class CategoryDao {
    //这个是判断在把数据存给数据库的时候有没有插入数据成功
    public boolean addCategory(Category category) throws SQLException {
            ComboPooledDataSource dataSource = new ComboPooledDataSource();
            QueryRunner queryRunner = new QueryRunner(dataSource);
            String sql = "insert into category values(?,?,?,?,?)";
            int row = queryRunner.update(sql, category.getC_id(), category.getC_name(), category.getPlace(),
                                            category.getType(), category.getCreatetime());

            if (row > 0) {//说明影响数据库的行数了
                return true;
            } else {
                return false;
            }
        }


    public List<Category> queryCategoryList() throws SQLException {

        ComboPooledDataSource dataSource= new ComboPooledDataSource();
        QueryRunner queryRunner= new QueryRunner(dataSource);
        String sql = "select * from category";
        //如果用到query的话 就需要把数据给封装到BeanListHandler中，返回的是一个生鲜种类的集合
        List<Category> categoryList = queryRunner.query(sql, new BeanListHandler<Category>(Category.class));

        return categoryList;

    }
}
