<%@ page import="cn.edu.hit.sms.entity.user.*" %><%-- Created by IntelliJ IDEA. User: VonBrank Date: 2021/8/1 Time: 20:59 To change this template use File | Settings |
    File Templates. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Object obj = request.getSession().getAttribute("user");
    if(!(obj instanceof User) ){
        response.sendRedirect("../../auth/login/?error=Logout");
        return;
    }
    User user = (User) obj;
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
    <title>学生成绩管理系统 | 个人设置</title>
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
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="collapsibleNavbar">
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
        </div>
        <div class="container">
            <div class="main-content">
                <h2 class="main-tile">Score Management System</h2>
                <%
                    if(request.getParameter("error") != null) {
                %>
                <div class="alert alert-danger alert-dismissible fade show">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <%
                        String error = request.getParameter("error");
                        if(error.equals("WrongPassword")) out.print("原密码错误。");
                        if(error.equals("PwdInconsistent")) out.print("两次输入的密码不相同。");
                        if(error.equals("IllegalPwd")) out.print("密码应只包含数字、字母以及 _ . @ & + - * / ");
                        if(error.equals("IncompleteInfo")) out.print("信息不全。");
                    %>
                </div>
                <%
                    }
                %>
                <div class="container">
                    <ul class="nav nav-tabs" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active" data-toggle="tab" href="#personal-info">个人信息</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" data-toggle="tab" href="#security-options">安全选项</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="../../auth/login/">用户中心</a>
                        </li>
                    </ul>

                    <div class="tab-content">
                        <div id="personal-info" class="container tab-pane active"><br>
                            <div class="card">
                                <div class="card-header">检查您的个人信息</div>
                                <div class="card-body">
                                    <table class="table table-hover">
                                        <tbody>
                                            <tr>
                                                <td class="col-6">ID</td>
                                                <td class="col-6"><%=user.getId()%></td>
                                            </tr>
                                            <tr>
                                                <td class="col-6">姓名</td>
                                                <td class="col-6"><%=user.getName()%></td>
                                            </tr>
                                            <tr>
                                                <td class="col-6">性别</td>
                                                <td class="col-6"><%=user.getGender()%></td>
                                            </tr>
                                            <tr>
                                                <td class="col-6">用户类型</td>
                                                <td class="col-6">
                                                    <%
                                                        if(user instanceof Staff) out.print("Staff");
                                                        if(user instanceof Teacher) out.print("Teacher");
                                                        if(user instanceof Student) out.print("Student");
                                                    %>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="col-6">专业 或 方向</td>
                                                <td class="col-6">
                                                    <%
                                                        if(user instanceof Staff) out.print("N/A");
                                                        if(user instanceof Teacher) out.print(((Teacher) user).getProfession());
                                                        if(user instanceof Student) out.print(((Student) user).getMajor());
                                                    %>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="card-footer">
                                    <form action="../../LoginServlet" method="post">
                                        <input type="hidden" name="op" value="logout">
                                        <button type="submit" class="btn btn-danger">登出</button>
                                    </form>

                                </div>
                            </div>
                        </div>
                        <div id="security-options" class="container tab-pane fade"><br>
                            <div class="card">
                                <div class="card-header">修改密码</div>
                                <div class="card-body">
                                    <form action="../../UserServlet" method="post">
                                        <div class="form-group">
                                            <label for="ppd">原密码:</label>
                                            <input type="password" class="form-control" id="ppd" name="ppd"
                                                placeholder="Enter previous password">
                                        </div>
                                        <div class="form-group">
                                            <label for="npd">新密码:</label>
                                            <input type="password" class="form-control" id="npd" name="npd"
                                                placeholder="Enter new password">
                                        </div>
                                        <div class="form-group">
                                            <label for="cpd">确认密码:</label>
                                            <input type="password" class="form-control" id="cpd" name="cpd"
                                                placeholder="Confirm new password">
                                        </div>
                                        <input type="hidden" name="op" value="resetPassword">
                                        <button type="submit" class="btn btn-primary btn-block mt-3">
                                            Confirm</button>
                                    </form>
                                </div>
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