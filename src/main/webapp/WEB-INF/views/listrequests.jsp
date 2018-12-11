<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CoopShopping</title>
    <link href="../webjars/bootstrap/3.3.6/css/bootstrap.min.css"
          rel="stylesheet">
</head>
<body>
<%@ include file ="common/navigation.jspf" %>
<div class="container">
    <table class="table table-striped table-hover">
        <caption>All Requests for item ${item.name} are</caption>
        <thead>
        <tr>
            <th>Id</th>
            <th>User</th>
            <th>Status</th>
            <th>Date</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requests}" var="request">
            <tr>
                <td>${request.requestid}</td>
                <td><a href="/user/${request.user}">${request.userByUser.firstname} ${request.userByUser.lastname}</a></td>
                <td>${request.accepted}</td>
                <td><fmt:formatDate pattern="dd/MM/yyyy"
                                    value="${request.requestdate}" /></td>
                <td>
                    <c:set var="statussent" value="sent" />
                    <c:if test = "${request.accepted ne statussent}">
                        <a type="button" class="btn btn-info"
                           href="/update-request/${request.requestid}">Change status</a>
                    </c:if>
                    <c:if test = "${request.accepted eq statussent}">
                        <a type="button" class="btn btn-danger"
                           href="/delete-request/${request.requestid}">Delete request</a>
                    </c:if>
                </td>

            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
<script src="../webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="../webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>
