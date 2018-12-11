<%--
  Created by IntelliJ IDEA.
  User: Igor
  Date: 01.12.2018
  Time: 19:20
  To change this template use File | Settings | File Templates.
--%>
<%@ include file ="common/header.jspf" %>
<%@ include file ="common/navigation.jspf" %>
<table class="table table-striped table-hover">
    <caption>Users are</caption>
    <thead>
    <tr>
        <th>id</th>
        <th>FirstName</th>
        <th>LastName</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
        <tr>
            <td><a href="/user/${user.userid}">${user.userid}</a></td>
            <td>${user.firstname}</td>
            <td>${user.lastname}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<%@ include file="common/footer.jspf"%>
