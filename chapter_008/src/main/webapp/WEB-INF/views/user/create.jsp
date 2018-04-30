<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<title>Create user</title>
<!DOCTYPE html>
<html lang='ru'>
<head>
    <meta charset='utf-8'>
    <title>Create user</title>
    <h1>Create user</h1>
</head>
<body>
<a href=${pageContext.servletContext.contextPath}>Users list</a>
<table border=1 bordercolor=black cellpadding=5>
    <tr>
        <th>Name</th>
        <th>Login</th>
        <th>Email</th>
    </tr>
    <tr>
        <form action=${pageContext.servletContext.contextPath}/create method="post">
            <td><input type='text' size='15' name='name'></td>
            <td><input type='text' size='15' name='login'></td>
            <td><input type='text' size='15' name='email'></td>
            <td><input type='submit'></td>
        </form>
    </tr>
</table>
</body>
</html>