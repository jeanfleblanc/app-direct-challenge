<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
	<head>
		<title><fmt:message key="label.welcome"/></title>
	</head>	
	<body>
		<h1 id="welcome"><fmt:message key="label.welcome"/></h1>
			
		<p><fmt:message key="label.lessons" /></p>
		
		<fmt:message key="label.buy">
  			<fmt:param value="<a href='https://www.appdirect.com/apps/8197'>"/>
  			<fmt:param value="</a>"/>
  			<fmt:param value="<a href='login' />"/>
  			<fmt:param value="</a>"/>
		</fmt:message>
	</body>
</html>