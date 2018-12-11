<%--
  Created by IntelliJ IDEA.
  User: Igor
  Date: 29.11.2018
  Time: 19:03
  To change this template use File | Settings | File Templates.
--%>
<%@ include file ="common/header.jspf" %>
<%@ include file ="common/navigation.jspf" %>
<div class="container">
    <form:form method="post" commandName="item">
        <form:hidden path="itemid"/>
        <fieldset class="form-group">
            <form:label path="name">Name</form:label>
            <form:input path="name" type="text" class="form-control"
                        required="required"/>
            <form:errors path="name" cssClass="text-warning" />
        </fieldset>
        <fieldset class="form-group">
            <form:label path="category">Category</form:label>
            <form:label path="category" class="radio-inline"><form:radiobutton path="category" value = "1" name="category"/>clothes</form:label>
            <form:label path="category" class="radio-inline"><form:radiobutton path="category" value = "2" name="category"/>food</form:label>
            <form:label path="category" class="radio-inline"><form:radiobutton path="category" value = "3" name="category"/>sport</form:label>
            <form:label path="category" class="radio-inline"><form:radiobutton path="category" value = "4" name="category"/>devices</form:label>
            <form:errors path="category" cssClass="text-warning" />
        </fieldset>
        <fieldset class="form-group">
            <form:label path="description">Description</form:label>
            <form:input path="description" type="text" class="form-control"
                        required="required"/>
            <form:errors path="description" cssClass="text-warning" />
        </fieldset>
        <form:hidden path="postdate"/>
        <button type="submit" class="btn btn-success">Submit</button>
    </form:form>
</div>

<%@ include file ="common/footer.jspf" %>
