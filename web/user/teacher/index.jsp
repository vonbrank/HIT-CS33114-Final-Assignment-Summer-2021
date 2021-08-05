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
        response.sendRedirect("../../auth/login/?op=LogoutError");
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
    <title>Score Management System | Register</title>
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
                    <a class="nav-link" href="#">About</a>
                </li>
                <!-- <li class="nav-item">
                      <a class="btn btn-primary " href="#">Logout</a>
                  </li> -->
            </ul>
        </div>
    </div>
    <div class="container">
        <div class="main-content">
            <h2 class="main-tile">Score Management System</h2>

            <div class="container">
                <!-- Nav tabs -->
                <ul class="nav nav-tabs" role="tablist">
                    <!-- <li class="nav-item">
                                <a class="nav-link active" data-toggle="tab" href="#personal-information">Personal Information</a>
                            </li> -->
                    <li class="nav-item">
                        <a class="nav-link active" data-toggle="tab" href="#course-management">Course Management</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="../profile/">Profile</a>
                    </li>
                    <!-- <li class="nav-item">
                                <a class="nav-link" data-toggle="tab" href="#menu2">Menu 2</a>
                            </li> -->
                </ul>

                <!-- Tab panes -->
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
<%--                                            <td class="col-sm-2">Teacher ID</td>--%>
<%--                                            <td class="col-sm-2">Teacher Name</td>--%>
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
                                                       href="#collapse-01">Details</a>
                                                    <a class="card-link btn btn-danger" href="#">Delete</a>
                                                </div>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div id="collapse-01" class="collapse" data-parent="#courseAccordion">
                                    <div class="card-body">

                                        <label>
                                            <h4>Student List</h4>
                                        </label>

                                        <table class="table table-hover">
                                            <thead class="btn-light" style="position: sticky; top: 0;">
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
                                                <td class="col-2"><%=score.getScore()%></td>
                                                <td class="col-2">
                                                    <button type="button" class="btn btn-primary btn-sm"
                                                            data-toggle="modal"
                                                            data-target="#scoreModal-<%=scoreCnt%>">
                                                        Modify Score
                                                    </button>
                                                    <div class="modal fade" id="scoreModal-<%=scoreCnt%>">
                                                        <div class="modal-dialog">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <h4 class="modal-title">Enter the score</h4>
                                                                    <button type="button" class="close"
                                                                            data-dismiss="modal">&times;
                                                                    </button>
                                                                </div>
                                                                <div class="modal-body">
                                                                    <form action="">
                                                                        <input type="text" class="form-control"
                                                                               id="scoreModify-<%=scoreCnt%>"
                                                                               name="scoreModify-<%=scoreCnt%>">
                                                                        <button type="submit"
                                                                                class="btn btn-primary btn-block mt-3">
                                                                            Submit
                                                                        </button>
                                                                    </form>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <button type="button"
                                                                            class="btn btn-secondary btn-danger"
                                                                            data-dismiss="modal">Cloese
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