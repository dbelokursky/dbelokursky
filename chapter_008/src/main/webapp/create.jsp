<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title>Create user</title>
<!DOCTYPE html>
<html lang='ru'>
<head>
    <meta charset='utf-8'>
    <title>Users list</title>
    <h1>Create user</h1>
</head>
<body>
<a href=<%=String.format("%s/list", request.getContextPath())%>>Users list</a>
<table border=1 bordercolor=black cellpadding=5>
    <tr>
        <th>Name</th>
        <th>Login</th>
        <th>Email</th>
    </tr>
    <tr>
        <form action="<%=String.format("%s/create", request.getContextPath())%>" method="post">
            <td><input type='text' size='20' name='name'></td>
            <td><input type='text' size='20' name='login'></td>
            <td><input type='text' size='20' name='email'></td>
            <td><input type='submit'></td>
        </form>
    </tr>
</table>
</body>
</html>