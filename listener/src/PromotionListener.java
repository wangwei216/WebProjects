import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Timer;


/*
一、实现一个商品促销活动的推广
    1. 需要创建一个timer的时间定时器
    2. 设置一个固定的时间安排（用timer对象去调用scheduleAtFixedRate();第一个参数是你要执行的任务，第二个是时间
    3.

*/

@WebListener()
public class PromotionListener implements ServletContextListener {

    // Public constructor is required by servlet spec
    public PromotionListener() {


    }


    public void contextInitialized(ServletContextEvent sce) {

        Timer timer = new Timer();


    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

}