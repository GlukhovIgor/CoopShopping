<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <form:form method="post" commandName="request">
        <form:hidden path="requestid"/>
        <form:hidden path="requestdate"/>
        <form:hidden path="user"/>
        <form:hidden path="requestcollection"/>
        <fieldset class="form-group">
            <form:label path="accepted">Status</form:label>
            <form:select path="accepted" multiple="true">
                <form:option value="no">Not yet accepted</form:option>
                <form:option value="accepted">Accepted</form:option>
                <form:option value="sent">Item Sent</form:option>
            </form:select>
        </fieldset>

        <button type="submit" class="btn btn-success">Submit</button>
    </form:form>
</div>
<script src="../webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="../webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>