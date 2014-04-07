<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>List Information</title>
    
		<!--
			Used for including CSRF token in JSON requests
			Also see bottom of this file for adding CSRF token to JQuery AJAX requests
		-->
		<meta name="_csrf" content="${_csrf.token}"/>
		<meta name="_csrf_header" content="${_csrf.headerName}"/> 
</head>

<body>
	<p>User: ${user.username}</p>
</body>
</html>
	