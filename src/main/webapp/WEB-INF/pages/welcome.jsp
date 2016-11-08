<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<html>
<body>
<h1>Spring MVC + Log4j integration example</h1>

<h2>${msg}</h2>
It is: ${time}
<br>
<s:message code="label.hello"/>

<a href="${pageContext.request.contextPath}/main">abc/index</a><br>
<a href="?lang=ru_RU">Русский</a>
<a href="?lang=en">English</a>
</body>
</html>