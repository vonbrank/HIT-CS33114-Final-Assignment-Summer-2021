package cn.edu.hit.sms.controller;

import cn.edu.hit.sms.dao.CourseDao;
import cn.edu.hit.sms.dao.UserDao;
import cn.edu.hit.sms.dao.impl.CourseDaoImpl;
import cn.edu.hit.sms.dao.impl.UserDaoImpl;
import cn.edu.hit.sms.entity.course.Course;
import cn.edu.hit.sms.entity.course.Score;
import cn.edu.hit.sms.entity.user.Student;
import cn.edu.hit.sms.entity.user.Teacher;
import cn.edu.hit.sms.entity.user.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "CourseServlet", value = "/CourseServlet")
public class CourseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        Object obj = request.getSession().getAttribute("user");
        if(!(obj instanceof User) ){
            response.sendRedirect("./auth/login/?error=Logout");
            return;
        }
        String op = request.getParameter("op");
        if (op == null) {
            response.sendRedirect("./auth/login/");
            return;
        }
        if(op.equals("selectCourse")) selectCourse(request, response);
        if(op.equals("deleteCourse")) deleteCourse(request, response);
        if(op.equals("modifyScore")) modifyScore(request, response);
        if(op.equals("assignTeacher")) assignTeacher(request, response);
    }

    private void selectCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object obj = request.getSession().getAttribute("user");
        CourseDao courseDao = new CourseDaoImpl();
        Course course;
        Score score;
        if(!(obj instanceof Student) ){
            response.sendRedirect("./auth/login/?error=Logout");
            return;
        }
        Student student = (Student) obj;
        String[] cidList = request.getParameterValues("courseSelect");
        PrintWriter out = response.getWriter();
        for (String cid : cidList) {
//            out.println(cid);
            course = courseDao.getCourseByCid(cid);
            score = new Score(student, course, -100);
            courseDao.addScore(score);
        }
        response.sendRedirect("./auth/login/");
    }

    private void deleteCourse (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object obj = request.getSession().getAttribute("user");
        CourseDao courseDao = new CourseDaoImpl();
        Course course;
        Score score;
        if(!(obj instanceof Student) ){
            response.sendRedirect("./auth/login/?error=Logout");
            return;
        }
        Student student = (Student) obj;
        String cid = request.getParameter("cid");
        String cname = request.getParameter("cname");
        score = new Score(student.getId(), student.getName(), cid, cname, 0);
        PrintWriter out = response.getWriter();
//        out.println(score);
//        out.println();
        courseDao.removeScore(score);
        response.sendRedirect("./auth/login/");
    }

    private void modifyScore (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sid = request.getParameter("sid");
        String sname = request.getParameter("sname");
        String cid = request.getParameter("cid");
        String cname = request.getParameter("cname");
        String scoreStr = request.getParameter("score");

        String regEx = "^-?[0-9]+$";
        Pattern pat = Pattern.compile(regEx);
        Matcher mat = pat.matcher(scoreStr);
        if (!mat.find()) {
            response.sendRedirect("./user/teacher/?error=IllegalScore");
            return;
        }
        CourseDao courseDao = new CourseDaoImpl();
        int scoreValue = Integer.parseInt(scoreStr);
        Score score = new Score(sid, sname, cid, cname, scoreValue);
        System.out.println(courseDao.modifyScore(score));
        response.sendRedirect("./user/teacher/");
    }

    private void assignTeacher  (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tid = request.getParameter("teacherAssignment");
        String cid = request.getParameter("cid");
        CourseDao courseDao = new CourseDaoImpl();
        UserDao userDao = new UserDaoImpl();
        PrintWriter out = response.getWriter();
        out.println(tid);
        out.println(cid);
        Course course = courseDao.getCourseByCid(cid);
        course.setTeacher((Teacher) userDao.getById(tid));
        out.println(course);
        courseDao.modifyCourse(course);
        response.sendRedirect("./user/staff/");
    }
}
