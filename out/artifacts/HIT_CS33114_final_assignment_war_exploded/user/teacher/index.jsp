<%@ page import="cn.edu.hit.sms.entity.user.*" %>
<%@ page import="cn.edu.hit.sms.entity.course.Course" %>
<%@ page import="cn.edu.hit.sms.entity.course.Score" %>
<%@ page import="cn.edu.hit.sms.dao.CourseDao" %>
<%@ page import="cn.edu.hit.sms.dao.impl.CourseDaoImpl" %>
<%@ page import="java.util.List" %><%--
    Created by IntelliJ IDEA.
    User: VonBrank
    Date: 2021/8/1
    Time: 20:23
    To change this template use File | Settings | File Templates.
    --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Object obj = request.getSession().getAttribute("user");
    if(!(obj instanceof Teacher) ){
        response.sendRedirect("../../auth/login/?error=Logout");
        return;
    }
    Teacher teacher;
    Course course;
    Score score;
    CourseDao courseDao = new CourseDaoImpl();
    int courseCnt = 0, scoreCnt = 0;
    List<Course> courseList = courseDao.getCourseByTid(((Teacher) obj).getId());
    List<Score> scoreList;
%>
<!DOCTYPE html>
<html lang="zh-cn">

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
        <div class="container" style="display: flex; align-items: center;">
            <a class="navbar-brand" href="../../">Harbin Institute of Technology</a>
            <ul class="navbar-nav ml-auto" style="display: flex; align-items: center;">
                <li class="nav-item">
                    <form action="../../LoginServlet" method="post">
                        <input type="hidden" name="op" value="logout">
                        <button type="submit" class="btn btn-danger btn-sm">登出</button>
                    </form>
                </li>
                <li class="nav-item" id="nav-about">
                    <a class="nav-link" href="../../about">关于</a>
                </li>
            </ul>
        </div>
    </div>
    <div class="container">
        <div class="main-content">
            <h2 class="main-tile">学生成绩管理系统 - 教师</h2>
            <%
                if(request.getParameter("error") != null) {
            %>
            <div class="alert alert-danger alert-dismissible fade show">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <%
                    String error = request.getParameter("error");
                    if(error.equals("IllegalScore")) out.print("请输入一个整数。");
                %>
            </div>
            <%
                }
            %>
            <div class="container">

                <ul class="nav nav-tabs" role="tablist">

                    <li class="nav-item">
                        <a class="nav-link active" data-toggle="tab" href="#course-management">课程管理</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="../profile/">个人设置</a>
                    </li>
                </ul>


                <div class="tab-content">
                    <div id="course-management" class="container tab-pane active mt-3">
                        <div id="courseAccordion">
                            <div class="card">
                                <div class="card-header mb-0">
                                    <table class="table table-hover mb-0">
                                        <thead class="btn-light">
                                        <tr>
                                            <td class="col-sm-2">ID</td>
                                            <td class="col-sm-4">Name</td>
                                            <td class="col-sm-3">Number of Students</td>
                                            <td class="col-sm-3">Option</td>
                                        </tr>
                                        </thead>
                                    </table>
                                </div>
                            </div>

                            <%
                                for (int i=0; i<courseList.size(); i++) {
                                    courseCnt++;
                                    course = courseList.get(i);

                            %>
                            <div class="card">
                                <div class="card-header">
                                    <table class="table table-hover mb-0">
                                        <tbody>
                                        <tr>
                                            <td class="col-sm-2"><%=course.getId()%></td>
                                            <td class="col-sm-4"><%=course.getName()%></td>
<%--                                            <td class="col-sm-2"><%=course.getId()%></td>--%>
<%--                                            <td class="col-sm-2"><%=course.getId()%></td>--%>
                                            <td class="col-sm-3"><%=course.getNumOfStu()%></td>
                                            <td class="col-sm-3">
                                                <div class="btn-group btn-group-sm">
                                                    <a class="card-link btn btn-primary" data-toggle="collapse"
                                                       href="#course-<%=courseCnt%>">详细信息</a>
                                                </div>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div id="course-<%=courseCnt%>" class="collapse" data-parent="#courseAccordion">
                                    <div class="card-body">

                                        <label>
                                            <h4>Student List</h4>
                                        </label>

                                        <table class="table table-hover">
                                            <thead class="bg-light" style="position: sticky; top: 0; z-index: 100">
                                            <tr>
                                                <td>ID</td>
                                                <td>Name</td>
                                                <td>Score</td>
                                                <td>Option</td>
                                            </tr>
                                            </thead>
                                            <tbody>

                                            <%
                                                scoreList = courseDao.getScoreByCid(course.getId());
                                                for(int j=0; j<scoreList.size(); j++) {
                                                    score = scoreList.get(j);
                                                    scoreCnt++;
                                            %>
                                            <tr>
                                                <td class="col-2"><%=score.getSid()%></td>
                                                <td class="col-4"><%=score.getSname()%></td>
                                                <td class="col-2"><%
                                                    if(score.getScore() == -100) out.print("N/A");
                                                    else out.print(score.getScore());
                                                %></td>
                                                <td class="col-2">
                                                    <button type="button" class="btn btn-primary btn-sm"
                                                            data-toggle="modal"
                                                            data-target="#scoreModal-<%=scoreCnt%>">
                                                        修改
                                                    </button>
                                                    <div class="modal fade" id="scoreModal-<%=scoreCnt%>">
                                                        <div class="modal-dialog">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <h4 class="modal-title">输入成绩</h4>
                                                                    <button type="button" class="close"
                                                                            data-dismiss="modal">&times;
                                                                    </button>
                                                                </div>
                                                                <div class="modal-body">
                                                                    <form action="../../CourseServlet" method="post">
                                                                        <input type="hidden" name="sid" value="<%=score.getSid()%>">
                                                                        <input type="hidden" name="sname" value="<%=score.getSname()%>">
                                                                        <input type="hidden" name="cid" value="<%=score.getCid()%>">
                                                                        <input type="hidden" name="cname" value="<%=score.getCname()%>">
                                                                        <input type="hidden" name="op" value="modifyScore">
                                                                        <input type="text" class="form-control"
                                                                               id="scoreModify-<%=scoreCnt%>"
                                                                               name="score" value="" autocomplete="off">
                                                                        <button type="submit"
                                                                                class="btn btn-primary btn-block mt-3">
                                                                            提交 </button>
                                                                    </form>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <button type="button"
                                                                            class="btn btn-secondary btn-danger"
                                                                            data-dismiss="modal">关闭
                                                                    </button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </td>
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