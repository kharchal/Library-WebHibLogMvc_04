<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<html>
<style>
    .red { color: red }
</style>
<body>
<h2>Pets:</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>NAME</th>
        <th>AGE</th>
        <th>KIND</th>
        <th></th>

    </tr>
    <c:forEach var="p" items="${pets}">
        <tr>
            <td>${p.id}</td>
            <td>${p.name}</td>
            <td>${p.age}</td>
            <td>${p.kind.name}</td>
            <td><a href="${pageContext.request.contextPath}/pets/edit/${p.id}">EDIT</a>
            <a href="${pageContext.request.contextPath}/pets/delete/${p.id}">DELETE</a></td>
        </tr>
    </c:forEach>
</table>
<hr>
<table border="0">
    <f:form commandName="pet" action="${pageContext.request.contextPath}/add" method="post">
        <c:if test="${edit}">
            <tr>
                <td><f:label path="id">ID:</f:label></td>
                <td><f:input path="id" readonly="true"/></td>
            </tr>
        </c:if>

        <tr>
            <td><f:label path="name"><s:message code="msg.name"/>:</f:label></td>
            <td><f:input path="name"/></td>
            <td>
                <span class="red">
                    <f:errors path="name"/>
                </span>
            </td>
        </tr>
        <tr>
            <td><f:label path="age">AGE:</f:label></td>
            <td><f:input path="age"/></td>
            <td>
                <span class="red">
                    <f:errors path="age"/>
                </span>
            </td>
        </tr>
        <tr>
            <td><!-- a href="${pageContext.request.contextPath}/kind/select">select kind</a><br -->
            <f:label path="kind.name">KIND:</f:label></td>
            <td><!-- f:input path="kind.name"/ -->
            <f:select path="kind.id" items="${kinds}"/>
            </td>
            <td>
                <c:set var="id" value=""/>
                <%--<c:if test="${edit}">--%>
                    <%--<c:set var="id" value="/${pet.id}"/>--%>
                <%--</c:if>--%>

                <a href="${pageContext.request.contextPath}/kind${id}">Add new Kind</a> or
                <a href="${pageContext.request.contextPath}/kind/all${id}">Edit</a>
            </td>
        </tr>
        <tr>
            <td colspan="3">
                <input type="submit" value="Submit"/>
                <input type="hidden" name="edit" value="${edit}">
            </td>
        </tr>
    </f:form>
</table>
<hr>
<s:message code="msg.hello"/><br>
<a href="${pageContext.request.contextPath}">welcome</a><br>
<a href="?lang=ru_RU">Russian</a>
<a href="?lang=en">English</a>
</body>
</html>
