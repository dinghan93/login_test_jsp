package com.kkb.xzk.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author: HanDing
 * @Description:
 * @Date Created in 2021-07-24 0:12
 * @Modified By:
 */

@WebFilter(urlPatterns = "/*")
public class FirstFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器1初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("过滤器1调用");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        request.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("userName");
        String uri = request.getRequestURI();
        if(uri.endsWith("success.jsp") && userName==null){
            response.sendRedirect("login.jsp");
        }
        //调用下一个过滤器，如果没有过滤器则调用servlet。
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("过滤器1结束");
    }

    @Override
    public void destroy() {
        System.out.println("过滤器1销毁");
    }
}
