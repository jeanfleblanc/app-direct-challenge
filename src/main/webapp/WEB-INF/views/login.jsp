<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>OpenID Login</title>
    
		<!--
			Used for including CSRF token in JSON requests
			Also see bottom of this file for adding CSRF token to JQuery AJAX requests
		-->
		<meta name="_csrf" content="${_csrf.token}"/>
		<meta name="_csrf_header" content="${_csrf.headerName}"/> 
</head>

<body>

<c:if test="${not empty param.login_error}">
  <font color="red">
    Your login attempt was not successful, try again.<br/><br/>
    Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
  </font>
</c:if>

<!-- Simple OpenID Selector -->
<form action="<c:url value='j_spring_openid_security_check'/>" method="post" id="openid_form">

    <input type="hidden" name="action" value="verify" />

    <fieldset>
            <legend>Sign-in or Create New Account</legend>

            <div id="openid_input_area">
                <input type="hidden" id="openid_identifier" name="openid_identifier" type="text" value="https://www.appdirect.com/openid/id" />
                <input id="openid_submit" type="submit" value="Sign-In"/>
            </div>
            <noscript>
            <p>OpenID is a service that allows you to log-on to many different websites using a single identity.
            Find out <a href="http://openid.net/what/">more about OpenID</a> and <a href="http://openid.net/get/">how to get an OpenID enabled account</a>.</p>
            </noscript>
    </fieldset>
</form>
<!-- /Simple OpenID Selector -->

</body>
</html>