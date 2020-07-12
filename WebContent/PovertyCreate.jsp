<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create Poverty record</title>
</head>
<body>
	<h1>Create Poverty record</h1>
	<form action="povertycreate" method="post">
		
		<p>
			<label for="Year">Year</label>
			<input id="Year" name="Year" value="">
		</p>
		<p>
			<label for="PovertyPopulation">PovertyPopulation</label>
			<input id="PovertyPopulation" name="PovertyPopulation" value="">
		</p>
		<p>
			<label for="PercentPovertyPopulation">PercentPovertyPopulation</label>
			<input id="PercentPovertyPopulation" name="PercentPovertyPopulation" value="">
		</p>
		<p>
			<label for="AgeGroupID">AgeGroupID</label>
			<input id="AgeGroupID" name="AgeGroupID" value="">
		</p>
		<p>
			<label for="CountyID">CountyID</label>
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
</body>
</html>