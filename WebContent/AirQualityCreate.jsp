<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create an AirQuality Record</title>
</head>
<body>
	<h1>Create AirQuality Record</h1>
	<form action="airqualitycreate" method="post">
		<p>
			<label for="RecordID">RecordID</label>
			<input id="RecordID" name="RecordID" value="">
		</p>
		<p>
			<label for="Year">Year</label>
			<input id="Year" name="Year" value="">
		</p>
		<p>
			<label for="DayType">DayType</label>
			<input id="DayType" name="DayType" value="">
		</p>
		<p>
			<label for="Count">"Count"</label>
			<input id="Count" name="Count" value="">
		</p>		
		<p>
			<label for="DaysInYearWithAQI">"DaysInYearWithAQI"</label>
			<input id="DaysInYearWithAQI" name="DaysInYearWithAQI" value="">
		</p>			
		<p>
			<label for="MaxAQI">"MaxAQI"</label>
			<input id="MaxAQI" name="MaxAQI" value="">
		</p>			
		<p>
			<label for="CountyID">"CountyID"</label>
			<input id="CountyID" name="CountyID" value="">
		</p>
		<p>
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
	
	<h1>Read</h1>
	<a href="airqualityread">Read AirQuality Record</a>
	
	<h1>Update</h1>
	<a href="airqualityupdate">Update AirQuality Record</a>

	<h1>Delete</h1>
	<a href="airqualitydelete">Delete AirQuality Record</a>
</body>
</html>