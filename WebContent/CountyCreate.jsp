<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a County</title>
</head>
<body>
	<h1>Create County</h1>
	<form action="countycreate" method="post">
		<p>
			<label for="CountyName">CountyName</label>
			<input id="CountyName" name="CountyName" value="">
		</p>
		<p>
			<label for="CountyFips">CountyFips</label>
			<input id="CountyFips" name="CountyFips" value="">
		</p>
		<p>
			<label for="StateID">StateID</label>
			<input id="StateID" name="StateID" value="">
		</p>
		<p>
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
	
	<h1>Find</h1>
	<a href="countyread">Find County Record</a>
	
	<h1>Delete</h1>
	<a href="countydelete">Delete County Record</a>
	
	<h1>Update</h1>
	<a href="countyupdate">Update County Record</a>
	
</body>
</html>