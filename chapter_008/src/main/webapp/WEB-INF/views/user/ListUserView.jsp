<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<title>Users list</title>
<!DOCTYPE html>
<html lang='ru'>
<head>
    <meta charset='utf-8'>
    <title>Users list</title>
    <h1>Users list</h1>
</head>
<body>

<table border=1 bordercolor=black cellpadding=5>
    <form action=${pageContext.servletContext.contextPath}/edit method='post'>
        <input type='submit' value='Edit my account'>
    </form>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Login</th>
        <th>Email</th>
        <th>Create date</th>
        <th>Role</th>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <form action=${pageContext.servletContext.contextPath}/list method='post'>
                <td><input type='text' name='userId' value=${user.id} size=3 readonly></td>
                <td>${user.name}</td>
                <td>${user.login}</td>
                <td>${user.email}</td>
                <td>${user.createDate}</td>
                <td>${user.role.name}</td>
            </form>
        </tr>
    </c:forEach>
</table>
</body>
</html>

