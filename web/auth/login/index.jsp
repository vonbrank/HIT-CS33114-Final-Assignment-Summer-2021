<%@ page import="cn.edu.hit.sms.entity.user.User" %>
<%@ page import="cn.edu.hit.sms.entity.user.Staff" %>
<%@ page import="cn.edu.hit.sms.entity.user.Teacher" %>
<%@ page import="cn.edu.hit.sms.entity.user.Student" %><%--
    Created by IntelliJ IDEA.
    User: VonBrank
    Date: 2021/8/1
    Time: 20:23
    To change this template use File | Settings | File Templates.
    --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Object obj = request.getSession().getAttribute("user");
    if(obj instanceof User) {
        if(obj instanceof Staff) response.sendRedirect("../../user/staff/");
        if(obj instanceof Teacher) response.sendRedirect("../../user/teacher/");
        if(obj instanceof Student) response.sendRedirect("../../user/student/");
        return;
    }
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
                    <li class="nav-item">
                        <a class="nav-link" href="#">About</a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="container">
            <div class="main-content">
                <h2 class="main-tile">Score Management System</h2>
                <div class="login-box col-10 col-md-8 col-lg-6 col-xl-4">
                    <div class="card">
                        <div class="card-header">Login to manage your scores</div>
                        <div class="card-body">
                            <form action="../../LoginServlet" method="post">
                                <div class="form-group">
                                    <label for="userid">User ID:</label>
                                    <input type="text" class="form-control" id="userid"
                                        name="userid" placeholder="Enter user ID">
                                </div>
                                <div class="form-group">
                                    <label for="pwd">Password:</label>
                                    <input type="password" class="form-control" id="pwd"
                                        name="pwd" placeholder="Enter password">
                                </div>
                                <div class="mt-3"
                                    style="display: flex; flex-direction: row;justify-content: space-between;">
                                    <div class="custom-control custom-checkbox">
                                        <input type="checkbox" class="custom-control-input"
                                            id="customCheckRememberMe" name="rememberMe">
                                        <label class="custom-control-label" for="customCheckRememberMe">Remember
                                            Me</label>
                                    </div>
                                    <div>
                                        <a href="#">Forget Password?</a>
                                    </div>
                                </div>
                                <input type="hidden" name="op" value="login">
                                <button type="submit" class="btn btn-primary btn-block mt-3">Login</button>
                                <div class="mt-2">
                                    <a href="../register">Register</a>
                                </div>
                            </form>
                        </div>
                        <!-- <div class="card-footer">底部</div> -->
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