<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
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
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Login</th>
        <th>Email</th>
        <th>Create date</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <tr>
        <%=request.getSession().getAttribute("allUsers")%>
    </tr>
</table>
<a href=<%=String.format("%s/create", request.getContextPath())%>>Create new user</a>
</body>
</html>

