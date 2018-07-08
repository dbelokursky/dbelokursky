<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang='ru'>
<head>
    <meta charset='utf-8'>
    <title>Cars sale</title>
    <h1>Cars sale</h1>
</head>
<body>

<table>
    <tr>
        <th>Brand</th>
        <th>Model</th>
        <th>Transmission</th>
        <th>Suspension</th>
        <th>Engine</th>
    </tr>
    <c:forEach items="${cars}" var="car">
        <tr>
            <td>${car.brand}</td>
            <td>${car.model}</td>
            <td>${car.transmission.name}</td>
            <td>${car.suspension.name}</td>
            <td>${car.engine.name}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

