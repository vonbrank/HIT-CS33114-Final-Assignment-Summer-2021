package cn.edu.hit.sms.controller;

import cn.edu.hit.sms.dao.UserDao;
import cn.edu.hit.sms.dao.impl.UserDaoImpl;
import cn.edu.hit.sms.entity.user.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    private UserDao userDao = new UserDaoImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String op = request.getParameter("op");
        if(op.equals("login")) Login(request, response);
        if(op.equals("logout")) Logout(request, response);
    }

    private void Login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userid = request.getParameter("userid");
        String pwd = request.getParameter("pwd");
        PrintWriter out = response.getWriter();
        out.println(userid);
        out.println(pwd);
        if(userid.equals("A001") || userid.equals("T006") || userid.equals("S021")) {
            User user = userDao.getById(userid);
            request.getSession().setAttribute("user", user);
            if(user instanceof Staff) response.sendRedirect("./user/staff/");
            if(user instanceof Teacher) response.sendRedirect("./user/teacher/");
            if(user instanceof Student) response.sendRedirect("./user/student/");
        }
        else {
            response.sendRedirect("./auth/login");
        }
    }

    private void Logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("user", null);
        response.sendRedirect("./auth/login");
    }

}
