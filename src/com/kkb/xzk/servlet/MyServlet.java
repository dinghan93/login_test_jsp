package com.kkb.xzk.servlet;

import com.kkb.xzk.validation.DatabaseValidation;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @Author: HanDing
 * @Description:
 * @Date Created in 2021-07-22 18:59
 * @Modified By:
 */
@WebServlet(urlPatterns = "/test")
public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String userName = req.getParameter("name");
        String userPass = req.getParameter("pass");
        try {
            boolean f = new DatabaseValidation().validate(userName, userPass);
            if(f){
                req.getSession().setAttribute("userName", userName);
                req.getSession().setAttribute("userPass", userPass);
                resp.sendRedirect("success.jsp");
            }else{
                Cookie cookie = new Cookie("uname", userName);
                cookie.setMaxAge(10);
                resp.addCookie(cookie);
                resp.sendRedirect("login.jsp");
            }
        } catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }

    }
}
