<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset='utf-8'>
    <title>Car card</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <link href="resources/table.css" rel="stylesheet">
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet"
          id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>

    <%--fancyBox--%>
    <script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/3.3.5/jquery.fancybox.min.css"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/fancybox/3.3.5/jquery.fancybox.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <form>
            <button class="btn btn-primary" formaction=${pageContext.servletContext.contextPath}/cars>Main</button>
        </form>
        <div class="panel panel-primary filterable">
            <div class="panel-heading">
                <h3 class="panel-title">Cars sale</h3>
            </div>
            <table class="table">
                <thead>
                <tr class="filters">
                    <th></th>
                    <th><input type="text" class="form-control" placeholder="Brand" disabled></th>
                    <th><input type="text" class="form-control" placeholder="Model" disabled></th>
                    <th><input type="text" class="form-control" placeholder="Transmission" disabled></th>
                    <th><input type="text" class="form-control" placeholder="Suspension" disabled></th>
                    <th><input type="text" class="form-control" placeholder="Engine" disabled></th>
                    <th><input type="text" class="form-control" placeholder="Sold" disabled></th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><img src="img/vw-beetle.png"></td>
                    <td>${car.brand}</td>
                    <td>${car.model}</td>
                    <td>${car.transmission.name}</td>
                    <td>${car.suspension.name}</td>
                    <td>${car.engine.name}</td>
                    <td>${car.sold}</td>
                </tr>
                </tbody>
            </table>
            <%System.out.println((request.getAttribute("car"))); %>
            <c:forEach items="${car.images}" var="img">
                <a data-fancybox="gallery" href="http://localhost:8080/uploads/${img.name}"><img
                        src="http://localhost:8080/uploads/thumb_${img.name}"></a>
            </c:forEach>
        </div>
    </div>
</div>
</div>
</body>
</html>
