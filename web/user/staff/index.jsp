<%--
  Created by IntelliJ IDEA.
  User: VonBrank
  Date: 2021/8/1
  Time: 20:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <a class="navbar-brand" href="/education-system">Harbin Institute of Technology</a>
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
                        <a class="nav-link active" data-toggle="tab" href="#teacher-management">Teacher
                            Management</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" data-toggle="tab" href="#student-management">Student Management</a>
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
                    <div id="teacher-management" class="container tab-pane active"><br>
                        <div class="card">
                            <div class="card-body">
                                <table class="table table-hover">
                                    <thead class="btn-light" style="position: sticky; top: 0;">
                                    <tr>
                                        <td>ID</td>
                                        <td>Name</td>
                                        <td>Gender</td>
                                        <td>User Type</td>
                                        <td>Major or Profession</td>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td>000001</td>
                                        <td>XiaoMing</td>
                                        <td>Male</td>
                                        <td>Student</td>
                                        <td>Computer Science and Technology</td>
                                    </tr>
                                    <tr>
                                        <td>000001</td>
                                        <td>XiaoMing</td>
                                        <td>Male</td>
                                        <td>Student</td>
                                        <td>Computer Science and Technology</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>

                        </div>
                    </div>
                    <div id="student-management" class="container tab-pane fade"><br>
                        <div class="card">
                            <div class="card-body">
                                <table class="table table-hover">
                                    <thead class="btn-light" style="position: sticky; top: 0;">
                                    <tr>
                                        <td>ID</td>
                                        <td>Name</td>
                                        <td>Gender</td>
                                        <td>User Type</td>
                                        <td>Major or Profession</td>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td>000001</td>
                                        <td>XiaoMing</td>
                                        <td>Male</td>
                                        <td>Student</td>
                                        <td>Computer Science and Technology</td>
                                    </tr>
                                    <tr>
                                        <td>000001</td>
                                        <td>XiaoMing</td>
                                        <td>Male</td>
                                        <td>Student</td>
                                        <td>Computer Science and Technology</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>

                        </div>
                    </div>
                    <!-- <div id="menu2" class="container tab-pane fade"><br>
                        <h3>Menu 2</h3>
                        <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque
                            laudantium, totam rem aperiam.</p>
                    </div> -->
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
