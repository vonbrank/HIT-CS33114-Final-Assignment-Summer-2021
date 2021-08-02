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

            <div class="register-box col-10 col-md-8 col-lg-6 mt-2">
                <div class="card">
                    <div class="card-header">Welcome to Score Management System</div>
                    <div class="card-body">
                        <form>
                            <div class="form-group">
                                <label for="email">User ID:</label>
                                <input type="email" class="form-control" id="userid" placeholder="Enter user ID">
                            </div>
                            <div class="form-group">
                                <label for="userType">User Type:</label>
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
                                <label for="pwd">Password:</label>
                                <input type="password" class="form-control" id="pwd" placeholder="Enter password">
                            </div>
                            <div class="form-group">
                                <label for="cfpwd">Confirm Password:</label>
                                <input type="password" class="form-control" id="cfpwd" placeholder="Confirm password">
                            </div>
                            <div class="form-group">
                                <label for="name">Name:</label>
                                <input type="password" class="form-control" id="name" placeholder="Enter you name">
                            </div>
                            <div class="form-group">
                                <label for="gender">Gender:</label>
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
                                <label for="majorOrProfession">Major or Profession:</label>
                                <select class="form-control" id="majorOrProfession" name="majorOrProfession">
                                    <option value="Aerospace Engineering" selected>
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

                            <button type="submit" class="btn btn-primary btn-block mt-3">Register</button>
                            <div class="mt-2">
                                <a href="../login">Already have an account? Login</a>
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