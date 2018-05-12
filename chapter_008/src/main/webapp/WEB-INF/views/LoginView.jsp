<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/LoginInputValidation.js"></script>

<html>
<head>
    <title>Log in</title>

    <!-- Stylesheets -->
    <link rel="stylesheet" href="resources/css/form.css">
</head>
<body>
<h1>Please login</h1>
<div class="form">
    <form onsubmit="return checkForm(this)" action=${pageContext.servletContext.contextPath}/login method="post">
        <input name="login" type="text" placeholder="Login"/>
        <input name="password" type="password" placeholder="Password"/>
        <input type="submit"/>
</form>
</div>
</body>
</html>
