<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a County</title>
</head>
<body>
	<form action="countyread" method="post">
		<h1>Search for a County by Countyname</h1>
		<p>
			<label for="CountyName">Countyname</label>
			<input id="CountyName" name="CountyName" value="${fn:escapeXml(param.CountyName)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<h1>Matching County</h1>
        <table border="1">
            <tr>
                <th>CountyName</th>
                <th>CountyFips</th>
                <th>StateID</th>
            </tr>
            <c:forEach items="${county}" var="county" >
                <tr>
                    <td><c:out value="${county.getCountyName()}" /></td>
                    <td><c:out value="${county.getCountyFips()}" /></td>
                    <td><c:out value="${county.getStateID()}" /></td>
                </tr>
            </c:forEach>
       </table>
       
    <h1>Find</h1>
	<a href="countycreate">Create County Record</a>
	
	<h1>Delete</h1>
	<a href="countydelete">Delete County Record</a>
	
	<h1>Update</h1>
	<a href="countyupdate">Update County Record</a>
	
</body>
</html>
