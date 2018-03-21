
function getAllContacts2() {
	
	var url = context+'/app/contact2/all';
	
	
	ajaxGet2(url,null,getAllContacts2SuccessFn);
	
}




function getAllContacts2SuccessFn(data) {
	
		//var jsonToString = JSON.stringify(data);
		//alert(jsonToString);  //just to see json value
		
		
		var table1 = '<table id="containerDivTable" border="1">'+
						'<tr> <td>Contact Id</td> <td>Name</td>  <td>Mobile</td> <td> </td>	<td></td> </tr>'+
					'</table>';
		$("#containerDiv").html(table1);
		
		
		$.each(data, function(i, jsonObj) {
			
			var tableRow ='<tr>'+
							'<td><a href="#" onclick="getContactAjax2('+jsonObj.contactId+');">'+jsonObj.contactId+'</a></td>'+
							'<td>'+jsonObj.contactName+'</td>'+
							'<td>'+jsonObj.contactMobile+'</td>'+
							'<td><a href="#" onclick="editContactAjax2('+jsonObj.contactId+');">Edit</a></td>'+
							'<td><a href="#" onclick="deleteContactAjax2('+jsonObj.contactId+');">Delete</a></td>'+
						   '</tr>';
			
			$("#containerDivTable").append(tableRow);
			
		});
		
		
		var addButton ='<tr>'+
						'<td> </td>'+
						'<td> </td>'+
						'<td align="right"><input type="button" value="Add" onclick="addContactAjax2();"></td>'+
						'</tr>';
		
		$("#containerDivTable").append(addButton);

	
}


function addContactAjax2() {
	
	var url = context+'/app/contact2/add';
	
	ajaxGet2(url);
	
}

function saveContactAjax2() {
	
	
	var url = context+'/app/contact2/save';
	
	var formData = $("#contactForm").serializeObject();
	var formDataString = JSON.stringify(formData);
	//console.log(formData);
	
	
	$.ajax({
		
		  url:url,
		  type:"POST",
		  data: formDataString,
		  contentType:"application/json",
		  dataType:"json",
		  success: function(data){
			  getAllContacts2SuccessFn(data);
		  },
		  error:function(xhr, ajaxOptions, thrownError){
				alert("ERROR: Unable to proceed your AJAX request"+thrownError);
		  }
		  
		});

}

function editContactAjax2(contactId) {
	
	var url = context+'/app/contact2/edit/'+contactId;
		
	ajaxCall2(url, 'GET',null, function(data) {
			
			//alert(data); //object [object]
			
			var table1 = 
			'<form name="contactForm" id="contactForm" action="save" method="post">'+
			'<table border="0">'+
				'<tr><td>Contact Id</td> <td><input type="text" name="contactId" id="contactId" readonly="readonly" value="'+data.contactId+'" /></td></tr>'+
				'<tr><td>Contact Name</td> <td><input type="text" name="contactName" id="contactName" value="'+data.contactName+'" /></td></tr>'+
				'<tr><td>Contact Mobile</td> <td><input type="text" name="contactMobile" id="contactMobile" value="'+data.contactMobile+'" /></td></tr>'+
				'<tr><td> </td> <td><input type="button" value="Save" onclick="saveContactAjax2();"></td> </tr>'+
			'</table>'+
			'</form>';
							
			$("#containerDiv").html(table1);
	
			
		});
	
	
}

function getContactAjax2(contactId) {
	
	var url = context+'/app/contact2/'+contactId;
	
	ajaxCall2(url, 'GET',null, function(data) {
		
		//alert(data); //object [object]
		
		//JSON.stringify converts [json object] into [json string] 
		//var jsonToString = JSON.stringify(data);
		//console.log(jsonToString);  //just to see json value
		
		//$.parseJSON converts [json string] into [json object] 
		//var jsonObj = $.parseJSON(jsonToString);
		
		
		var table1 = '<table id="containerDivTable" border="1">'+
							'<tr><td>Contact Id</td> <td>'+data.contactId+'</td></tr>'+
							'<tr><td>Contact Name</td> <td>'+data.contactName+'</td></tr>'+
							'<tr><td>Contact Mobile</td> <td>'+data.contactMobile+'</td></tr>'+
						'</table>';
						
		$("#containerDiv").html(table1);

		
	});
	
	
	
}

function deleteContactAjax2(contactId) {
	
	
	var url = context+'/app/contact2/delete/'+contactId+'?confirm=yes';
	

	var successfn = function(data) {
				
				getAllContacts2SuccessFn(data);
	
	};
	
	
	ajaxGet2(url,null,successfn);
	
}



function ajaxGet2(url,data,successfn, errorfn) {
	
	ajaxCall2(url, 'GET',data, successfn, errorfn);
	
}

function ajaxPost2(url,data,successfn, errorfn) {
	
	ajaxCall2(url, 'POST', data, successfn, errorfn);
	
}


function ajaxCall2(url,type,data,successfn, errorfn) {
	
	if(errorfn == undefined){
		errorfn = function(xhr, ajaxOptions, thrownError){
						alert("ERROR: Unable to proceed your AJAX request"+thrownError);
				  };
	}
	
	if(successfn == undefined){
		successfn = function(data){
			
			//var particularElement = $(data).filter("#containerDiv").html();
			
			//~ or ~
			//$responseData = $(data);
			//var particularElement = $responseData.filter("#containerDiv").html();
			
			
			$("#containerDivTable").append(data);
			
		};
	}
	
	
	
	$.ajax({
		url: url,
		type: type,
		data: data,
		dataType: 'json',
		success: successfn,
		error: errorfn
	});
	
}



