<%--
  Created by IntelliJ IDEA.
  User: Igor
  Date: 28.11.2018
  Time: 20:37
  To change this template use File | Settings | File Templates.
--%>
<%@ include file ="common/header.jspf" %>
<%@ include file ="common/navigation.jspf" %>
<div class="container">
    <table class="table table-striped table-hover">
        <caption>All Items are</caption>
        <thead>
        <tr>
            <th>Id</th>
            <th>User Creator</th>
            <th>Name</th>
            <th>Category</th>
            <th>Description</th>
            <th>Post Date</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${items}" var="item">
            <tr>
                <td><a href="/item/${item.itemid}">${item.itemid}</a></td>
                <td><a href="/user/${item.usercreator}">${item.usercreator}</a></td>
                <td>${item.name}</td>
                <td>${item.categoryByCategory.name}</td>
                <td>${item.description}</td>
                <td><fmt:formatDate pattern="dd/MM/yyyy"
                                    value="${item.postdate}" /></td>

                <td>
                    <c:if test = "${sessionScope.thisid eq item.usercreator}">
                        <a type="button" class="btn btn-primary"
                           href="/update-item?id=${item.itemid}">Edit</a>
                        <a type="button" class="btn btn-warning"
                           href="/delete-item?id=${item.itemid}">Delete</a>
                    </c:if>
                </td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div>
        <a type="button" class="btn btn-success" href="/add-item">Add</a>
    </div>
</div>
<%@ include file ="common/footer.jspf" %>

