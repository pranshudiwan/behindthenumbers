<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find an AirQuality Record</title>
</head>
<body>
	
	<h1>Read: Find AirQuality records that exceed a particular maxAQI value</h1>
	<form action="airqualityread" method="post">
		<p>
			<label for="maxAqi">AQI Threshold</label> <input id="maxAqi"
				name="maxAqi" value="${fn:escapeXml(param.maxAqi)}">
		</p>
		<p>
			<input type="submit"> <br />
			<br />
			<br /> <span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	
	<h2>Matching AirQuality records</h2>
	<table border="1">
		<tr>
			<th>RecordID</th>
			<th>Year</th>
			<th>DayType</th>
			<th>Count</th>
			<th>DaysInYearWithAQI</th>
			<th>MaxAQI</th>
			<th>CountyID</th>
		</tr>
		<c:forEach items="${airQualitiesList}" var="airQuality">
			<tr>
				<td><c:out value="${airQuality.getRecordId()}" /></td>
				<td><c:out value="${airQuality.getYear()}" /></td>
				<td><c:out value="${airQuality.getDayType().name()}" /></td>
				<td><c:out value="${airQuality.getCount()}" /></td>
				<td><c:out value="${airQuality.getDaysInYearWithAqi()}" /></td>
				<td><c:out value="${airQuality.getMaxAqi()}" /></td>
				<td><c:out value="${airQuality.getCountyId()}" /></td>
			</tr>
		</c:forEach>
	</table>
	
	<h1>Create</h1>
	<a href="airqualitycreate">Create AirQuality Record</a>
	
	<h1>Update</h1>
	<a href="airqualityupdate">Update AirQuality Record</a>

	<h1>Delete</h1>
	<a href="airqualitydelete">Delete AirQuality Record</a>
</body>
</html>