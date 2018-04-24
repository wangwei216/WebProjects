package testFliter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(filterName = "CharsetFilter" ,urlPatterns = "*",initParams = {@WebInitParam(name ="encoding",value = "text/html;charset=utf-8")})
public class CharsetFilter implements Filter {

    private String encoding;

    public void destroy() {
        encoding=null;
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(req, resp);
        if (encoding==null){
            resp.setContentType(encoding);
        }
    }

    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("encoding");

    }

}
