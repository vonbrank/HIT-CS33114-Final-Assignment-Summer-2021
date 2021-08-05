<%@ page import="cn.edu.hit.sms.dao.UserDao" %>
<%@ page import="cn.edu.hit.sms.dao.impl.UserDaoImpl" %>
<%@ page import="cn.edu.hit.sms.entity.user.Teacher" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.edu.hit.sms.entity.user.Student" %>
<%@ page import="cn.edu.hit.sms.dao.CourseDao" %>
<%@ page import="cn.edu.hit.sms.dao.impl.CourseDaoImpl" %>
<%@ page import="cn.edu.hit.sms.entity.course.Course" %>
<%@ page import="cn.edu.hit.sms.entity.course.Score" %>
<%@ page import="cn.edu.hit.sms.entity.user.User" %>
<%@ page import="cn.edu.hit.sms.entity.user.Staff" %>
<%--
  Created by IntelliJ IDEA.
  User: VonBrank
  Date: 2021/8/1
  Time: 20:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Object obj = request.getSession().getAttribute("user");
    if(!(obj instanceof Staff) ){
        response.sendRedirect("../../auth/login/?error=Logout");
        return;
    }
    UserDao userDao = new UserDaoImpl();
    CourseDao courseDao = new CourseDaoImpl();
    int teacherCnt = 0, courseCnt = 0, studentCnt = 0;
    List<Teacher> teacherList;
    List<Student> studentList;
    List<Course> courseList;
    List<Score> scoreList;
    Teacher teacher;
    Student student;
    Course course;
    Score score;
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="http://en.hit.edu.cn/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="../../css/index.css">
    <script src="../../js/main.js" defer></script>
    <title>学生成绩管理系统 | 用户中心</title>
</head>

<body>
<!-- <p id="demo"></p> -->
<div class="bg-video">
    <video class="video-01" autoplay muted loop>
        <source src="http://en.hit.edu.cn/public/video/z.mp4" type="video/mp4">
    </video>
</div>

<div class="body-container">
    <div class="navbar navbar-expand-sm bg-light navbar-light">
        <div class="container">
            <a class="navbar-brand" href="../../">Harbin Institute of Technology</a>
            <ul class="navbar-nav ml-auto">
                <li class="nav-item" id="nav-about">
                    <a class="nav-link" href="#">关于</a>
                </li>
                <!-- <li class="nav-item">
                <a class="btn btn-primary " href="#">Logout</a>
            </li> -->
            </ul>
        </div>
    </div>
    <div class="container">
        <div class="main-content">
            <h2 class="main-tile">学生成绩管理系统 - 管理员</h2>

            <div class="container">
                <!-- Nav tabs -->
                <ul class="nav nav-tabs" role="tablist">
                    <!-- <li class="nav-item">
                        <a class="nav-link active" data-toggle="tab" href="#personal-information">Personal Information</a>
                    </li> -->
                    <li class="nav-item">
                        <a class="nav-link active" data-toggle="tab" href="#teacher-management">教师管理</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" data-toggle="tab" href="#student-management">学生管理</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" data-toggle="tab" href="#course-management">课程管理</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="../profile/">个人设置</a>
                    </li>
                    <!-- <li class="nav-item">
                        <a class="nav-link" data-toggle="tab" href="#menu2">Menu 2</a>
                    </li> -->
                </ul>

                <!-- Tab panes -->
                <div class="tab-content">

                    <%
                        teacherList = userDao.getAllTeachers();
