package bean;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/*
一、如何设置对象感知监听器
    1. 需要继承HttpSessionBindingListener的方法，来实现和对象绑定和解绑的功能
*
* */
public class User implements HttpSessionBindingListener{

    private  int id;
    private  String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public void valueBound(HttpSessionBindingEvent httpSessionBindingEvent) {
        //这个是实现绑定对象的方法
        System.out.println("这个对象被绑定了");
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent httpSessionBindingEvent) {

        System.out.println("这个对象被解绑了");
    }
}
