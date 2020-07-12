<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Cov19 Record</title>
</head>
<body>
	<h1>Create Cov19 Record</h1>
	<form action="cov19create" method="post">
		<p>
			<label for="date">Date (yyyy-mm-dd)</label>
			<input id="date" name="date" value="">
		</p>
		<p>
			<label for="caseType">Case Type</label>
			<input id="caseType" name="caseType" value="">
		</p>
		<p>
			<label for="count">Count</label>
			<input id="count" name="count" value="">
		</p>
		<p>
			<label for="countyID">County ID</label>
			<input id="countyID" name="countyID" value="">
		</p>
		<p>
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
</body>
</html>