//                        out.println(teacherList.size());
                    %>
                    <div id="teacher-management" class="container tab-pane active"><br>
                        <div id="teacherAccordion">
                            <div class="card">
                                <div class="card-header mb-0">
                                    <table class="table table-hover mb-0">
                                        <thead class="btn-light">
                                        <tr>
                                            <td class="col-sm-1">ID</td>
                                            <td class="col-sm-2">姓名</td>
                                            <td class="col-sm-1">性别</td>
                                            <td class="col-sm-4">方向</td>
                                            <td class="col-sm-2">课程数</td>
                                            <td class="col-sm-2">选项</td>
                                        </tr>
                                        </thead>
                                    </table>
                                </div>
                            </div>
                            <%
                                for(int i=0; i<teacherList.size(); i++) {
                            %>
                            <div class="card">
                                <div class="card-header">
                                    <table class="table table-hover mb-0">
                                        <tbody>
                            <%
                                    teacherCnt++;
                                    teacher = teacherList.get(i);
                                    courseList = courseDao.getCourseByTid(teacher.getId());
                            %>
                                        <tr>
                                            <td class="col-sm-1"><%=teacher.getId()%></td>
                                            <td class="col-sm-2"><%=teacher.getName()%></td>
                                            <td class="col-sm-1"><%=teacher.getGender()%></td>
                                            <td class="col-sm-4"><%=teacher.getProfession()%></td>
                                            <td class="col-sm-2"><%=courseList.size()%></td>
                                            <td class="col-sm-2">
                                                <div class="btn-group btn-group-sm">
                                                    <a class="card-link btn btn-primary" data-toggle="collapse"
                                                       href="#<%="teacher-"+teacherCnt%>">Details</a>
                                                    <a class="card-link btn btn-danger" href="#">Delete</a>
                                                </div>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div id="<%="teacher-"+teacherCnt%>" class="collapse" data-parent="#teacherAccordion">
                                    <div class="card-body">
                                        <label for="teacherAssignment-<%=teacherCnt%>">
                                            <h4>Course List</h4>
                                        </label>

                                        <table class="table table-hover">
                                            <thead class="btn-light" style="position: sticky; top: 0;">
                                            <tr>
                                                <td>ID</td>
                                                <td>Name</td>
                                                <td>Number of Students</td>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <%

                                                for(int j=0; j<courseList.size(); j++) {
                                                    course = courseList.get(j);
                                            %>
                                            <tr>
                                                <td><%=course.getId()%></td>
                                                <td><%=course.getName()%></td>
                                                <td><%=course.getNumOfStu()%></td>
                                            </tr>

<%--                                            <tr>--%>
<%--                                                <td>000001</td>--%>
<%--                                                <td>Data Structure</td>--%>
<%--                                                <td>90</td>--%>
<%--                                            </tr>--%>
                                            <%
                                                }
                                            %>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>

                            <%
                                }
                            %>
                        </div>
                    </div>

                    <%
                        studentList = userDao.getAllStudents();
                    %>
                    <div id="student-management" class="container tab-pane fade"><br>
                        <div id="studentAccordion">
                            <div class="card">
                                <div class="card-header mb-0">
                                    <table class="table table-hover mb-0">
                                        <thead class="btn-light">
                                        <tr>
                                            <td class="col-sm-1">ID</td>
                                            <td class="col-sm-2">姓名</td>
                                            <td class="col-sm-1">性别</td>
                                            <td class="col-sm-4">专业</td>
                                            <td class="col-sm-2">选课数</td>
                                            <td class="col-sm-2">选项</td>
                                        </tr>
                                        </thead>
                                    </table>
                                </div>
                            </div>
                            <%
                                for(int i=0; i<studentList.size(); i++) {
                                    studentCnt++;
                                    student = studentList.get(i);
                                    scoreList = courseDao.getScoreBySid(student.getId());
                            %>
                            <div class="card">
                                <div class="card-header">
                                    <table class="table table-hover mb-0">
                                        <tbody>
                                        <tr>
                                            <td class="col-sm-1"><%=student.getId()%></td>
                                            <td class="col-sm-2"><%=student.getName()%></td>
                                            <td class="col-sm-1"><%=student.getGender()%></td>
                                            <td class="col-sm-4"><%=student.getMajor()%></td>
                                            <td class="col-sm-2"><%=scoreList.size()%></td>
                                            <td class="col-sm-2">
                                                <div class="btn-group btn-group-sm">
                                                    <a class="card-link btn btn-primary" data-toggle="collapse"
                                                       href="#score-<%=studentCnt%>">Details</a>
                                                    <a class="card-link btn btn-danger" href="#">Delete</a>
                                                </div>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>

                                </div>
                                <div id="score-<%=studentCnt%>" class="collapse" data-parent="#studentAccordion">
                                    <div class="card-body">
                                        <label>
                                            <h4>Course List</h4>
                                        </label>

                                        <table class="table table-hover">
                                            <thead class="btn-light" style="position: sticky; top: 0;">
                                            <tr>
                                                <td>ID</td>
                                                <td>Name</td>
                                                <td>Score</td>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <%

                                                for (int j=0; j<scoreList.size(); j++) {
                                                    score = scoreList.get(j);
                                            %>
                                            <tr>
                                                <td><%=score.getCid()%></td>
                                                <td><%=score.getCname()%></td>
                                                <td><%=score.getScore()%></td>
                                            </tr>

                                            <%
                                                }
                                            %>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>

                            <%
                                }
                            %>

                        </div>
                    </div>

                    <%
                        courseList = courseDao.getAllCourses();
                    %>
                    <div id="course-management" class="container tab-pane fade">
                        <div id="courseAccordion">
                            <div class="card">
                                <div class="card-header mb-0">
                                    <table class="table table-hover mb-0">
                                        <thead class="btn-light">
                                        <tr>
                                            <td class="col-sm-1">ID</td>
                                            <td class="col-sm-3">Name</td>
                                            <td class="col-sm-2">Teacher ID</td>
                                            <td class="col-sm-2">Teacher Name</td>
                                            <td class="col-sm-2">Number of Students</td>
                                            <td class="col-sm-2">Option</td>
                                        </tr>
                                        </thead>
                                    </table>
                                </div>
                            </div>
                            <%
                                for (int i=0; i<courseList.size(); i++) {
                                    courseCnt++;
                                    course = courseList.get(i);
                                    scoreList = courseDao.getScoreByCid(course.getId());
                            %>
                            <div class="card">
                                <div class="card-header">
                                    <table class="table table-hover mb-0">
                                        <tbody>
                                        <tr>
                                            <td class="col-sm-1"><%=course.getId()%></td>
                                            <td class="col-sm-3"><%=course.getName()%></td>
                                            <td class="col-sm-2"><%=course.getTeacher().getId()%></td>
                                            <td class="col-sm-2"><%=course.getTeacher().getName()%></td>
                                            <td class="col-sm-2"><%=scoreList.size()%></td>
                                            <td class="col-sm-2">
                                                <div class="btn-group btn-group-sm">
                                                    <a class="card-link btn btn-primary" data-toggle="collapse"
                                                       href="#course-<%=courseCnt%>">Details</a>
                                                    <a class="card-link btn btn-danger" href="#">Delete</a>
                                                </div>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>

                                </div>
                                <div id="course-<%=courseCnt%>" class="collapse" data-parent="#courseAccordion">
                                    <div class="card-body">

                                        <form action="" method="POST">
                                            <div class="form-group">
                                                <form action="">
                                                    <label for="teacherAssignment-01">
                                                        <h4>Assign a Teacher</h4>
                                                    </label>
                                                    <select class="form-control" id="teacherAssignment-01"
                                                            name="teacherAssignment-01">
                                                        <option value="Teacher-01" selected>
                                                            Teacher-01
                                                        </option>
                                                        <option value="Teacher-02">
                                                            Teacher-02
                                                        </option>
                                                        <option value="Teacher-03">
                                                            Teacher-03
                                                        </option>
                                                    </select>
                                                    <button type="submit"
                                                            class="btn btn-primary btn-block mt-3">Assign</button>
                                                </form>

                                            </div>
                                        </form>

                                        <label>
                                            <h4>Student List</h4>
                                        </label>

                                        <table class="table table-hover">
                                            <thead class="btn-light" style="position: sticky; top: 0;">


                                            <tr>
                                                <td>ID</td>
                                                <td>Name</td>
                                                <td>Score</td>
                                            </tr>
                                            </thead>
                                            <tbody>

                                            <%
                                                for (int j=0; j<scoreList.size(); j++) {
                                                    score = scoreList.get(j);
                                            %>
                                            <tr>
                                                <td><%=score.getSid()%></td>
                                                <td><%=score.getSname()%></td>
                                                <td><%=score.getScore()%></td>
                                            </tr>
                                            <%
                                                }
                                            %>

                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <%
                                }
                            %>
                        </div>

                    </div>
                </div>
            </div>

        </div>
    </div>
    <div class="navbar navbar-expand-sm bg-light navbar-light">
        <div class="container">
            <ul class="navbar-nav m-auto">
                <li class="nav-item">
                    <a class="nav-link" href="#">©2021</a>
                </li>
            </ul>
        </div>
    </div>
</div>

</body>

</html>