<%@ page import="ru.job4j.crud.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang='ru'>
<head>
    <meta charset='utf-8'>
    <title>Edit user</title>
    <h1>Edit user</h1>
</head>
<body>
<%!
    public String printIntoTable(User user) {
        StringBuilder sb = new StringBuilder();
        sb.append("<tr>")
                .append("<form  action='/it/list' method='post'>")
                .append("<td><input type='text' name='userId' value=").append(user.getId()).append(" size=3 readonly>" + "</td>")
                .append("<td><input type='text' size='10' name='name' value=").append(user.getName()).append("></td>")
                .append("<td><input type='text' size='10' name='login' value=").append(user.getLogin()).append("></td>")
                .append("<td><input type='text' size='10' name='email' value=").append(user.getEmail()).append("></td>")
                .append("<td><input type='submit' value='Save' formaction = '/it/edit'></td>")
                .append("</form>")
                .append("</tr>");
        return sb.toString();
    }
%>
<a href=<%=String.format("%s/list", request.getContextPath())%>>Users list</a>
<table border=1 bordercolor=black cellpadding=5>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Login</th>
        <th>Email</th>
        <th>Edit</th>
    </tr>
    <%=printIntoTable((User) session.getAttribute("user"))%>
</table>
</body>
</html>