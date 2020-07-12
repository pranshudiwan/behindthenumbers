<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete an AirQuality Record</title>
</head>
<body>
	<h1>${messages.title}</h1>
	<form action="airqualitydelete" method="post">
		<p>
			<div <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
				<label for="RecordID">RecordID</label>
				<input id="RecordID" name="RecordID" value="${fn:escapeXml(param.RecordID)}">
			</div>
		</p>
		<p>
			<span id="submitButton" <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
			<input type="submit">
			</span>
		</p>
	</form>
	<br/><br/>
	
	<h1>Create</h1>
	<a href="airqualitycreate">Create AirQuality Record</a>
	
	<h1>Read</h1>
	<a href="airqualityread">Read AirQuality Record</a>
	
	<h1>Update</h1>
	<a href="airqualityupdate">Update AirQuality Record</a>

</body>
</html>