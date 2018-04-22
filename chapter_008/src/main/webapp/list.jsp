<%@ page import="ru.job4j.crud.User" %>
<%@ page import="ru.job4j.crud.UserStore" %>
<%@ page import="java.util.ArrayList" %>
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

<%!
    UserStore userStore = UserStore.INSTANCE;

    private String printAllUsers() {
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> allUsersIds = userStore.getAllUsersIds();
        for (Integer id : allUsersIds) {
            sb.append(printIntoTable(userStore.getUser(id)));
        }
        return sb.toString();
    }
%>

<%!
    private String printIntoTable(User user) {

        StringBuilder sb = new StringBuilder();
        sb.append("<tr>")
                .append("<form  action= ").append("/it/list").append(" method='post'>")
                .append("<td><input type='text' name='userId' value=").append(user.getId()).append(" size=3 readonly></td>")
                .append("<td>").append(user.getName()).append("</td>")
                .append("<td>").append(user.getLogin()).append("</td>")
                .append("<td>").append(user.getEmail()).append("</td>")
                .append("<td>").append(user.getCreateDate()).append("</td>")
                .append("<td><input type='submit' value='Edit' formaction = ").append("/it/edit></td>")
                .append("<td><input type='submit' value='Delete' formaction = ").append("/it/list></td>")
                .append("</form>")
                .append("</tr>");
        return sb.toString();
    }
%>

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
    <%=printAllUsers()%>
</table>
<a href=<%=String.format("%s/create", request.getContextPath())%>>Create new user</a>
</body>
</html>

