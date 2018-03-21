<%@ include file="../common/includes.jsp"%>
<!DOCTYPE html>

<html>
<head>

<title> All Contact </title>
<%@ include file="../common/jsincludes.jsp"%>

</head>

<body>

<table border="1">
	
	<tr>
		<td>Contact Id</td>
		<td>Name</td>
		<td>Mobile</td>
		<td> </td>
		<td></td>
	</tr>
	
	<c:forEach var="contact" items="${contactVOs}">
		<tr>
			<td><a href="#" onclick="getContact(${contact.contactId});">${contact.contactId}</a></td>
			
			<td>${contact.contactName}</td>
			<td>${contact.contactMobile}</td>
			
			<td><a href="#" onclick="editContact(${contact.contactId});">Edit</a></td>
			<td><a href="#" onclick="deleteContact(${contact.contactId});">Delete</a></td>
			
		</tr>
	</c:forEach>
	
	<c:if test="${empty contactVOs}">
		<tr>
			<td colspan="3"> No Contacts Found </td>
		</tr>
	</c:if>
	
	<tr>
		<td> </td>
		<td> </td>
		<td align="right"><input type="button" value="Add" onclick="addContact();"></td>
	</tr>
	
</table>

</body>
</html>