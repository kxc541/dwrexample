
var MC = new MathContext("0");
function exponent(num, exp) {
	var ret = new BigDecimal("1");
	for (var i = 0; i < exp.intValueExact(); i++) {
		ret = ret.multiply(num);
	}
	return ret;
}
function toggleRows(tableId, numRows) {
	tbl = document.getElementById(tableId);
	var len = tbl.rows.length;
	var hideStyle = "none";
	var showStyle = "";
	for (i = 1; i < len; i++) {
		if (i <= numRows) {
			tbl.rows[i].style.display = showStyle;
		} else {
			tbl.rows[i].style.display = hideStyle;
		}
	}
}
function recalculate(theForm) {
	var hiddenForm = document.getElementById("theForm");
	var levels;
	if (theForm.tournamentLevels.options[theForm.tournamentLevels.selectedIndex].value.length > 0) {
		levels = new BigDecimal(theForm.tournamentLevels.options[theForm.tournamentLevels.selectedIndex].value);
	} else {
		levels = new BigDecimal("0");
	}
	hiddenForm.levels.value = levels.toString();
	var entryFee;
	if (theForm.entryfee.value.length > 0) {
		entryFee = new BigDecimal(theForm.entryfee.value);
	} else {
		entryFee = new BigDecimal("0");
	}
	hiddenForm.entryFee.value = entryFee.toString();
	var tournamentFee;
	if (theForm.tournamentFee.value.length > 0) {
		tournamentFee = new BigDecimal(theForm.tournamentFee.value);
	} else {
		tournamentFee = new BigDecimal("0");
	}
	hiddenForm.houseFeePerPlayer.value = tournamentFee.toString();
	var totalPlayers = exponent(new BigDecimal("2"), levels);
	var totalPot = totalPlayers.multiply(entryFee);
	var totalHold = totalPlayers.multiply(tournamentFee);
	tbl = document.getElementById("levelsTable");
	var tournamentTotalPayout = new BigDecimal("0");
	var levelPayoutAmount = new BigDecimal("0");
	var levelPayoutPercent = new BigDecimal("0");	
	var remainingPot = totalPot.subtract(totalHold.add(tournamentTotalPayout));	
	for (var i = 1; i <= 6; i++) {
		if (i <= levels.intValueExact() + 1) {
			var inverseLevel = levels.subtract(new BigDecimal(i.toString()));
			var levelUsers = exponent(new BigDecimal("2"), inverseLevel);
			var levelPercent;
			if (document.getElementById("Level" + i + "Percent").value.length > 0) {
				levelPercent = new BigDecimal(document.getElementById("Level" + i + "Percent").value);								
			} else {				
				levelPercent = new BigDecimal("0");
			}			
			var loosingper;			
			if (document.getElementById("Level" + i + "Amount").value.length > 0) {
				loosingper = new BigDecimal(document.getElementById("Level" + i + "Amount").value);
			} else {
				loosingper = new BigDecimal("0");
			}	
			
			var percent_get = new BigDecimal("0");
			percent_get = document.getElementById("Level" + i + "Users").innerHTML;
			
			var winningamt = ((totalPot - totalHold) * levelPercent) / 100;
			winningamt = winningamt * percent_get;						
			var loosingamt = ((totalPot - totalHold) * loosingper) / 100;
			loosingamt = loosingamt * percent_get;
			var totalamt = winningamt + loosingamt;
			
			var per = new BigDecimal("0");
			per = levelPercent.add(loosingper);			
			
			var levelAmount = (totalPot.subtract(totalHold)).multiply(per);				
			levelPayoutAmount = levelPayoutAmount.add(levelAmount);			
			levelPayoutPercent = levelPayoutPercent.add(levelPercent);			
			tournamentTotalPayout = tournamentTotalPayout.add(levelUsers.multiply(levelAmount));	
			var levelTotalPercent = levelUsers.multiply(levelPercent);
			var levelTotalAmount = levelUsers.multiply(levelAmount);
			document.getElementById("hfLevel" + i + "Amount").value = levelAmount.toString();
			document.getElementById("hfLevel" + i + "StartDate").value = document.getElementById("Level" + i + "StartDate").value;
			document.getElementById("hfLevel" + i + "StartTime").value = document.getElementById("Level" + i + "StartTime").value;
			document.getElementById("hfLevel" + i + "EndDate").value = document.getElementById("Level" + i + "EndDate").value;
			document.getElementById("hfLevel" + i + "EndTime").value = document.getElementById("Level" + i + "EndTime").value;
			document.getElementById("Level" + i + "Users").innerHTML = levelUsers.toString();
			document.getElementById("Level" + i + "Loosers").innerHTML = levelUsers.toString();
			document.getElementById("Level" + i + "Amount").innerHTML = loosingamt.toString();
			document.getElementById("Level" + i + "PayoutAmount").innerHTML = loosingamt.toString();
			document.getElementById("Level" + i + "PayoutPercent").innerHTML = winningamt.toString();
			document.getElementById("Level" + i + "TotalAmount").innerHTML = totalamt.toString();
			document.getElementById("Level" + i + "TotalPercent").innerHTML = winningamt.toString();
			
			if(remainingPot == 0)
				remainingPot = 0;
			else {				
				remainingPot = remainingPot - winningamt;
				remainingPot = round(remainingPot);	
				remainingPot = remainingPot - loosingamt;
				remainingPot = round(remainingPot);
			}
		} else {
			document.getElementById("Level" + i + "Users").innerHTML = "";
			document.getElementById("Level" + i + "Loosers").innerHTML = "";
			document.getElementById("Level" + i + "Amount").innerHTML = "";
			document.getElementById("Level" + i + "PayoutAmount").innerHTML = "";
			document.getElementById("Level" + i + "PayoutPercent").innerHTML = "";
			document.getElementById("Level" + i + "TotalAmount").innerHTML = "";
			document.getElementById("Level" + i + "TotalPercent").innerHTML = "";
			document.getElementById("Level" + i + "Percent").value = "";
			document.getElementById("hfLevel" + i + "Amount").value = "";
			document.getElementById("hfLevel" + i + "StartDate").value = "";
			document.getElementById("hfLevel" + i + "StartTime").value = "";
			document.getElementById("hfLevel" + i + "EndDate").value = "";
			document.getElementById("hfLevel" + i + "EndTime").value = "";
		}		
	}	
	//var remainingPot = totalPot.subtract(totalHold.add(tournamentTotalPayout));
	var remainingPotPerPlayer = remainingPot /totalPlayers;	
	document.getElementById("totalPot").innerHTML = totalPot;
	document.getElementById("totalHold").innerHTML = totalHold;
	document.getElementById("remainingPot").innerHTML = remainingPot;
	document.getElementById("totalpotforvalidation").value = remainingPot;
	var j = document.getElementById("totalpotforvalidation").value;
	document.getElementById("remainingPotPerPlayer").innerHTML = remainingPotPerPlayer;
	toggleRows("levelsTable", levels.intValueExact() + 1);
}
function round(x) {
	return Math.round(x*100)/100;
}

