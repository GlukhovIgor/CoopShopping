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
<table class="table table-striped ">
    <caption>Item information</caption>
    <tr>
        <td>id</td>
        <td>${item.itemid}</td>
    </tr>
    <tr>
        <td>User Creator</td>
        <td><a href="/user/${item.usercreator}">${item.userByUsercreator.firstname} ${item.userByUsercreator.lastname}</a></td>
    </tr>
    <tr>
        <td>Item Name</td>
        <td>${item.name}</td>
    </tr>
    <tr>
        <td>Post Date</td>
        <td><fmt:formatDate pattern="dd/MM/yyyy"
                        value="${item.postdate}" /></td>
    </tr>
    <tr>
        <td>Description</td>
        <td>${item.description}</td>
    </tr>
</table>
<table class="table table-striped table-condensed">
    <caption>Information about request collection</caption>
    <c:forEach items="${reqcols}" var="reqcol">
        <c:set var = "TR1" value="yes"/>
        <c:set var = "yes" value="true"/>
        <c:set var = "no" value="false"/>
        <c:if test="${reqcol.allowrequests eq TR1}">
            <tr>
                <td>Request collection id</td>
                <td>${reqcol.requestcollectionid}</td>
            </tr>
            <tr>
                <td>Start date</td>
                <td><fmt:formatDate pattern="dd/MM/yyyy"
                                    value="${reqcol.startdate}" /></td>
            </tr>
            <tr>
                <td>End date</td>
                <td><fmt:formatDate pattern="dd/MM/yyyy"
                                    value="${reqcol.enddate}" /></td>
            </tr>
            <c:set var="contains" value="false" />
            <c:forEach var="rq" items="${reqcol.requestsByRequestcollectionid}">
                <c:if test="${rq.user eq sessionScope.userid}">
                    <c:set var="contains" value="true" />
                </c:if>
            </c:forEach>
            <c:if test="${sessionScope.userid eq item.usercreator}">
                <tr>
                    <td colspan="2">
                        <a type="button" class="btn btn-primary"
                           href="/showRequests/${item.itemid}">Show Requests</a>
                    </td>

                </tr>
            </c:if>
            <c:if test="${contains eq yes && sessionScope.userid ne item.usercreator}">
                <tr>
                    <td colspan="2">
                        <button class="btn btn-primary" title="Request has already been sent" disabled>Send Request</button>
                    </td>
                </tr>
            </c:if>
            <c:if test="${contains eq no && sessionScope.userid ne item.usercreator}">
                <tr>
                    <td colspan="2">
                        <a type="button" class="btn btn-primary"
                                       href="/addRequest/${item.itemid}">Send Request</a>
                    </td>
                </tr>
            </c:if>

        </c:if>
    </c:forEach>
</table>
<script src="../webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="../webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>
