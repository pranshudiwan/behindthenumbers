<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a Poverty Record</title>
</head>
<body>
	
	<h1>Read: Find Poverty records for a given countyID for all age groups</h1>
	<form action="povertyread" method="post">
		<p>
			<label for="CountyID">CountyID</label> 
			<input id="CountyID" name="CountyID" value="${fn:escapeXml(param.CountyID)}">
		</p>
		<p>
			<input type="submit"> 
			<br /><br /><br /> 
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	
	<h2>Matching Poverty records</h2>
	<table border="1">
		<tr>
			<th>RecordID</th>
			<th>Year</th>
			<th>PovertyPopulation</th>
			<th>PercentPovertyPopulation</th>
			<th>AgeGroupID</th>
			<th>CountyID</th>
		</tr>
		<c:forEach items="${povList}" var="poverty">
			<tr>
				<td><c:out value="${poverty.getRecordID()}" /></td>
				<td><c:out value="${poverty.getYear()}" /></td>
				<td><c:out value="${poverty.getPovertyPopulation()}" /></td>
				<td><c:out value="${poverty.getPercentPovertyPopulation()}" /></td>
				<td><c:out value="${poverty.getAgeGroupID()}" /></td>
				<td><c:out value="${poverty.getCountyID()}" /></td>
			</tr>
		</c:forEach>
	</table>
	<h1>Create</h1>
	<a href="povertycreate">Create Poverty Record</a>
	
	<h1>Update</h1>
	<a href="povertyupdate">Update Poverty Record</a>

	<h1>Delete</h1>
	<a href="povertydelete">Delete Poverty Record</a>
</body>
</html>