function validateRemainingPot() {
	var remainingPot = document.getElementById("totalpotforvalidation").value;
	if (remainingPot != 0) {
		alert("Remaining Pot and Remaining Pot Per Player must be 0.");
		return false;
	}
	return true;
}
	//Function to validate only numbers are allowed.
function checkTBKeyPress(obj, tbtype, length, precision, event) {
	keyCode = event.keyCode ? event.keyCode : event.charCode ? event.charCode : event.which ? event.which : void 0;
	
    //alert(keyCode);
 	//9 = Tab, 32 = Space, 13 = Enter, 8 = Back Space, 46 = Delete, 37 = Move Left, 39 = Move Right, 35 = End, 36 = Home
	if (keyCode == 9 || keyCode == 32 || keyCode == 13 || keyCode == 46 || keyCode == 8 || keyCode == 35 || keyCode == 36 || keyCode == 37) {
		if (keyCode == 190 && obj.value.length > 0) {
			retnullval(event);
			return;
		}
		return;
	}
	if (obj.value.length >= length) {
		retnullval(event);
		return;
	}
	if (tbtype == "alphanumeric") {
		if ((keyCode < 65 || keyCode > 90) && (keyCode < 97 || keyCode > 122) && (keyCode < 48 || keyCode > 57)) {
			retnullval(event);
			return;
		}
		return;
	}
	if (tbtype == "alpha") {
		if ((keyCode < 65 || keyCode > 90) && (keyCode < 97 || keyCode > 122)) {
			retnullval(event);
			return;
		}
	}
	if (tbtype == "float") {
		if (keyCode == 46) {
			if (precision == 0) {
				retnullval(event);
				return;
			}
			if (length - obj.value.length == 1) {
				retnullval(event);
				return;
			}
			if (obj.value.length == 0) {
				obj.value = "0";
				return;
			}
			var ind = obj.value.indexOf(".");
			if (ind >= 0) {
				retnullval(event);
			}
		} else {
			if (keyCode < 48 || keyCode > 57) {
				retnullval(event);
				return;
			}
		}
		var ind = obj.value.indexOf(".");
		if (ind >= 0) {
			var sstr = obj.value.substring(ind);
			if (sstr.length > precision) {
				retnullval(event);
			}
			return;
		}
	}
	if (tbtype == "integer") {
		if (keyCode < 48 || keyCode > 57) {
			retnullval(event);
			return;
		}
	}
}
function retnullval(event) {
	event = (window.event) ? window.event : event;
	if (event.preventDefault) {
		event.preventDefault();
	} else {
		event.returnValue = false;
		return false;
	}
}

