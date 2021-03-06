<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log in</title>
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link href="resources/login.css" rel="stylesheet">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
</head>
<body>
<div id="fullscreen_bg" class="fullscreen_bg"/>
<div class="container">
    <form class="form-signin" method="post" formaction="<c:url value='/j_spring_security_check' />">
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
