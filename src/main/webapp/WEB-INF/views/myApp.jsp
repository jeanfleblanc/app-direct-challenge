<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
	<head>
		<title><fmt:message key="label.welcome"/></title>
	</head>	
	<body>
		<h1 id="welcome"><fmt:message key="label.welcome"/></h1>
			
		<p><fmt:message key="label.lessons"/></p>
		
			<ul>
				<li>
					<fmt:message key="label.lesson.1"/>
				</li>
				<li>
					<fmt:message key="label.lesson.2"/>
				</li>
				<li>
					<fmt:message key="label.lesson.3"/>
				</li>			
			</ul>
			
			<fmt:message key="label.susbscripiton"/> ${requestScope.subscription}
	</body>
</html>