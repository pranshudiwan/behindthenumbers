<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete a User</title>
</head>
<body>
	<h1>${messages.title}</h1>
	<form action="cov19delete" method="post">
		<div>
			<label for="recordID">Record ID</label> <input id="recordID"
				name="recordID" value="${fn:escapeXml(param.recordID)}">
		</div>
		<input type="submit">
	</form>
</body>
</html>