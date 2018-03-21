<%@ include file="../common/includes.jsp"%>
<!DOCTYPE html>

<html>
<head>

<title> Add Contact </title>
<%@ include file="../common/jsincludes.jsp"%>

</head>

<body>

<form name="contactForm" id="contactForm" action="save" method="post">

	<table border="0">
		
		<tr><td>Contact Name</td> <td><input type="text" name="contactName" id="contactName" /></td></tr>
		<tr><td>Contact Mobile</td> <td><input type="text" name="contactMobile" id="contactMobile" /></td></tr>
		
		<tr>
			<td> </td>
			<td><input type="submit" value="Save" ></td>
		</tr>
		
	</table>

</form>



</body>
</html>