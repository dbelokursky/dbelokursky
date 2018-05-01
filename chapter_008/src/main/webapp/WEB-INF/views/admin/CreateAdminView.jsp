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
<a href=${pageContext.servletContext.contextPath}/list>Users list</a>
<table border=1 bordercolor=black cellpadding=5>
    <tr>
        <th>Name</th>
        <th>Login</th>
        <th>Email</th>
        <th>Password</th>
        <th>Role</th>
    </tr>
    <tr>
        <form action=${pageContext.servletContext.contextPath}/create method="post">
            <td><input type='text' size='15' name='name'></td>
            <td><input type='text' size='15' name='login'></td>
            <td><input type='text' size='15' name='email'></td>
            <td><input type='text' size='15' name='password'></td>
            <td><select name="role">
                <option value="ADMIN">ADMIN</option>
                <option value="USER">USER</option>
            </select></td>
            <td><input type='submit' value="Save"></td>
        </form>
    </tr>
</table>
</body>
</html>