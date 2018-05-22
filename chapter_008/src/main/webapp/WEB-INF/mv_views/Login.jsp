<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="resources/css/mv/login.css">
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->
    <title>Login</title>
</head>
<body>

<div id="fullscreen_bg" class="fullscreen_bg"/>

<div class="container">
    <form class="form-signin" action=${pageContext.servletContext.contextPath}/login method="post">
        <h1 class="form-signin-heading text-muted">Sign In</h1>
        <input type="text" name="login" class="form-control" placeholder="Login" required="" autofocus="">
        <input type="password" name="password" class="form-control" placeholder="Password" required="">
        <button class="btn btn-lg btn-primary btn-block" type="submit">
            Sign In
        </button>
    </form>
</div>

</body>
</html>
