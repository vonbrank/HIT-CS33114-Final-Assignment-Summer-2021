<%--
    Created by IntelliJ IDEA.
    User: VonBrank
    Date: 2021/8/1
    Time: 20:23
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
                <ul class="nav nav-pills" role="tablist">
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
                        <div class="card">
                            <div class="card-header">
                                <table class="table table-hover mb-0">
                                    <tbody>
                                    <tr>
                                        <td class="col-sm-6">
                                            <h4>Select your Courses</h4>
                                        </td>
                                        <!-- <td class="col-sm-2">C++</td>
                                                            <td class="col-sm-2">LiHua</td>
                                                            <td class="col-sm-2">32</td>
                                                            <td class="col-sm-2">90</td> -->
                                        <td class="col-sm-6">
                                            <div class="btn-group">
                                                <a class="card-link btn btn-primary" data-toggle="collapse"
                                                   href="#collapse-04">More</a>
                                            </div>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div id="collapse-04" class="collapse">
                                <form action="">
                                    <div class="card">
                                        <div class="card-body mb-0">
                                            <table class="table table-hover mb-0">
                                                <tbody>
                                                <tr>
                                                    <td class="col-sm-2">
                                                        <div class="custom-control custom-checkbox mb-3">
                                                            <input type="checkbox" class="custom-control-input"
                                                                   id="customCheck"
                                                                   name="example1" value="00001">
                                                            <label class="custom-control-label"
                                                                   for="customCheck"></label>
                                                        </div>
                                                    </td>
                                                    <td class="col-sm-2">000001</td>
                                                    <td class="col-sm-2">Data Structure</td>
                                                    <td class="col-sm-2">Lihua</td>
                                                    <td class="col-sm-2">32</td>
                                                    <td class="col-sm-2"></td>
                                                </tr>
                                                <tr>
                                                    <td class="col-sm-2">
                                                        <div class="custom-control custom-checkbox mb-3">
                                                            <input type="checkbox" class="custom-control-input"
                                                                   id="customCheck2"
                                                                   name="example1" value="00001">
                                                            <label class="custom-control-label"
                                                                   for="customCheck2"></label>
                                                        </div>
                                                    </td>
                                                    <td class="col-sm-2">000001</td>
                                                    <td class="col-sm-2">Data Structure</td>
                                                    <td class="col-sm-2">Lihua</td>
                                                    <td class="col-sm-2">32</td>
                                                    <td class="col-sm-2"></td>
                                                </tr>
                                                <tr>
                                                    <td class="col-sm-2">
                                                        <div class="custom-control custom-checkbox mb-3">
                                                            <input type="checkbox" class="custom-control-input"
                                                                   id="customCheck3"
                                                                   name="example1" value="00001">
                                                            <label class="custom-control-label"
                                                                   for="customCheck3"></label>
                                                        </div>
                                                    </td>
                                                    <td class="col-sm-2">000001</td>
                                                    <td class="col-sm-2">Data Structure</td>
                                                    <td class="col-sm-2">Lihua</td>
                                                    <td class="col-sm-2">32</td>
                                                    <td class="col-sm-2"></td>
                                                </tr>

                                                </tbody>
                                            </table>
                                            <button type="submit" class="btn btn-primary btn-block">Submit</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="card">
                                <div class="card-header">
                                    <table class="table table-hover mb-0">
                                        <thead>
                                        <td colspan="6">
                                            <h4>Course List</h4>
                                        </td>
                                        <tr>
                                            <td class="col-sm-2">ID</td>
                                            <td class="col-sm-2">Name</td>
                                            <td class="col-sm-2">Teacher Name</td>
                                            <td class="col-sm-2">Number of Students</td>
                                            <td class="col-sm-2">Score</td>
                                            <td class="col-sm-2">Option</td>

                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td class="col-sm-2">000001</td>
                                            <td class="col-sm-2">C++</td>
                                            <td class="col-sm-2">LiHua</td>
                                            <td class="col-sm-2">32</td>
                                            <td class="col-sm-2">90</td>
                                            <td class="col-sm-2">
                                                <div class="btn-group btn-group-sm">
                                                    <a class="card-link btn btn-danger" href="#">Delete</a>
                                                </div>

                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
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
</body>

</html>