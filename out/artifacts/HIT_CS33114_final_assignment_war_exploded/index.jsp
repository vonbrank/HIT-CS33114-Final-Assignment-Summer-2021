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
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <style>
        .body-container {
            min-height: 100vh;
            display: flex;
            justify-content: space-between;
            flex-direction: column;
        }

        .main-content {
            min-height: 40vh;
            /* margin: 0; */
            /* padding: 0; */
            display: flex;
            align-items: center;
            justify-content: space-around;
            flex-direction: column;
            /* border: solid; */
        }

        .bg-video {
            width: 100%;
            height: 100vh;
            /*left: 0px;*/
            /*top: 0px;*/
            z-index: -100;
            /*vertical-align: bottom;*/
            display: block;
            position: absolute;
            overflow: hidden;
            opacity: 0.3;
            /*border: solid;*/
        }

        .bg-video > .video-01 {
            width: 100%;
            height: auto;
            /*object-fit: fill;*/
            /*border: solid red;*/
            /*background-color: red;*/
        }
    </style>
    <title>Welcome to Education System | Harbin Institute of Technology</title>
</head>

<body>
<div class="bg-video">
    <video class="video-01" autoplay muted loop>
        <source src="http://en.hit.edu.cn/public/video/z.mp4"
                type="video/mp4">
    </video>
</div>

<div class="body-container">
    <div class="navbar navbar-expand-sm bg-light navbar-light">
        <div class="container">
            <a class="navbar-brand" href="./">Harbin Institute of Technology</a>
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="#">About</a>
                </li>
            </ul>
        </div>
    </div>
    <div class="main-content">
        <h2 class="main-tile">Education System</h2>
        <div>
            <a href="#" class="btn btn-primary">Login</a>
        </div>
    </div>
    <div class="navbar navbar-expand-sm bg-light navbar-light">
        <div class="container">
            <!-- <a class="navbar-brand" href="#">Von Brank</a> -->
            <ul class="navbar-nav m-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="#">©2021</a>
                </li>
            </ul>
        </div>
    </div>
</div>


</body>

</html>
