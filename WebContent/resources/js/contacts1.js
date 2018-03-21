
function addContactAjax() {
	
	var url = context+'/app/contact1/add';
	
	ajaxGet(url);
	
}

function saveContactAjax() {
	
	
	var url = context+'/app/contact1/save';
	var data = $("#contactForm").serialize();
	
	ajaxPost(url, data);
	
}

function editContactAjax(contactId) {
	
	var url = context+'/app/contact1/edit/'+contactId;
		
	ajaxGet(url);
	
	
}

function getContactAjax(contactId) {
	
	var url = context+'/app/contact1/'+contactId;
	
	ajaxGet(url);
	
}

function deleteContactAjax(contactId) {
	
	
	var url = context+'/app/contact1/delete/'+contactId+'?confirm=yes';
	
	ajaxGet(url);
	
}



function ajaxGet(url,data,successfn, errorfn) {
	
	ajaxCall(url, 'GET',data, successfn, errorfn);
	
}

function ajaxPost(url,data,successfn, errorfn) {
	
	ajaxCall(url, 'POST', data, successfn, errorfn);
	
}


function ajaxCall(url,type,data,successfn, errorfn) {
	
	
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
			
			$("#containerDiv").html(data);
			
		};
	}
	
	
	
	$.ajax({
		url: url,
		type: type,
		data: data,
		success: successfn,
		error: errorfn
	});
	
}



