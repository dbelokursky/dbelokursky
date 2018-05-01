<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Log in</title>
</head>
<body>
<form action=${pageContext.servletContext.contextPath}/login method="post">
    <label><b>Username</b></label>
    <input type="text" placeholder="Enter Username" name="login" size="15">
    <label><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="password" size="15">
    <input type="submit">
</form>
</body>
</html>
