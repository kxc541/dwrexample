//
//	FormValid.js - form validation object
//
var ajaxPath = "ajax/";
var FormValid = {
	//
	//	constants
	//
	ERROR_COLOR:			"#dd4422",
	NO_ERROR_COLOR:			"#000",
	CHECK_ALL_TIMEOUT:		5000,
	MULTI_PART_ID:			"mPart",
	MULTI_PART_INFO_ID:		"mPartInfo",
	MULTI_PART_NAV_ID:		"mPartNav",
	NO_SELECTION:			"WONoSelectionString",
	//
	//	validation constants
	//
	EIGHTEEN_YEARS_IN_MS:	567993600000,
	DIGITS:					"0123456789",
	PHONE_CHARS:			"()- .",
	US_POSTAL_CODE_RE:		/(^\d{5}$)|(^\d{5}-\d{4}$)/,
	CAN_POSTAL_CODE_RE:		/^\D{1}\d{1}\D{1}\-?\d{1}\D{1}\d{1}$/,
	EMAIL_FORMAT_RE:		/^[a-zA-Z][\w\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\w\.-]*[a-zA-Z0-9]\.[a-zA-Z][a-zA-Z\.]*[a-zA-Z]$/,
	DATE_FORMAT_RE:			/^\d{1,2}(\-|\/|\.)\d{1,2}\1\d{4}$/,
	BLANK_SPACE_RE:			/\s*/,
	DEBUG:					false,
	LOG_LEVEL:				2,		// log messages of LOG_LEVEL or lower are shown weven when DEBUG is false.
	//
	//	attributes
	//
	bValid:					true,
	bAutoCheck:				true,
	aParts:					null,
	aPartsVisited:			new Array(0),
	curPart:				0,
	curStep:				0,
	aReview:				null,
	aReviewConditionals:	null,
	aRequired:				new Array(0),
	aValidation:			new Array(0),
	aOnChange:				new Array(),
	aOnBlur:				new Array(),
	hasFocus:				null,
	bCheckAll:				false,
	checkAllCount:			0,
	//
	//	specific attributes
	//
	eMailAddress:			null,
	bLoginAvailable:		false,
	
	
	init:	function() {
		FormValid.log(5, "FormValid.init: starting.");
		//
		// grab the required fields
		//
		if ($("form").get().length > 0 && (FormValid.aParts = $(".MultiPart").get()).length == 0)
			return;
			
		$(".clearDefText").focus(FormValid.onFocus);
		$(".defStateChecked").attr("checked", "true");			
			
		for (var iPart=0; iPart<FormValid.aParts.length; iPart++) {
			FormValid.aRequired[iPart] = $(FormValid.aParts[iPart]).find(".required").get();
			FormValid.aValidation[iPart] = $(FormValid.aParts[iPart]).find(".validation").get();
			FormValid.aPartsVisited[iPart] = false;
			
			for (var i=0; i<FormValid.aRequired[iPart].length; i++) {
				FormValid.log(5, "FormValid.init: required loop: "+FormValid.aRequired[iPart][i].name);
				$("#"+FormValid.MULTI_PART_INFO_ID+iPart).append("<li id='"+FormValid.aRequired[iPart][i].name+"Li'>"+
					(($(FormValid.aRequired[iPart][i]).attr("longName")==undefined)?$(FormValid.aRequired[iPart][i]).attr("defText"):
					$(FormValid.aRequired[iPart][i]).attr("longName"))+"</li>");
			
				if (FormValid.linkBoxState(FormValid.aRequired[iPart][i]))
					FormValid.fieldValid(FormValid.aRequired[iPart][i]);
				else 
					FormValid.fieldNotValid(FormValid.aRequired[iPart][i]);
				
				if ($(FormValid.aRequired[iPart][i]).attr("validation") == undefined) {
					FormValid.log(5, "FormValid.init: no validation attr: "+FormValid.aRequired[iPart][i].name);
					if (FormValid.aRequired[iPart][i].type == "select-one" || FormValid.aRequired[iPart][i].type == "checkbox") {
						$(FormValid.aRequired[iPart][i]).change(FormValid.onChange);
						FormValid.aOnChange[FormValid.aRequired[iPart][i].name] = FormValid.required;
					} else {
						$(FormValid.aRequired[iPart][i]).blur(FormValid.onBlur);
						FormValid.aOnBlur[FormValid.aRequired[iPart][i].name] = FormValid.required;					
					}
					
				} else {
					FormValid.log(5, "FormValid.init: validation attr: "+FormValid.aRequired[iPart][i].name+": "+$(FormValid.aRequired[iPart][i]).attr("validation"));
					if (FormValid.aRequired[iPart][i].type == "select-one" || FormValid.aRequired[iPart][i].type == "checkbox") {
						$(FormValid.aRequired[iPart][i]).change(FormValid.onChange);
						FormValid.aOnChange[FormValid.aRequired[iPart][i].name] = FormValid[$(FormValid.aRequired[iPart][i]).attr("validation")];
					} else {
						$(FormValid.aRequired[iPart][i]).blur(FormValid.onBlur);
						FormValid.aOnBlur[FormValid.aRequired[iPart][i].name] = FormValid[$(FormValid.aRequired[iPart][i]).attr("validation")];					
					}
					
				}
			}

			for (var i=0; i<FormValid.aValidation[iPart].length; i++) {
				FormValid.log(5, "FormValid.init: validation loop: " + 
					FormValid.aValidation[iPart][i].name);
				if (FormValid.aValidation[iPart][i].type == "select-one" || 
					FormValid.aValidation[iPart][i].type == "checkbox") {
					
					$(FormValid.aValidation[iPart][i]).change(FormValid.onChange);
					
					FormValid.aOnChange[FormValid.aValidation[iPart][i].name] = 
						FormValid[$(FormValid.aValidation[iPart][i]).attr("validation")];
				} 
				else {
					$(FormValid.aValidation[iPart][i]).blur(FormValid.onBlur);
					FormValid.aOnBlur[FormValid.aValidation[iPart][i].name] = 
						FormValid[$(FormValid.aValidation[iPart][i]).attr("validation")];
				}
			}

		}
		
		FormValid.aReview = $(".review").get();
		FormValid.aReviewConditionals = $(".reviewConditional").get();

		$(".mPartNav").click(FormValid.navToPart);

		FormValid.showPart(FormValid.curPart);

		FormValid.aPartsVisited[FormValid.curPart] = true;
		FormValid.initDefText();
		FormValid.isValid();
		FormValid.checkAllTimer();
		
//		FormValid.setAllParts();
		
		FormValid.log(5, "FormValid.init: has run: bValid = "+FormValid.bValid);
	},
	
	required:	function(thisObj) {
		var bFieldValid;
		FormValid.log(5, "FormValid.required: "+thisObj.name+": "+thisObj.type);
		
		if (FormValid.checkLinkBox(thisObj))
			return;
		
		switch(thisObj.type) {
			case "text": 
				if (thisObj.value == $(thisObj).attr("defText") || 
					thisObj.value == "" || 
					thisObj.value.match(FormValid.BLANK_SPACE_RE) == null) {
					
					FormValid.log(5, "FormValid.required: "+thisObj.name+": required filed empty.");
					bFieldValid = false;
				} 
				else {
					FormValid.log(5, "FormValid.required: "+thisObj.name+": required field filled in.");
					bFieldValid = true;
				}
			break;
			
			case "select-one":
				if (thisObj.value == "WONoSelectionString") {
					bFieldValid = false;					
				} else {
					bFieldValid = true;				
				}
			break;
			
			case "checkbox":
				if (!thisObj.checked) {
					bFieldValid = false;					
				} else {
					bFieldValid = true;					
				}
			
			break;
			
			default:
			
		}
		
		if (bFieldValid)
			FormValid.fieldValid(thisObj);
		else
			FormValid.fieldNotValid(thisObj);
			
		FormValid.isValid();
	},
	
	//
	//	custom validation methods
	//
	dateSelect:		function(thisObj) {
		FormValid.log(3, "FormValid.dateSelect: "+thisObj.name);
		
		var dateString = $("[@name=birthMonth]").val() + "/" + 
			$("[@name=birthDay]").val() + "/" + $("[@name=birthYear]").val();

		FormValid.log(0, "FormValid.dateSelect: "+dateString);

		$("[@name=birthDate]").val(dateString);
		
		FormValid.checkAll();	
	},
	
	passwordMatch:	function(thisObj) {
		FormValid.log(5, "FormValid.passwordMatch: "+thisObj.name);
		var aFields = $("password").get();
		
		if (aFields[0].value != "" && aFields[0].value == aFields[1].value) {
			FormValid.fieldValid(aFields[0]);
			FormValid.fieldValid(aFields[1]);
		} else {
			FormValid.fieldNotValid(aFields[0]);
			FormValid.fieldNotValid(aFields[1]);			
		}
		FormValid.isValid();
	},
	
	screenName:	function(thisObj) {
		FormValid.log(5, "FormValid.screenName: ");
		
		if (thisObj.value == "" || thisObj.value == $(thisObj).attr("defText") 
			|| thisObj.value.match(FormValid.BLANK_SPACE_RE)[0].length > 0) {
			$("#checkButton").css({
				opacity:"0.5",
				cursor:"default"
			});
			$("#availableMsg").css("display", "none");
			FormValid.fieldNotValid(thisObj);
		} else {
			$("#checkButton").css({
				opacity:"1",
				cursor:"pointer"
			});
		}
	},
	
	xboxCheck:	function(thisObj) {
		FormValid.log(3, "FormValid.xboxCheck: "+thisObj.checked);
		var aXboxChecks = $(".xbox").get();
		
		if (aXboxChecks[0].checked || aXboxChecks[1].checked)
			$("#xboxLiveTag").show();
		else
			$("#xboxLiveTag").hide();	
		
	},
	
	ps3check:	function(thisObj) {
		FormValid.log(3, "FormValid.ps3check: "+thisObj.checked);
		
		if (thisObj.checked)
			$("#psnetTag").show();
		else
			$("#psnetTag").hide();
	},
	
	ageCheck:	function(thisObj) {
		FormValid.log(5, "FormValid.ageCheck: "+thisObj.name);
		var now = new Date();
		var checkMiliSecs = Date.parse(thisObj.value);
		
		if (thisObj.value.match(FormValid.DATE_FORMAT_RE) && now.getTime() - checkMiliSecs >= FormValid.EIGHTEEN_YEARS_IN_MS) {
			FormValid.log(5, "FormValid.ageCheck: yes they are 18");
			FormValid.fieldValid(thisObj);
		} else {
			FormValid.log(5, "FormValid.ageCheck: nope they are TOO young");
			FormValid.fieldNotValid(thisObj);
		}
		
		FormValid.isValid();
	},
	
	numbersOnly:	function(thisObj) {
		FormValid.log(5, "FormValid.numbersOnly: "+thisObj.name);
		var bFieldValid = true;
		
//		for (var i=0; i<thisObj.value.length; i++)
//			if ((thisObj.value.charAt(i) < "0") || (thisObj.value.charAt(i) > "9"))
//				bFieldValid = false;
	
		bFieldValid = FormValid.hasOnly(thisObj.value, FormValid.DIGITS);
		
		if (thisObj.value.length == 0)
			bFieldValid = false;

		if (bFieldValid) {
			FormValid.log(5, "FormValid.numbersOnly: value is all numbers.");
			FormValid.fieldValid(thisObj);
			FormValid.isValid();
		} else {
			FormValid.log(5, "FormValid.numbersOnly: value does not contain only numbers.");
			FormValid.fieldNotValid(thisObj);
			FormValid.isValid();			
		}
	},
	
	phoneNumber:	function(thisObj) {
		FormValid.log(5, "FormValid.phoneNumber: "+thisObj.name);
		var bFieldValid = true;
		
		bFieldValid = FormValid.hasOnly(thisObj.value, FormValid.DIGITS+FormValid.PHONE_CHARS);
		
		if (thisObj.value.length < 10)
			bFieldValid = false;
			
		if (bFieldValid) {
			FormValid.log(5, "FormValid.phoneNumber: has the right length and  characters.");
			FormValid.fieldValid(thisObj);
			FormValid.isValid();
		} else {
			FormValid.log(5, "FormValid.phoneNumber: is not a valid phone number.");
			FormValid.fieldNotValid(thisObj);
			FormValid.isValid();			
		}			
	},
	
	postalCode:		function(thisObj) {
		if (FormValid.checkLinkBox(thisObj))
			return;
				
		var bFieldValid = true;
		
		if (thisObj.value.match(FormValid.US_POSTAL_CODE_RE) == null && (thisObj.value.match(FormValid.CAN_POSTAL_CODE_RE) == null))
			bFieldValid = false;

		if (bFieldValid) {
			FormValid.log(5, "FormValid.postalCode: is a valid zip code.");
			FormValid.fieldValid(thisObj);
			FormValid.isValid();
		} else {
			FormValid.log(5, "FormValid.postalCode: is not a valid zip code.");
			FormValid.fieldNotValid(thisObj);
			FormValid.isValid();			
		}						
	},
	
	email:		function(thisObj) {
		var aEmails = $(".email").get();
		
		if (aEmails[0].value != aEmails[1].value || thisObj.value.match(FormValid.EMAIL_FORMAT_RE) == null) {
			FormValid.log(5, "FormValid.email: is not a valid email address.");
			FormValid.fieldNotValid(aEmails[0]);
			FormValid.fieldNotValid(aEmails[1]);
			FormValid.isValid();			
		} else if (thisObj.value != FormValid.eMailAddress) {
			FormValid.log(5, "FormValid.email: stored email and field don't match, checking via Ajax.");
			FormValid.eMailAddress = thisObj.value;
			FormValid.isLoginAvailable();
		} else if (FormValid.bLoginAvailable) {
			FormValid.log(5, "FormValid.email: is a valid email address.");
			FormValid.fieldValid(aEmails[0]);
			FormValid.fieldValid(aEmails[1]);
			FormValid.isValid();
		}
	},
	
	submit:			function() {
		if (FormValid.isValid()) {
			FormValid.bAutoCheck = false;
			FormValid.log(3, "FormValid.submit: bAutoCheck set to false.");
			FormValid.defTextOnSubmit();
			$("form").get(0).submit();
		}
		
		return FormValid.bValid;
	},
	
	isPartValid:	function(mPart) {
		if ($(FormValid.aParts[mPart]).find(".notValid").get().length > 0) {
			$(FormValid.aParts[mPart]).find(".continueButton").css({
				opacity:"0.5",
				cursor:"default"				
			});
			return false;
		} else {
			$(FormValid.aParts[mPart]).find(".continueButton").css({
				opacity:"1",
				cursor:"pointer"				
			});
			$("#"+FormValid.MULTI_PART_NAV_ID+mPart).removeClass("error");
			return true;
		}
	},
	
	isValid:		function() {
		FormValid.isPartValid(FormValid.curPart);
		
		if ($(".notValid").get().length > 0) {
			FormValid.bValid = false;
			$(".submitButton").css({
				opacity:"0.5",
				cursor:"default"
			});
		} else {
			FormValid.bValid = true;
			$(".submitButton").css({
				opacity:"1",
				cursor:"pointer"
			});
		}
		
		return FormValid.bValid;
	},
	
	fieldValid:		function(thisObject) {
		FormValid.log(5, "FormValid.fieldValid: "+thisObject.name);		
		$(thisObject).removeClass("notValid").addClass("isValid");
		$("#"+thisObject.name+"Li").css("color", FormValid.NO_ERROR_COLOR);
		FormValid.hasFocus = null;		
	},
	
	fieldNotValid: 	function(thisObject) {
		FormValid.log(5, "FormValid.fieldNotValid: "+thisObject.name);
		$(thisObject).removeClass("isValid").addClass("notValid");
		$("#"+thisObject.name+"Li").css("color", FormValid.ERROR_COLOR);
		FormValid.hasFocus = null;
	},
	
	checkAll:	function() {
		FormValid.log("checkAll was called but part of it is commented out");
		if (!FormValid.bAutoCheck) {
			FormValid.log(3, "FormValid.checkAll: bAutoCheck false, exiting.");
			return;
		}
			
		var hasFocus = FormValid.hasFocus;
		FormValid.defTextOnSubmit();
		
		//for (var i=0; i<FormValid.aRequired[FormValid.curPart].length; i++) {
		//		if (FormValid.aRequired[FormValid.curPart][i].type == "select-one" || FormValid.aRequired[FormValid.curPart][i].type == "checkbox")
		//			FormValid.aOnChange[FormValid.aRequired[FormValid.curPart][i].name](FormValid.aRequired[FormValid.curPart][i]);
		//		else
		//			FormValid.aOnBlur[FormValid.aRequired[FormValid.curPart][i].name](FormValid.aRequired[FormValid.curPart][i]);
		//}
		
		FormValid.defTextOnError();
		$(hasFocus).focus();
	},

	checkAllTimer:	function() {			
		FormValid.checkAll();
		setTimeout( FormValid.checkAllTimer, FormValid.CHECK_ALL_TIMEOUT);
		FormValid.log(5, "FormValid.checkAllTimer has run: "+(++FormValid.checkAllCount));
	},
	
	setReview:		function() {
		FormValid.log(3, "FormValid.setReview: "+FormValid.aReview.length);
		
		FormValid.defTextOnSubmit();
		for (var i=0; i<FormValid.aReview.length; i++) {
			var revField = FormValid.findFormField(FormValid.aReview[i].id.replace(/Rev/, ""));

			if (revField != null) {
				FormValid.log(3, "FormValid.setReview: "+revField.name+": "+revField.type);
				
				if (FormValid.linkBoxState(revField)) {
					FormValid.log(3, "FormValid.setReview: linkBox checked: "+revField.name);
					$(FormValid.aReview[i]).text("N/A");
				} else if (FormValid.aReview[i].nodeName == "IMG") {
					FormValid.aReview[i].src = revField.value;
				} else {				
					switch(revField.type) {
						case "text":
							$(FormValid.aReview[i]).text(revField.value);
							break;
							
						case "textarea":
							$(FormValid.aReview[i]).text(revField.value);
							break;
					
						case "password":
							$(FormValid.aReview[i]).text(revField.value);
							break;
					
						case "select-one":
							if (revField.value == FormValid.NO_SELECTION)
								$(FormValid.aReview[i]).text("N/A");
							else
								$(FormValid.aReview[i]).text(revField.options[parseInt(revField.value)+1].text);
							
							break;
				
						default:
							FormValid.log(3, "FormValid.setReview: ***DEFAULT***: "+revField.type+": "+revField.name);
					}
				}
			} else
				FormValid.log(3, "FormValid.setReview: coudn't find source field: "+FormValid.aReview[i].id);
		}
		//
		//	custom conditionals
		//
		
		for (var i=0; i<FormValid.aReviewConditionals.length; i++) {
			var condField = $("#"+FormValid.aReviewConditionals[i].id.replace(/Cond/,"Rev")).get(0);
			
			FormValid.log(3, "FormValid.setReview: conditional: "+condField.id+": '"+$(condField).text()+"'");
			
			if ($(condField).text() == "" || $(condField).text() == undefined)
				$(FormValid.aReviewConditionals[i]).hide();
			else
				$(FormValid.aReviewConditionals[i]).show();
		}
		
		FormValid.defTextOnError();
	},
	
	//
	//	navagation
	//
	showPart:		function(partNum) {
		if (partNum < 0 || partNum > FormValid.aParts.length)
			return FormValid.curPart;
			
		FormValid.curPart = partNum;
		FormValid.aPartsVisited[partNum] = true;
		$("#PageNav>ul>li").removeClass("on");
		$(".MultiPart, .MultiPartInfo").hide();
		$("#"+FormValid.MULTI_PART_NAV_ID+FormValid.curPart).addClass("on").removeClass("error");
		$("#"+FormValid.MULTI_PART_ID+FormValid.curPart+", #"+FormValid.MULTI_PART_INFO_ID+FormValid.curPart).show();
		FormValid.isPartValid(partNum);
		FormValid.setReview();
		FormValid.checkAll();
		
		return partNum;
	},
	
	navToPart:		function() {
		var toPart = this.id.replace(FormValid.MULTI_PART_NAV_ID, "");
		if (toPart <= FormValid.curStep) {
			if (!FormValid.isPartValid(FormValid.curPart))
				$("#"+FormValid.MULTI_PART_NAV_ID+FormValid.curPart).addClass("error");
					
			FormValid.showPart(toPart);
		}
	},
	
	nextStep:		function(thisObj) {
		if ($(thisObj).css("opacity") == "0.5")
			return;
		
		if (FormValid.curStep == FormValid.curPart)
			FormValid.curStep++;
			
		FormValid.showPart(parseInt(FormValid.curPart)+1);	
	},
	
	nextPart:		function() {
		if (FormValid.curPart < FormValid.aParts.length)
			return FormValid.showPart(parseInt(FromValid.curPart)+1);
		else
			return false;
	},
	
	prevPart:		function() {
		if (FromValid.curPart > 0)
			return FormValid.showPart(parseInt(FormValid.curPart)-1);
		else
			return false;
	},

	//
	//	events
	//
	onFocus:		function() {
		FormValid.log(4, "FormValid.onFocus: "+this.name);
		FormValid.hasFocus = this;
	},
	
	onChange:		function() {
		FormValid.log(4, "FormValid.onChange: "+this.name);
		if (FormValid.aOnChange[this.name] != undefined)
			FormValid.aOnChange[this.name](this);
	},
	
	onBlur:			function() {
		FormValid.log(4, "FormValid.onBlur: "+this.name);
		if (FormValid.aOnBlur[this.name] != undefined)
			FormValid.aOnBlur[this.name](this);
		if (FormValid.hasFocus == this)
			FormValid.hasFocus = null;
	},

	//
	//	utilities
	//
	findFormField:		function(name) {
		var formField = null;
		
		if (formField = $("input[@name="+name+"]").get(0))
			return formField;
		
		if (formField = $("textarea[@name="+name+"]").get(0))
			return formField;
		
		return $("select[@name="+name+"]").get(0);
	},
	
	findRequired:		function(name) {
		FormValid.log(3, "FormaValid.findRequired: "+name);
		
		for (var iPart=0; iPart<FormValid.aParts.length; iPart++)
			for (var i=0; i<FormValid.aRequired[iPart].length; i++) {
				FormValid.log(3, "FormValid.findRequired: "+FormValid.aReuqired[iPart][i].name);
				if (FormValid.aRequired[iPart][i].name == name)
					return FormValid.aRequired[iPart][i];
			}
						
		return;
	},
	
	hasOnly:		function(value, validChars) {
		for (var i=0; i<value.length; i++)
			if (validChars.indexOf(value.charAt(i)) == -1)
				return false;
				
		return true;
	},
	
	digitCount:		function(value) {
		var digits = "0123456789";
		var iCount = 0;
		for (var i=0; i<value.length; i++)
			if (digits.indexOf(value.charAt(i)) > -1)
				iCount++;
		
		return iCount;
	},
	
	//
	//	linkBox methods
	//
	checkLinkBox:	function(thisObj) {
		if (FormValid.linkBoxState(thisObj)) {
			FormValid.log(5, "FormValid.checkLinkBox: linkBoxState true: "+thisObj.name);
			FormValid.fieldValid(thisObj);
			if ($(thisObj).is(".linkBox"))
				$(thisObj).css("opacity", "0.5").get(0).disabled = true;
			FormValid.isValid();
			return true;
		} else {
			if ($(thisObj).is(".linkBox"))
				$(thisObj).css("opacity", "1").get(0).disabled = false;
			return false;		
		}
	},
	
	linkBox:		function() {
		FormValid.log(5, "FormValid.linkBox: "+this.name);
		FormValid.checkAll();			
	},
	
	linkBoxState:	function(obj) {
		FormValid.log(5, "FormValid.linkBoxState: "+obj.name + ": " + 
			$("#" + $(obj).attr("linkBox")).attr("checked"));
		
		if ($(obj).attr("linkBox") == undefined )
			return false;
		else
			return $("#"+$(obj).attr("linkBox")).attr("checked");		
	},

	log:		function(level, msg) {
		if (FormValid.DEBUG || level <= parseInt(FormValid.LOG_LEVEL)) {
			//console.log.log(msg);
		}
	},
	
	//
	//	ajax calls
	//
	isLoginAvailable:	function() {
		FormValid.log(5, "FormValid.isLoginAvailable.");
		
		var emailOne = $("#emailOne").get(0).value;
		
		FormValid.eMailAddress = emailOne;
		ajaxPath = "ajax/"; // Need to change later.
		$.get(ajaxPath+"isLoginAvailable",
			{login:FormValid.eMailAddress},
			function(retData) {
				loginAvailable = eval(retData);
				FormValid.log(5, "FormValid.isLoginAvailable(callback): "+loginAvailable);
				
				if (loginAvailable) {
					FormValid.fieldValid($("#emailOne").get(0));
					FormValid.fieldValid($("#emailTwo").get(0));
					FormValid.bLoginAvailable = true;
					$("#emailAvailableMsg").removeClass("NameNotAvailable").addClass("NameAvailable")
						.text("Email Is Available!!").css("display", "block");
				} else {
					FormValid.fieldNotValid($("#emailOne").get(0));
					FormValid.fieldNotValid($("#emailTwo").get(0));
					FormValid.bLoginAvailable = false;
					$("#emailAvailableMsg").removeClass("NameNotAvailable").addClass("NameAvailable")
						.text("Email Is Not Available!!").css("display", "block");
					$("#emailTwo").get(0).value = "";
					$("#emailOne").get(0).focus();	
				}
					
				FormValid.isValid();
			}
		);
	},

	isNameAvailable:	function() {
		FormValid.log(5, "FormValid.isNameAvailable.");
		
		FormValid.hasFocus = null;
		
		if ($("#checkButton").css("opacity") == "0.5")
			return;
		
		var screenName = $("#screenName").get(0).value;
		FormValid.log(5, "FormValid.isNameAvailable: "+screenName);
		ajaxPath = "ajax/"; // Need to change later.
		$.get(ajaxPath+"isNameAvailable",
			{screenName:screenName},
			function(retData) {
			
				nameAvailable = eval(retData);
				
				FormValid.log(5, "FormValid.isNameAvailable(callback): "+nameAvailable);
				
				if (nameAvailable) {
					FormValid.fieldValid($("#screenName").get(0));
					$("#availableMsg").removeClass("NameNotAvailable");
					$("#availableMsg").removeClass("NameNotAvailable").addClass("NameAvailable")
						.text("Name Is Available!!").css("display", "block");
				} else {
					FormValid.fieldNotValid($("#screenName").get(0));
					$("#availableMsg").removeClass("NameAvailable").addClass("NameNotAvailable")
						.text("Name Is Taken!!").css("display", "block");
				}
					
				FormValid.isValid();
			}
		);
	},
//
//	test method section
//
	setAllParts:	function() {
		FormValid.curStep = FormValid.aParts.length;
		for (var i=0; i<FormValid.aParts.length; i++)
			FormValid.aPartsVisited[i] = true;
		FormValid.log(0, "FormValid.setAllParts: step set to max; all parts visited.");
	},
//
//	default text section
//
	initDefText:	function() {
		// this function sets up the default
		// text behavior for input fields
		var ia = $("input.clearDefText").get();
		for(var i=0; i<ia.length; i++) {
			FormValid.log(5, "initDefText: "+ia[i].name);
		
			if ($(ia[i]).attr('defText') == undefined)
				$(ia[i]).attr({defText:ia[i].value});
			else if (ia[i].value == "")
				ia[i].value = $(ia[i]).attr('defText');
				
			$(ia[i]).blur(FormValid.setDefText);
			$(ia[i]).focus(FormValid.clearDefText);
		}				
	},
	
	setDefText:		function() {
		FormValid.log(4, "setDefText: "+this.name);
		// this function sets the feault text
		// onBlur handler
		if (this.value == "")
			this.value = $(this).attr('defText');
			
		if (FormValid.hasFocus == this)
			FormValid.hasFocus = null;			
	},
	
	clearDefText:	function() {
		FormValid.log(5, "clearDefText: "+this.name);
		// this function clears the default text
		// onFocus handler
		if (this.value == $(this).attr('defText'))
			this.value = ""; 		
	},
	
	defTextOnSubmit:	function() {
		var aText = $(".clearDefText").get();
		for (var i=0;i<aText.length;i++)
			if (aText[i].value == $(aText[i]).attr('defText'))
				aText[i].value = "";
	},
	
	defTextOnError:		function() {
		FormValid.log(4, "FormValid.defTextOnError: ");
		var aText = $(".clearDefText").get();
		for (var i=0;i<aText.length;i++)
			if (aText[i] != FormValid.hasFocus && aText[i].value == "")
				aText[i].value = $(aText[i]).attr('defText');			
	}
	
};

$(document).ready(FormValid.init);
//console.log.log("FormValid.js loaded.");


