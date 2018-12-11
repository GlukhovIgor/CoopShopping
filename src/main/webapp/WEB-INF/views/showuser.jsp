<%--
  Created by IntelliJ IDEA.
  User: Igor
  Date: 02.12.2018
  Time: 17:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CoopShopping</title>
    <link href="../webjars/bootstrap/3.3.6/css/bootstrap.min.css"
          rel="stylesheet">
</head>
<body>
<%@ include file ="common/navigation.jspf" %>
<table class="table table-striped ">
    <caption>User information</caption>
    <tr>
        <td>id</td>
        <td>${user.userid}</td>
    </tr>
    <tr>
        <td>Username</td>
        <td>${user.username}</td>
    </tr>
    <tr>
        <td>First Name</td>
        <td>${user.firstname}</td>
    </tr>
    <tr>
        <td>Last Name</td>
        <td>${user.lastname}</td>
    </tr>
</table>

<script src="../webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="../webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>