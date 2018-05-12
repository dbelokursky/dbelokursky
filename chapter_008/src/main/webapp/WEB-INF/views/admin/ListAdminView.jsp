<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<title>Users list</title>
<!DOCTYPE html>
<html lang='ru'>
<head>
    <meta charset='utf-8'>
    <title>Users list</title>
    <h1>Users list</h1>
    <link rel="stylesheet" href="resources/css/tables.css">
    <link rel="stylesheet" href="resources/css/buttons.css">
</head>
<body>

<table border=1 bordercolor=black cellpadding=5>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Login</th>
        <th>Email</th>
        <th>Create date</th>
        <th>Role</th>
        <th>Country</th>
        <th>City</th>
        <th>Edit</th>
        <th>Delete</th>
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
                <td>${user.country}</td>
                <td>${user.city}</td>
                <td><input type='submit' value='Edit' class="action-button shadow animate green"
                           formaction=${pageContext.servletContext.contextPath}/edit></td>
                <td><input type='submit' value='Delete' class="action-button shadow animate red"
                           formaction=${pageContext.servletContext.contextPath}/list></td>
            </form>
        </tr>
    </c:forEach>
</table>
<a href=${pageContext.servletContext.contextPath}/create>Create new user</a>
</body>
</html>

