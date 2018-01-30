package ListenerTest;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class FirstServeletContextListener implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("说明这个初始化方法以及被执行了");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("说明servletContext这个销毁的方法被执行了");
    }
}
