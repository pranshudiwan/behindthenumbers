<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update AirQuality Record</title>
</head>
<body>
	<h1>Update AirQuality Record</h1>
	<form action="airqualityupdate" method="post">
		<p>
			<label for="recordId">RecordID</label>
			<input id="recordId" name="recordId" value="${fn:escapeXml(param.recordId)}">
		</p>
		<p>
			<label for="daysWithAqi">New Days With AQI Value</label>
			<input id="daysWithAqi" name="daysWithAqi" value="${fn:escapeXml(param.daysWithAqi)}">
		</p>
		<p>
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
	
	<h1>Create</h1>
	<a href="airqualitycreate">Create AirQuality Record</a>
	
	<h1>Read</h1>
	<a href="airqualityread">Read AirQuality Record</a>

	<h1>Delete</h1>
	<a href="airqualitydelete">Delete AirQuality Record</a>
</body>
</html>