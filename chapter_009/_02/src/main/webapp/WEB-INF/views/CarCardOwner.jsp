<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit car</title>
    <link href="resources/table.css" rel="stylesheet">
    <link href="resources/uploadButton.css" rel="stylesheet">
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
</head>
<body>
<table class="table">
    <thead>
    <tr>
        <th></th>
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
        <form id="carEdit" method="post">
            <td><input name="id" type="hidden" value="${car.id}"></td>
            <td><input name="brand" type="text" value="${car.brand}" class="form-control" placeholder="Brand"></td>
            <td><input name="model" type="text" value="${car.model}" class="form-control" placeholder="Model"></td>
            <td><input name="transmission" type="text" value="${car.transmission.name}" class="form-control"
                       placeholder="Transmission"></td>
            <td><input name="suspension" type="text" value="${car.suspension.name}" class="form-control"
                       placeholder="Suspension"></td>
            <td><input name="engine" type="text" value="${car.engine.name}" class="form-control" placeholder="Engine">
            </td>
            <td>
                <select name="sold" class="form-control">
                    <option value="false">For sale</option>
                    <option value="true">Sold</option>
                </select>
            </td>
            <td>
                <label class="btn btn-primary">
                    Save<input type="submit" formaction=${pageContext.servletContext.contextPath}/edit value="Save"
                               style="display: none;">
                </label>
            </td>
        </form>
    </tr>
</table>
</body>
</html>
