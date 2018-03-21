
function addContact() {
	
	location.href = "add";
	
}

function saveContact() {
	
	//location.href = "save";
	
	var saveUrl = context+'/app/contact/save';
	var formData = $("#contactForm").serialize();
	
	alert(formData);
	alert(saveUrl);
	
	$.ajax({
		url: saveUrl,
		data: formData,
		type: 'POST',
		success: function(data) {
			
			alert(data);
			
		},
		error: function(xhr, ajaxOptions, thrownError){
			
			alert("error");
			
		}
	});
	
	
	
}

function editContact(contactId) {
	
	location.href = "edit/"+contactId;
	
}

function getContact(contactId) {
	
	location.href = contactId;
	
}

function deleteContact(contactId) {
	
	location.href = 'delete/'+contactId+'?confirm=yes';
	
}




