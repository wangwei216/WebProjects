package testFliter;

import javax.servlet.*;
import java.io.IOException;

/*
一、使用fliter功能的时候需要先配置filter的xml文件
    1. 需要配置filtername
    2. 需要配置filtermapping

二、Filter可以用来干嘛？
    1. 他可以过滤指定的文件夹或者指定网址
    2. 可以为不同客户提供不同权限的功能
    3. 解决一些字符乱码问题

三、如何对请求进行拦截和放行
    1. 拦截的配置<url-pattern>可以指定以下几点：
        a。以“/”开头并且以“/*”结尾的话如/test/fliter/* 表示在test下的fliter路径下面的所有文件都要被拦截
        b。第二种是以开头为“*.”后缀为“jsp”就是说会对后缀带"jsp"的所有文件都会进行拦截
        c。以空字符串“ ”中间跟的事一个指定的网址的话 ，就会对这个网址所有的内容都会拦截
        d。如果是以“/”开头的话就是默认情况表示程序默认的servlet（一般很少用）
    2. 对访问方式的配置<dispatcher>时什么时候执行fliter的含义(除此之外还可以使用@的方式进行去注解配置不需要在xml中配置)
        a。REQUEST  表示默认的，代表直接访问某个资源时
        b。FORWARD  表示转发时才会执行fliter
        c。INCLUDE  表示包含某个资源时才会执行fliter
        d。ERROR     表示发生错误时，进行跳转是执行fliter
        e。ASYNC     表示异步的时候需要执行
    3. 要想实现放行的话就需要在doFilter方法中用filterChain去调用doFilter方法就可以了
*
* */
public class FirstFliter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("说明Filter运行了，所以测试显示的内容没有显示出来给拦截了");
        filterChain.doFilter(servletRequest,servletResponse);

    }

    @Override
    public void destroy() {

    }
}
