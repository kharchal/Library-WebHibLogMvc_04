<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<html>
<style>
    .red { color: red }
</style>
<body>
<h2>Kinds:</h2>
<a href="${pageContext.request.contextPath}/kind">Add new kind</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>NAME</th>
        <th></th>

    </tr>
    <c:forEach var="p" items="${kinds}">
        <tr>
            <td>${p.id}</td>
            <td>${p.name}</td>
            <td><a href="${pageContext.request.contextPath}/kind/edit/${p.id}">EDIT</a>
            <a href="${pageContext.request.contextPath}/kind/delete/${p.id}">DELETE</a></td>
        </tr>
    </c:forEach>
</table>
<hr>
<c:set var="petId" value=""/>
<c:if test="${not empty selectedPetId}">
    <c:set var="petId" value="/edit/${selectedPetId}"/>
</c:if>
<a href="${pageContext.request.contextPath}/pets${petId}">Back to Pets</a>
<hr>
<s:message code="msg.hello"/><br>
<a href="${pageContext.request.contextPath}">welcome</a><br>
<a href="?lang=ru_RU">Russian</a>
<a href="?lang=en">English</a>
</body>
</html>
