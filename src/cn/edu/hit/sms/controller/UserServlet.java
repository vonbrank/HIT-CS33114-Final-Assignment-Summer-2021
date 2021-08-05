package cn.edu.hit.sms.controller;

import cn.edu.hit.sms.dao.UserDao;
import cn.edu.hit.sms.dao.impl.UserDaoImpl;
import cn.edu.hit.sms.entity.user.User;
import tools.SHA256;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserServlet", value = "/UserServlet")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object obj = request.getSession().getAttribute("user");
        if(!(obj instanceof User) ){
            response.sendRedirect("../../auth/login/?op=LogoutError");
            return;
        }
        if(request.getParameter("op") == null) return;
        if(request.getParameter("op").equals("resetPassword")) resetPassword(request, response);
    }

    private void  resetPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDaoImpl();
        String ppd = request.getParameter("ppd");
        String npd = request.getParameter("npd");
        String cpd = request.getParameter("cpd");

        if(ppd == null || npd == null || cpd == null) {
            response.sendRedirect("./user/profile/?error=WrongPassword");
            return;
        }


        if(ppd.equals("") || npd.equals("") || cpd.equals("")) {
            response.sendRedirect("./user/profile/?error=IncompleteInfo");
            return;
        }

        User user = (User) request.getSession().getAttribute("user");
        boolean flag = userDao.login(user.getId(), SHA256.get(ppd));
        if(!flag) {
            response.sendRedirect("./user/profile/?error=WrongPassword");
            return;
        }


        String pwdAlphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789._@&+-*/";
        for(int i=0; i<npd.length(); i++) {
            flag = true;
            for(int j=0; j<pwdAlphabet.length(); j++) {
                if(npd.charAt(i) == pwdAlphabet.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                response.sendRedirect("./user/profile/?error=IllegalPwd");
                return;
            }
        }

        if(!npd.equals(cpd)) {
            response.sendRedirect("./user/profile/?error=PwdInconsistent");
            return;
        }

        userDao.modify(user, npd);
        request.getSession().setAttribute("user", null);
        response.sendRedirect("./auth/login/?info=ResetPassword");
    }
}
