<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang='ru'>

<head>
    <meta charset='utf-8'>
    <title>Cars sale</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <link href="resources/table.css" rel="stylesheet">
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
</head>

<body>


<script>
    function getCarCard(id) {
        window.location.href = "./carcard?carId=" + id;
        return false;

    }


    function loadAllItems() {
        $.ajax('./carsjson', {
            method: 'get',
            complete: function (data) {
                var result =
                    "<tr class= 'filters'>" +
                    "<th></th>" +
                    "<th><input type='text' class='form-control' placeholder='Brand' disabled></th>" +
                    "<th><input type='text' class='form-control' placeholder='Model' disabled></th>" +
                    "<th><input type='text' class='form-control' placeholder='Transmission' disabled></th>" +
                    "<th><input type='text' class='form-control' placeholder='Suspension' disabled></th>" +
                    "<th><input type='text' class='form-control' placeholder='Engine' disabled></th>" +
                    "<th><input type='text' class='form-control' placeholder='Sold' disabled></th>" +
                    "</tr>";
                var items = JSON.parse(data.responseText);
                for (var i = 0; i < items.length; i++) {
                    result +=
                        "<tr role='button' onclick='getCarCard(" + items[i].id + ")'>" +
                        "<td><img src='img/vw-beetle.png'></td>" +
                        "<td>" + items[i].brand + "</td>" +
                        "<td>" + items[i].model + "</td>" +
                        "<td>" + items[i].transmission.name + "</td>" +
                        "<td>" + items[i].suspension.name + "</td>" +
                        "<td>" + items[i].engine.name + "</td>" +
                        "<td>" + items[i].sold + "</td>" +
                        "</tr>";
                }
                var table = document.getElementById("items");
                table.innerHTML = result;
            }
        })
    }

    loadAllItems();
</script>

<div class="container">
    <div class="row">
        <form>
            <button class="btn btn-primary" formaction=${pageContext.servletContext.contextPath}/add>Add</button>
        </form>
        <div class="panel panel-primary filterable">
            <div class="panel-heading">
                <h3 class="panel-title">Cars Filters</h3>
            </div>
            <table class="table" id="items">

            </table>
        </div>
    </div>
</div>
</body>
</html>

