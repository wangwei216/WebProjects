package webPackage.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
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

        //查询数据库中的所有数据总和
    public List<Category> queryCategoryList() throws SQLException {

        ComboPooledDataSource dataSource= new ComboPooledDataSource();
        QueryRunner queryRunner= new QueryRunner(dataSource);
        String sql = "select * from category";
        //如果用到query的话 就需要把数据给封装到BeanListHandler中，返回的是一个生鲜种类的集合
        List<Category> categoryList = queryRunner.query(sql, new BeanListHandler<Category>(Category.class));
        return categoryList;
    }
    //分页查询数据
    public List<Category> queryPageCategoryList(int startPosion, int currentCount) throws SQLException {

        ComboPooledDataSource dataSource= new ComboPooledDataSource();
        QueryRunner queryRunner= new QueryRunner(dataSource);
        String sql = "select * from category limit ?,?";
        //如果用到query的话 就需要把数据给封装到BeanListHandler中，返回的是一个生鲜种类的集合
        //如果是SQL语句中需要参数的话，先用问号代替，然后在query方法中的新建的那个BeanListHandler后面加上参数
        List<Category> categoryList = queryRunner.query(sql, new BeanListHandler<Category>(Category.class),startPosion,currentCount);
        return categoryList;
    }


    public int queryCount() throws SQLException {

        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        QueryRunner queryRunner=new QueryRunner(dataSource);
        String sql = "select count(*) category ";
        //如果使用聚合函数的话，就必须new出来一个 ScalarHandler<>()来储存数据，最后返回的还是一个Long类型的值
        Long query = queryRunner.query(sql, new ScalarHandler<>());
        //还需要把Long类型转为int类型
        return query.intValue();
    }

    public boolean updateCategory(Category category) throws SQLException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        QueryRunner queryRunner = new QueryRunner(dataSource);
        String sql = "update category set c_name=?,place=? ,type=? where c_id=?";
        int row = queryRunner.update(sql, category.getC_name(), category.getPlace(), category.getType(), category.getC_id());
                if (row>0){ //返回值大于0说明影响行数大于0 这说明成功了
                    return true;
                }
                else {
                    return false;
                }

    }

    public boolean deleteCategory(Category category) throws SQLException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        QueryRunner queryRunner = new QueryRunner(dataSource);
        String sql = "delete from category where c_id = ? ";
        int row = queryRunner.update(sql, category.getC_id());
           if (row>0){
               return true;
           }else {
               return false;
           }

    }
}
