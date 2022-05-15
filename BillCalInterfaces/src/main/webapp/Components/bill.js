$(document).ready(function(){
	$("#alertSuccess").hide();
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
	var status = validateBillForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	// If valid------------------------
	var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";
	$.ajax(
		{
			url: "BillCallAPI",
			type: type,
			data: $("#formBill").serialize(),
			dataType: "text",
			complete: function(response, status) {
				onBillSaveComplete(response.responseText, status);
			}
		});
});

function onBillSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") 
		{
			
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
			
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
	$("#hidItemIDSave").val("");
	$("#formItem")[0].reset();
}



// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) 
{ 
$("#hidItemIDSave").val($(this).data("id")); 
 $("#accNumber").val($(this).closest("tr").find('td:eq(0)').text()); 
 $("#name").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#units").val($(this).closest("tr").find('td:eq(2)').text()); 
 $("#date").val($(this).closest("tr").find('td:eq(3)').text()); 
});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	// Form validation-------------------
	var status = validateBillForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	// If valid------------------------
	var typ = ($("#hidItemIDSave").val() == "") ? "" : "PUT";
	var ide = 2;
	$.ajax(
		{
			url: "BillCallAPI",
			type: typ,
			headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            data: `accNumber=+${encodeURIComponent(accNumber)}`+`&name=+${encodeURIComponent(name)}`+`&units=+${encodeURIComponent(units)}`+`&date=+${encodeURIComponent(date)}`+`&id=+${encodeURIComponent(ide)}`,
			data: $("#formBill").serialize(),
			dataType: "text",
			complete: function(response, status) {
				onBillSaveComplete(response.responseText, status);
			}
		});
});




$(document).on("click", ".btnRemove", function(event) {
	
	var ids = 1;
	$.ajax(
		{
			url: "BillCallAPI",
			type: "DELETE",
			headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
			data: `id=+${encodeURIComponent(ids)}`,
			//data: "id=" + $(this).data("id"),
			//dataType: "text",
			complete: function(response, status) {
				onItemDeleteComplete(response.responseText, status);
			}
		});
});

function onItemDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
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
// CLIENT-MODEL================================================================
function validateBillForm() {
	// CODE
	if ($("#accNumber").val().trim() == "") {
		return "Insert Acc Number.";
	}
	// NAME
	if ($("#name").val().trim() == "") {
		return "Insert Name.";
	}
	// Units
	if ($("#units").val().trim() == "") {
		return "Insert Units.";
	}
	
	// DATE-------------------------------
	if ($("#date").val().trim() == "") {
		return "Insert date.";
	}
	// convert to decimal price
//	$("#itemPrice").val(parseFloat(tmpPrice).toFixed(2));
	
	return true;
}
