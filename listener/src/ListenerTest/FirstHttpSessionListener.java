package ListenerTest;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener()
public class FirstHttpSessionListener implements
        HttpSessionListener {

    // Public constructor is required by servlet spec
    public FirstHttpSessionListener() {
    }


    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
        System.out.println(se.getSession().getId()+"说明创建一个session对话成功");
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
        System.out.println("说明创建一个session对话失败，没有成功");
    }




}
