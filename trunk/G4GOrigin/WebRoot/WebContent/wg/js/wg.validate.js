/////jquery validation starts here

//$.validator.setDefaults({
//	submitHandler: function() { 
//		$('
//		alert("submitted!");
//		 
//	}			
//});

$().ready(function() {
	//console.log("jquery validator loaded");
	
	
	
	// validate signup form on keyup and submit
	$("#mainForm").validate({
		rules: {
			
			passwordOne: {
				required: true,
				minLength: 5
			},
			passwordTwo: {
				required: true,
				minLength: 5,
				equalTo: "#passwordOne"
			},
			screenName: {
				required: true
				//nowhitespace: true
			},
			emailOne: {
				required: true,
				email: true
			},
			emailTwo: {
				required: true,
				email: true,
				equalTo: "#emailOne"
			},
			consoleCheckbox: {
				required:true, 
				minLength:1
			},
			profileHeadline: {
				required: true,
				maxLength: 100
			},
			aboutMe: {
				required: false,
				maxLength: 100
			},
			availability: {
				required: false,
				maxLength: 100
			},
			firstName: {
				required: true,
				maxLength:18
			},
			middleName: {
				required: false,
				maxLength: 3
			},
			lastName: { 
				required: true,
				maxLength: 18
			},
			
			birthDate: {
				date: true,
				date18: true 
			},
			nationCodes: {
				required: true,
				notWONoSelectionString: true
			},
			street1: {
				required: true
			},
			cityField: {
				required: true
			},
			billingZip: {
				required: true
			},
			gender: {
				required: true
			},
			MoSel: {
				required: true	
			},
			DySel: {
				required: true
			},
			YrSel: {
				required: true
			}
		},
		messages: {
			passwordOne: {
				required: "Please provide a password",
				minLength: "Your password must be at least 5 characters long"
			},
			passwordTwo: {
				required: "Please provide a password",
				minLength: "Your password must be at least 5 characters long",
				equalTo: "Please enter the same password as above"
			},
			emailOne: {
				required: "Please provide an email address"
			},
			emailTwo: {
				required: "Please provide an email address",
				equalTo: "Please enter the same email as above"
			},
			nationCodes: {
				notWONoSelectionString: "Please select a country"
			},
			gender: {
				required: "Gender is required"
			},
			MoSel: {
				required: "Month is required"
			},
			DySel: {
				required: "Day is required"
			},
			YrSel: {
				required: "Year is required"
			},
			consoleCheckbox: {
				required: "You must select at least one gaming console"
			}
		}
	});
	

	
	
	// validate signup form on keyup and submit
	$("#reputationForm").validate({
		rules: {
			
			comment: {
				required: true,
				maxLength: 100
			}
		}
	});

	
	/*
	//code to hide topic selection, disable for demo
	var newsletter = $("#newsletter");
	// newsletter topics are optional, hide at first
	var topics = $("#newsletter_topics")[newsletter.is(":checked") ? "show" : "hide"]();
	// show when newsletter is checked
	newsletter.change(function() {
		topics[this.checked ? "show" : "hide"]();
	});
	*/
});
