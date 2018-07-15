<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <head>
        <title>Car card</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

        <link href="resources/table.css" rel="stylesheet">
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet"
              id="bootstrap-css">
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    </head>
</head>
<body>
<div class="container">
    <table class="table">
        <div class="row">
            <div class="panel panel-primary filterable">
                <div class="panel-heading">
                    <h3 class="panel-title">Cars card</h3>
                </div>
                <thead>
                <tr>
                    <th></th>
                    <th>Brand</th>
                    <th>Model</th>
                    <th>Transmission</th>
                    <th>Suspension</th>
                    <th>Engine</th>
                    <th>Sold</th>
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
            </div>
        </div>
    </table>

</div>
</body>
</html>
