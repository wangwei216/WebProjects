package webPackage.service;

import webPackage.bean.Category;
import webPackage.bean.Page;
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

/*
* 这个方法就是为了得到和分页有关的参数
 * */
    public Page findPageCategory(int currentPage,int currentCount ) throws SQLException {

        Page page = new Page();

//        private  int totalPage;//总页数
//        private  int currentPage;//当前页码  （这个可以从前端界面直接得到）
//        private  int currentCount;//当前页显示的数量（这个也可以直接从前端界面得到）
//        private  int totalCount;//总共数据的数目
//
        CategoryDao dao = new CategoryDao();
        int totalCount = dao.queryCount();
        /*也是为了根据总共数据的总数除以 当前显示的总页数，来得到一共的页码数
这里用到了一个math函数的一个方法，就是只要这个数是小数，就要让这个数往上加一，如1.2  就是2  0.8  就是1
        *   总数   每页显示的数目   总页数
        *   9           10        0.9      1
        *   14          10        1.4      2
        */
        //要保证ceil跟的是一个double类型
        int  totalPage = (int) Math.ceil(1.0*totalCount / currentCount);
        //然后使用page的实体类，去把每一个属性设置进去
            page.setCurrentCount(currentCount);
            page.setCurrentPage(currentPage);
            page.setTotalCount(totalCount);//这个是数据库查询出来的
            page.setTotalPage(totalPage);//这个是计算出来的


        /*
        * 这里使用到了一个SELECT  * FROM category LIMIT 1,3(1表示开始查询的位置从0开始，3表示要查询多少条数据)
        *   页数          每页显示的数据         查询的起始位置
        *   1               10                      0
        *   2                10                     10
        *   3               10                      20
        *   规律就是（当前的的页面-1）*每页固定显示的数据 = 每次查询的起始位置
        *   （currentPage-1）*currentCount = 起始位置
        * */
        //计算出每次查询的起始位置
        int startPosition = (currentPage-1)*currentCount;
        //分页查询数据,拿到DAO层的返回生鲜种类的集合，在service层进行调用，返回的也是一个生鲜的种类集合
        List<Category> categories = dao.queryPageCategoryList(startPosition, currentCount);
        //把集合封装到page类中
        page.setList(categories);

        return page;
    }


    public boolean updateCategory(Category category) throws SQLException {

        CategoryDao dao = new CategoryDao();
        boolean updateCategory = dao.updateCategory(category);
        return updateCategory;

    }

    public boolean deleteCategory(Category category) throws SQLException {
        CategoryDao dao = new CategoryDao();
        boolean deleteCategory = dao.deleteCategory(category);
        return deleteCategory;
    }
}
