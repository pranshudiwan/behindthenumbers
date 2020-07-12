<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update a Poverty Record</title>
</head>
<body>
	<h1>Update Poverty Record</h1>
	<form action="povertyupdate" method="post">
		<p>
			<label for="RecordID">RecordID</label>
			<input id="RecordID" name="RecordID" value="${fn:escapeXml(param.RecordID)}">
		</p>
		<p>
			<label for="PovertyPopulation">PovertyPopulation</label>
			<input id="PovertyPopulation" name="PovertyPopulation" value="">
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