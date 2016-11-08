<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<html>
<style>
    .red { color: red }
</style>
<body>
<h2>Kind:</h2>

<table border="0">
    <f:form commandName="kind" action="${pageContext.request.contextPath}/kind" method="post">
    <!-- f:form commandName="kind" method="post" -->
        <c:if test="${edit}">
            <tr>
                <td><f:label path="id">ID:</f:label></td>
                <td><f:input path="id" readonly="true"/></td>
            </tr>
        </c:if>

        <tr>
            <td><f:label path="name">NAME:</f:label></td>
            <td><f:input path="name"/></td>
            <td>
                <span class="red">
                    <f:errors path="name"/>
                </span>
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
