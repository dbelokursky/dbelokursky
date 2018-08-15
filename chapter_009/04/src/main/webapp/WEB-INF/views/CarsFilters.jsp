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
    function loadItems() {
        $.ajax('./carstojson', {
            method: 'get',
            complete: function (data) {
                var brands;
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
                    if ($("#sold").is(':checked') && $("#withPhoto").is(':not(:checked)')) {
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
                        brands += "<option value='" + items[i].brand + "'>" + items[i].brand + "</option>";
                    } else if ($("#sold").is(':not(:checked)') && $("#withPhoto").is(':checked')) {
                        if (!$.isEmptyObject(items[i].images) && items[i].sold === false) {
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
                            brands += "<option>" + items[i].brand + "</option>";
                        }
                    } else if ($("#sold").is(':checked') && $("#withPhoto").is(':checked')) {
                        if (!$.isEmptyObject(items[i].images)) {
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
                            brands += "<option>" + items[i].brand + "</option>";
                        }
                    } else {
                        if (items[i].sold === false) {
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
                            brands += "<option value='" + items[i].brand + "'>" + items[i].brand + "</option>";
                        }
                    }
                }
                var list = document.getElementById("brand");
                list.innerHTML = brands;
                var table = document.getElementById("items");
                table.innerHTML = result;
            }
        })
    }


    function getCarCard(id) {
        window.location.href = "./carcard?carId=" + id;
        return false;
    }

    $(document.body).on('change', "#brand", function () {
        $.ajax('./carstojson', {
            method: 'get',
            complete: function (data) {
                var brands;
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
                    if (items[i].brand == $("#brand option:selected").val()) {
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
                    brands += "<option>" + items[i].brand + "</option>";
                }
                var list = document.getElementById("brand");
                list.innerHTML = brands;
                var table = document.getElementById("items");
                table.innerHTML = result;
            }
        })
    });

    loadItems();

</script>
<div class="container">
    <div class="row">
        <form>
            <button class="btn btn-primary" formaction=${pageContext.servletContext.contextPath}/add>Add</button>
        </form>
        <form class="form-inline">
            <div class="form-group">
                <label for="withPhoto">With photo</label>
                <input name="withPhoto" type="checkbox" id="withPhoto" onclick="loadItems()">
            </div>
            <div class="form-group">
                <label for="sold">Show sold</label>
                <input name="sold" type="checkbox" id="sold" onclick="loadItems()">
            </div>
            <div class="form-group">
                <label for="brand">Brand</label>
                <select name="brand" id="brand"></select>
            </div>
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

