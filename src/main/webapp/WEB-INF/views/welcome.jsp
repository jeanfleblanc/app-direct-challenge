<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
	<head>
		<title><fmt:message key="label.welcome"/></title>

		<!--
			Used for including CSRF token in JSON requests
			Also see bottom of this file for adding CSRF token to JQuery AJAX requests
		-->
		<meta name="_csrf" content="${_csrf.token}"/>
		<meta name="_csrf_header" content="${_csrf.headerName}"/>
	</head>	
	<body>
		<fmt:message key="label.welcome"/>
		<script type="text/javascript" src="<c:url value="/resources/jquery/jquery-2.1.0.min.js" />"></script>
	</body>
</html>