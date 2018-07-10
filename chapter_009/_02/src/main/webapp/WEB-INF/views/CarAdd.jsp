<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new car</title>
    <link href="resources/table.css" rel="stylesheet">
    <link href="resources/uploadButton.css" rel="stylesheet">
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>
<body>
<table class="table">
    <thead>
    <tr>
        <th>Brand</th>
        <th>Model</th>
        <th>Transmission</th>
        <th>Suspension</th>
        <th>Engine</th>
        <th>Status</th>
        <th></th>
    </tr>
    </thead>
    <tr>
        <form formaction=${pageContext.servletContext.contextPath}/add>
            <td><input name="brand" type="text" class="form-control" placeholder="Brand"></td>
            <td><input name="model" type="text" class="form-control" placeholder="Model"></td>
            <td><input name="transmission" type="text" class="form-control" placeholder="Transmission"></td>
            <td><input name="suspension" type="text" class="form-control" placeholder="Suspension"></td>
            <td><input name="engine" type="text" class="form-control" placeholder="Engine"></td>
            <td>
                <select name="sold" class="form-control">
                    <option value="false">For sale</option>
                    <option value="true">Sold</option>
                </select>
            </td>
            <td>
                <button class="btn btn-primary" formmethod="post">
                    Save
                </button>
            </td>
        </form>
    </tr>
</table>
<table class="table">
    <tr>
        <form action="${pageContext.servletContext.contextPath}/upload" method="post" formenctype="multipart/form-data">
            <td>
                <label class="btn btn-primary">
                    Add photo<input type="file" multiple style="display: none;">
                </label>
            </td>
            <td>
                <input type="submit" class="btn btn-primary">
            </td>
        </form>
    </tr>
</table>
</body>
</html>
