package cn.edu.hit.sms.controller;

import cn.edu.hit.sms.dao.UserDao;
import cn.edu.hit.sms.dao.impl.UserDaoImpl;
import cn.edu.hit.sms.entity.user.*;
import tools.SHA256;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    private UserDao userDao = new UserDaoImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        String op = request.getParameter("op");
        if (op == null) {
            response.sendRedirect("./auth/login/");
            return;
        }
        if(op.equals("login")) Login(request, response);
        if(op.equals("logout")) Logout(request, response);
        if(op.equals("register")) Register(request, response);
    }

    private void Login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userid = request.getParameter("userid");
        String pwd = request.getParameter("pwd");
        String pwdHash = SHA256.get(pwd);
        PrintWriter out = response.getWriter();
        out.println(userid);
        out.println(pwd);
        out.println(pwdHash);
        boolean flag = userDao.login(userid, pwdHash);
        if(flag) {
            User user = userDao.getById(userid);
            request.getSession().setAttribute("user", user);
            if(user instanceof Staff) response.sendRedirect("./user/staff/");
            if(user instanceof Teacher) response.sendRedirect("./user/teacher/");
            if(user instanceof Student) response.sendRedirect("./user/student/");
        }
        else {
            response.sendRedirect("./auth/login/?error=WrongPassword");
        }
    }

    private void Logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("user", null);
        response.sendRedirect("./auth/login/");
    }

    private void Register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String userid = request.getParameter("userid");
        String userType = request.getParameter("userType");
        String pwd = request.getParameter("pwd");
        String cpd = request.getParameter("cpd");
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String majorOrProfession = request.getParameter("majorOrProfession");
        if(!pwd.equals(cpd)){
            response.sendRedirect("./auth/register/?error=PwdInconsistent");
            return;
        }
        if (userid.equals("") || userType.equals("") || pwd.equals("")
                || name.equals("") || gender.equals("")
                || majorOrProfession.equals("")) {
            response.sendRedirect("./auth/register/?error=IncompleteInfo");
            return;
        }
        if (userType.equals("Staff")  && !majorOrProfession.equals("N/A")) {
            response.sendRedirect("./auth/register/?error=StaffWithNoMajor");
            return;
        }
        if ((userType.equals("Teacher") || userType.equals("Student") ) && majorOrProfession.equals("N/A")) {
            response.sendRedirect("./auth/register/?error=TSWithMajor");
            return;
        }

        String pwdAlphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789._@&+-*/";
        for(int i=0; i<pwd.length(); i++) {
            boolean flag = true;
            for(int j=0; j<pwdAlphabet.length(); j++) {
                if(pwd.charAt(i) == pwdAlphabet.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                response.sendRedirect("./auth/register/?error=IllegalPwd");
                return;
            }
        }

        User user = null;
        if(userType.equals("Staff")) user = new Staff(userid, name, gender);
        if(userType.equals("Teacher")) user = new Teacher(userid, name, gender, majorOrProfession);
        if(userType.equals("Student")) user = new Student(userid, name, gender, majorOrProfession);
        if(userDao.add(user, pwd) == -1) {
            response.sendRedirect("./auth/register/?error=UseridExist");
            return;
        }
        response.sendRedirect("./auth/login/?info=RegisterSuccess");
    }

}
