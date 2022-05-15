$(document).ready(function() {
	
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {
	

	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	// Form validation-------------------
	var status = validateUserForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	// If valid------------------------
	var type = ($("#hidConnReqIdSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "UserServlet",
		type : type,
		data : $("#userForm").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onUserSaveComplete(response.responseText, status);
		}
	});
});

function onUserSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divPaymentGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidConnReqIdSave").val("");
	$("#userForm")[0].reset();
}
// UPDATE==========================================
$(document).on(
		"click",
		".btnUpdate",
		function(event) {
			$("#hidConnReqIdSave").val(
					$(this).closest("tr").find('#hidConnReqIdUpdate').val());
			$("#name").val($(this).closest("tr").find('td:eq(0)').text());
			$("#nic").val($(this).closest("tr").find('td:eq(1)').text());
			$("#address").val($(this).closest("tr").find('td:eq(2)').text());
			$("#zipcode").val($(this).closest("tr").find('td:eq(3)').text());
			$("#contactnumber").val($(this).closest("tr").find('td:eq(4)').text());
			$("#email").val($(this).closest("tr").find('td:eq(5)').text());
		});

// REMOVE ====================================================

$(document).on("click", ".btnRemove", function(event) {
	
	$.ajax({
		url : "UserServlet",
		type : "DELETE",
		data : "connReqId=" + $(this).data("itemid"),
		dataType : "text",
		complete : function(response, status) {
			onUserDeleteComplete(response.responseText, status);
		}
	});
});
function onUserDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divPaymentGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

// CLIENTMODEL=========================================================================
function validateUserForm() {
	// Name
	if ($("#name").val().trim() == "") {
		return "Insert Name";
	}
	// NIC
	if ($("#nic").val().trim() == "") {
		return "Insert NIC ";
	}
	// Address
	if ($("#address").val().trim() == "") {
		return "Insert address";
	}
	// zip code
	if ($("#zipcode").val().trim() == "") {
		return "Insert zipcode";
	}
	
	// Contact Number
	if ($("#contactnumber").val().trim() == "") {
		return "Insert telephone number";
	}
	// Email
	if ($("#email").val().trim() == "") {
		return "Insert email";
	}
	
	//is numerical value
	var tmpCharge = $("#contactnumber").val().trim();
	if (!$.isNumeric(tmpCharge)) {
		return "Insert a valid phone number";
	}
	
	//valid phone number
	var txtLength = $("#contactnumber").val().length;
    if (txtLength != 10) {
        return "Insert a valid phone number";
    }
    
    //valid NIC number
	//var txtLength = $("#nic").val().length;
    //if (txtLength != 10) {
//        return "Insert a valid NIC number";
  //  }	
    
    
	
	return true;

}
