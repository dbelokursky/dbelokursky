<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<title>Users list</title>
<!DOCTYPE html>
<html lang='ru'>
<head>
    <meta charset='utf-8'>
    <title>Users list</title>
    <h1>Users list</h1>
    <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/pure-min.css"
          integrity="sha384-nn4HPE8lTHyVtfCBi5yW9d20FjT8BJwUXyWZT9InLYax14RDjBj46LmSztkmNP9w" crossorigin="anonymous">
</head>
<body>

<table class="pure-table pure-table-bordered">
    <thead>
    <tr>
        <th>ID</th>
        <th>Login</th>
        <th>Password</th>
        <th>Role</th>
        <th>Music Type</th>
    </tr>
    </thead>
    <c:forEach items="${allUsers}" var="user">
        <tr>
            <form action=${pageContext.servletContext.contextPath}/list method='post'>
                <td><input type='text' name='id' value=${user.id} size=3 readonly></td>
                <td>${user.login}</td>
                <td>${user.password}</td>
                <td>${user.roleId}</td>
                <td>${user.addressId}</td>
            </form>
        </tr>
    </c:forEach>
</table>

</body>
</html>

