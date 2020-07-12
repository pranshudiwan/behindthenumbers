<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete a County</title>
</head>
<body>
	<h1>${messages.title}</h1>
	<form action="countydelete" method="post">
		<p>
			<div <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
				<label for="CountyName">CountyName</label>
				<input id="CountyName" name="CountyName" value="${fn:escapeXml(param.CountyName)}">
			</div>
	    </p>
		<p>
			<span id="submitButton" <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
			<input type="submit">
			</span>
		</p>
	</form>
	<br/><br/>
	
	<h1>Find</h1>
	<a href="countyread">Find County Record</a>
	
	<h1>Create</h1>
	<a href="countycreate">Create County Record</a>
	
	<h1>Update</h1>
	<a href="countyupdate">Update County Record</a>
	
</body>
</html>