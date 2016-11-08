<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<html>
<body>
<h2>Hello World!</h2>
It is: ${time}<br>
${msg}

<br>
<a href="${pageContext.request.contextPath}/pets">Pets</a>
<a href="${pageContext.request.contextPath}/kind/all">Kinds</a>
<hr>
<s:message code="label.hello"/><br>
<a href="${pageContext.request.contextPath}">welcome</a><br>
<a href="?lang=ru_RU">Russian</a>
<a href="?lang=en">English</a>
</body>
</html>
