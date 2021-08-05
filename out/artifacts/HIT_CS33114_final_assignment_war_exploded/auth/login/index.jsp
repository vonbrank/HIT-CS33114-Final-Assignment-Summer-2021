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
                        <a class="nav-link" href="#">关于</a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="container">
            <div class="main-content">
                <h2 class="main-tile">学生成绩管理系统</h2>
                <div class="login-box col-10 col-md-8 col-lg-6 col-xl-4">
                    <div class="card">
                        <div class="card-header">登录以管理您的成绩</div>
                        <div class="card-body">
                            <form action="../../LoginServlet" method="post">
                                <div class="form-group">
                                    <label for="userid">用户 ID:</label>
                                    <input type="text" class="form-control" id="userid"
                                        name="userid" placeholder="Enter user ID" autocomplete="off">
                                </div>
                                <div class="form-group">
                                    <label for="pwd">密码:</label>
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
                                        <a href="./?error=ForgetPassword">忘记密码?</a>
                                    </div>
                                </div>
                                <input type="hidden" name="op" value="login">

                                <button type="submit" class="btn btn-primary btn-block mt-3">登录</button>
                                <div class="mt-2">
                                    <a href="../register">还没有账户？ 注册</a>
                                </div>
                                <%
                                    if(request.getParameter("error") != null) {
                                %>
                                <div class="alert alert-danger alert-dismissible fade show">
                                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                                    <%
                                        String error = request.getParameter("error");
                                        if(error.equals("WrongPassword")) out.print("用户名不存在或密码错误");
                                        if(error.equals("ForgetPassword")) out.print("忘记密码？ 那你凉了。");
                                        if(error.equals("Logout")) out.print("未登录，请先登录。");
                                    %>
                                </div>
                                <%
                                    }
                                %>
                                <%
                                    if(request.getParameter("info") != null) {
                                %>
                                <div class="alert alert-success alert-dismissible fade show">
                                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                                    <%
                                        String info = request.getParameter("info");
                                        if(info.equals("RegisterSuccess")) out.print("注册成功，请登录。");
                                        if(info.equals("ResetPassword")) out.print("重置密码成功，请重新登录。");
                                    %>
                                </div>
                                <%
                                    }
                                %>
                            </form>
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