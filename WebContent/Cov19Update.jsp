<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Cov19 Record</title>
</head>
<body>
	<h1>Update Cov19 Record</h1>
	<form action="cov19update" method="post">
		<div>
			<label for="recordID">Record ID</label> <input id="recordID"
				name="recordID" value="${fn:escapeXml(param.recordID)}">
		</div>
		<div>
			<label for="count">New Count</label> <input id="count" name="count"
				value="${fn:escapeXml(param.count)}">
		</div>
		<input type="submit">
	</form>
	<span id="successMessage"><b>${messages.success}</b></span>
</body>
</html>