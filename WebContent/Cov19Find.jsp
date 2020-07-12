<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CRUD On Covid19 Related Info</title>
</head>
<body>
	<h1>Create</h1>
	<a href="cov19create">Create Cov19 Record</a>

	<h1>Find</h1>
	<form action="cov19find" method="post">
		<h2>Search for Cov19 records by county ID</h2>
		<p>
			<label for="countyID">County ID</label> <input id="countyID"
				name="countyID" value="${fn:escapeXml(param.countyID)}">
		</p>
		<p>
			<input type="submit"> <br /> <br /> <br /> <span
				id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>

	<h2>Matching Cov19 Records</h2>
	<table border="1">
		<tr>
			<th>Record ID</th>
			<th>Date</th>
			<th>Case Type</th>
			<th>Count</th>
			<th>County ID</th>
		</tr>
		<c:forEach items="${cov19List}" var="cov19">
			<tr>
				<td><c:out value="${cov19.getRecordID()}" /></td>
				<td><fmt:formatDate value="${cov19.getDate()}"
						pattern="yyyy-MM-dd" /></td>
				<td><c:out value="${cov19.getCaseType()}" /></td>
				<td><c:out value="${cov19.getCount()}" /></td>
				<td><c:out value="${cov19.getCountyID()}" /></td>
			</tr>
		</c:forEach>
	</table>

	<h1>Update</h1>
	<a href="cov19update">Update Cov19 Record</a>

	<h1>Delete</h1>
	<a href="cov19delete">Delete Cov19 Record</a>
</body>
</html>
