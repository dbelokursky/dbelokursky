<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang='ru'>
<head>
    <meta charset='utf-8'>
    <title>Edit user</title>
    <h1>Edit user</h1>
    <link rel="stylesheet" href="resources/css/tables.css">
    <link rel="stylesheet" href="resources/css/buttons.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">

    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
</head>

<script>
    $(document).ready(function () {
        $(function () {
            $("#country").autocomplete({
                source: function (request, response) {
                    $.ajax({
                        url: "countries",
                        type: "GET",
                        data: {
                            term: request.term
                        },
                        dataType: "json",
                        success: function (data) {
                            response(data);
                        }
                    });
                }
            });
        });
    });
</script>

<script>
    $(document).ready(function () {
        $(function () {
            $("#city").autocomplete({
                source: function (request, response) {
                    $.ajax({
                        url: "cities",
                        type: "GET",
                        data: {
                            term: request.term
                        },
                        dataType: "json",
                        success: function (data) {
                            response(data);
                        }
                    });
                }
            });
        });
    });
</script>

<body>
<a href=${pageContext.servletContext.contextPath}/list>Users list</a>
<table border=1 bordercolor=black cellpadding=3>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Login</th>
        <th>Email</th>
        <th>Password</th>
        <th>Role</th>
        <th>Country</th>
        <th>City</th>
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
            <td><input type="text" name="country" id="country" value=${user.country}></td>
            <td><input type="text" name="city" id="city" value=${user.city}></td>
            <td><input type="submit" value="Save" class="action-button shadow animate green"
                       formaction=${pageContext.servletContext.contextPath}/edit></td>
        </form>
    </tr>
</table>
</body>
</html>