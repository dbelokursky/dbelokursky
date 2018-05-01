<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang='ru'>
<head>
    <meta charset='utf-8'>
    <title>Edit user</title>
    <h1>Edit user</h1>
</head>
<body>
<a href=${pageContext.servletContext.contextPath}/list>Users list</a>
<table border=1 bordercolor=black cellpadding=5>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Login</th>
        <th>Email</th>
        <th>Password</th>
        <th>Role</th>
        <th>Edit</th>
    </tr>
    <tr>
        <form action=${pageContext.servletContext.contextPath}/edit method="post">
            <td><input type="text" name="userId" size="3" value=${user.id} readonly></td>
            <td><input type="text" name="name" value=${user.name}></td>
            <td><input type="text" name="login" value=${user.login}></td>
            <td><input type="text" name="email" value=${user.email}></td>
            <td><input type="text" name="password" value=${user.password}></td>
            <td><select name="role">
                <option value="ADMIN">ADMIN</option>
                <option value="USER">USER</option>
            </select></td>
            <td><input type="submit" value="Save" formaction=${pageContext.servletContext.contextPath}/edit></td>
        </form>
    </tr>
</table>
</body>
</html>