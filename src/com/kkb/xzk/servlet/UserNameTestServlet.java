package com.kkb.xzk.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: HanDing
 * @Description:
 * @Date Created in 2021-07-25 19:10
 * @Modified By:
 */
@WebServlet(urlPatterns = "/testusername")
public class UserNameTestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        String userName = req.getParameter("username");
        PrintWriter pw = resp.getWriter();
        if("admin".equals(userName)){
            pw.println("用户名已被注册");
        }else{
            pw.println("用户名有效");
        }
    }
}
