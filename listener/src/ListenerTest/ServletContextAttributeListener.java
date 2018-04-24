package ListenerTest;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.annotation.WebListener;

/*
总结：一共是有三大域对象属性监听器：
    1. servletContextAttributeListener
    2. HttpSessionAttributeListener
    3. ServletRequestAttributeListener



一、设置完之后需要进行配置一下文件，要么是配置一下xml文件要是是直接加上注解
* */
@WebListener
public class ServletContextAttributeListener implements javax.servlet.ServletContextAttributeListener
{

    @Override
    public void attributeAdded(ServletContextAttributeEvent servletContextAttributeEvent) {
//这个是设置属性被添加的方法
        System.out.println("属性被添加");
        System.out.println(servletContextAttributeEvent.getName());//这个是获取属性的名的方法
        System.out.println(servletContextAttributeEvent.getValue());//这个是得到属性的值的方法
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent servletContextAttributeEvent) {

        System.out.println("这个方法实现的是属性被删除的方法");
        System.out.println(servletContextAttributeEvent.getName());
        System.out.println(servletContextAttributeEvent.getValue());
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent servletContextAttributeEvent) {
        System.out.println("这个方法实现的是属性被修改的方法");
        System.out.println(servletContextAttributeEvent.getName());
        System.out.println(servletContextAttributeEvent.getValue());
    }
}
