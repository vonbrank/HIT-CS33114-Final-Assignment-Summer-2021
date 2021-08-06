<%@ page import="cn.edu.hit.sms.entity.user.*" %><%--
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
    <title>学生成绩管理系统 | 注册</title>
</head>

<body>
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
                    <a class="nav-link" href="../../about">关于</a>
                </li>
            </ul>
        </div>
    </div>
    <div class="container">
        <div class="main-content">
            <h2 class="main-tile">学生成绩管理系统</h2>

            <div class="register-box col-10 col-md-8 col-lg-6 mt-2">
                <div class="card">
                    <div class="card-header">欢迎使用 - 学生成绩管理系统</div>
                    <div class="card-body">
                        <form action="../../LoginServlet" method="post">
                            <%
                                if(request.getParameter("error") != null) {
                            %>
                            <div class="alert alert-danger alert-dismissible fade show">
                                <button type="button" class="close" data-dismiss="alert">&times;</button>
                                <%
                                    String error = request.getParameter("error");
                                    if(error.equals("UseridExist")) out.print("用户ID已存在。");
                                    if(error.equals("PwdInconsistent")) out.print("两次输入的密码不相同。");
                                    if(error.equals("IncompleteInfo")) out.print("所填信息不完整。");
                                    if(error.equals("StaffWithNoMajor")) out.print("管理员不属于任何专业或方向。");
                                    if(error.equals("TSWithMajor")) out.print("师生应有一个专业或方向。");
                                    if(error.equals("IllegalPwd")) out.print("密码应只包含数字、字母以及 _ . @ & + - * / ");
                                %>
                            </div>
                            <%
                                }
                            %>
                            <div class="form-group">
                                <label for="userid">用户 ID:</label>
                                <input type="text" class="form-control" id="userid" name="userid"
                                       placeholder="Enter user ID" autocomplete="off">
                            </div>
                            <div class="form-group">
                                <label for="userType">用户类型:</label>
                                <select class="form-control" id="userType" name="userType">
                                    <option value="Staff" selected>
                                        Staff
                                    </option>
                                    <option value="Teacher">
                                        Teacher
                                    </option>
                                    <option value="Student">
                                        Student
                                    </option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="pwd">密码:</label>
                                <input type="password" class="form-control" id="pwd" name="pwd" placeholder="Enter password">
                            </div>
                            <div class="form-group">
                                <label for="cpd">确认密码:</label>
                                <input type="password" class="form-control" id="cpd" name="cpd" placeholder="Confirm password">
                            </div>
                            <div class="form-group">
                                <label for="name">姓名:</label>
                                <input type="text" class="form-control" id="name" name="name"
                                       placeholder="Enter your name"  autocomplete="off">
                            </div>
                            <div class="form-group">
                                <label for="gender">性别:</label>
                                <select class="form-control" id="gender" name="gender">
                                    <option value="Male" selected>
                                        Male
                                    </option>
                                    <option value="Female">
                                        Female
                                    </option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="majorOrProfession">专业 或 方向:</label>
                                <select class="form-control" id="majorOrProfession" name="majorOrProfession">
                                    <option value="N/A" selected>
                                        N/A
                                    </option>
                                    <option value="Aerospace Engineering">
                                        Aerospace Engineering
                                    </option>
                                    <option value="Electrical Engineering and Automation">
                                        Electrical Engineering and Automation
                                    </option>
                                    <option value="Computer Science">
                                        Computer Science and Technology
                                    </option>
                                    <option value="Electronic Information Engineering">
                                        Electronic Information Engineering
                                    </option>
                                    <option value="Mechanical and Electrical Engineering">
                                        Mechanical and Electrical Engineering
                                    </option>
                                    <option value="Materials Science and Engineering">
                                        Materials Science and Engineering
                                    </option>
                                    <option value="Energy Science and Engineering">
                                        Energy Science and Engineering
                                    </option>
                                    <option value="Instrument science and Technology">
                                        Instrument science and Technology
                                    </option>
                                    <option value="Chemical Engineering and Chemistry">
                                        Chemical Engineering and Chemistry
                                    </option>
                                    <option value="Environmental Science and Engineering">
                                        Environmental Science and Engineering
                                    </option>
                                </select>
                            </div>
                            <input type="hidden" name="op" value="register">
                            <button type="submit" class="btn btn-primary btn-block mt-3">Register</button>
                            <div class="mt-2">
                                <a href="../login">已有账户？ 登录</a>
                            </div>
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