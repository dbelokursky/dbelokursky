<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang='ru'>
<head>
    <meta charset='utf-8'>
    <title>Edit user</title>
    <h1>Edit user</h1>
</head>
<body>
<a href=<%=String.format("%s/list", request.getContextPath())%>>Users list</a>
<table border=1 bordercolor=black cellpadding=5>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Login</th>
        <th>Email</th>
        <th>Edit</th>
    </tr>
    <%=session.getAttribute("user")%>
</table>
</body>
</html>