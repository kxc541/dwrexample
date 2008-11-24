
			function setMyDay(isMonth) {
				var month = document.getElementById('month').options[document.getElementById('month').selectedIndex].value;
				var year = document.getElementById('year').options[document.getElementById('year').selectedIndex].value;
				
				if(isMonth == 1) {
				var obj = document.getElementById('day').options.length = 0;
				if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
					for(index = 1; index < 32; index++)
						document.getElementById('day').options[index - 1] = new Option(index, index);
				}
				else if(month == 4 || month == 6 || month == 9 || month == 11) {
					for(index = 1; index < 31; index++)
						document.getElementById('day').options[index - 1] = new Option(index, index);
				}
				}
				if(month == 2){
					var obj = document.getElementById('day').options.length = 0;
					var isLeapYear = false;
					if(year == 3600 || (year % 100 == 0 && year % 400 != 0)) {
						isLeapYear = false;
					}
					else if((year % 400 == 0) || (year % 4 == 0 && year % 100 != 0)) {
						isLeapYear = true;
					}
					
					if(isLeapYear) {
						for(index = 1; index < 30; index++)
							document.getElementById('day').options[index - 1] = new Option(index, index);
					}
					else {
						for(index = 1; index < 29; index++)
							document.getElementById('day').options[index - 1] = new Option(index, index);
					}
				}
			}
	