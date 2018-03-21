<%@ include file="../common/includes.jsp"%>
<%@ include file="../common/jsincludes.jsp"%>
<!DOCTYPE html>

<html>
<head>
<title> Edit Contact </title>
</head>

<body>

<form name="contactForm" id="contactForm" action="save" method="post">

<table border="0">
	
	<tr><td>Contact Id</td> <td><input type="text" name="contactId" id="contactId" readonly="readonly" value="${contactVOKey.contactId}" /></td></tr>
	<tr><td>Contact Name</td> <td><input type="text" name="contactName" id="contactName" value="${contactVOKey.contactName}" /></td></tr>
	<tr><td>Contact Mobile</td> <td><input type="text" name="contactMobile" id="contactMobile" value="${contactVOKey.contactMobile}" /></td></tr>
	
	<tr>
		<td> </td>
		<td><input type="submit" value="Save"></td>
	</tr>
	
</table>

</form>

</body>
</html>