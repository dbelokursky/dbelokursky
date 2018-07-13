<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload</title>
</head>
<body>
<form formaction=${pageContext.servletContext.contextPath}/upload method="post" enctype="multipart/form-data">
    <input name="images" type="file" multiple><br>
    <input type="submit"><br>
</form>
</body>
</html>
