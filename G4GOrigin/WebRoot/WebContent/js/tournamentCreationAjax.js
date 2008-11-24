
$(document).ready(function(){

$(function()
{
	
	$('.date-pick').datePicker({clickInput:true})
	$('#Level1StartDate').bind('dpClosed',function(e, selectedDates){
			var d = selectedDates[0];
			if (d) {
				d = new Date(d);
				$('#Level1EndDate').dpSetStartDate(d.addDays(0).asString());
			}});
	$('#Level1EndDate').bind('dpClosed',function(e, selectedDates){
			var d = selectedDates[0];
			if (d) {
				d = new Date(d);
				$('#Level1StartDate').dpSetEndDate(d.addDays(0).asString());
			}});
			
			
	$('#Level2StartDate').bind('dpClosed',function(e, selectedDates){
			var d = selectedDates[0];
			if (d) {
				d = new Date(d);
				$('#Level2EndDate').dpSetStartDate(d.addDays(0).asString());
			}});
	$('#Level2EndDate').bind('dpClosed',function(e, selectedDates){
			var d = selectedDates[0];
			if (d) {
				d = new Date(d);
				$('#Level2StartDate').dpSetEndDate(d.addDays(0).asString());
			}});
			
	$('#Level3StartDate').bind('dpClosed',function(e, selectedDates){
			var d = selectedDates[0];
			if (d) {
				d = new Date(d);
				$('#Level3EndDate').dpSetStartDate(d.addDays(0).asString());
			}});
	$('#Level3EndDate').bind('dpClosed',function(e, selectedDates){
			var d = selectedDates[0];
			if (d) {
				d = new Date(d);
				$('#Level3StartDate').dpSetEndDate(d.addDays(0).asString());
			}});
			
			
			
	$('#Level4StartDate').bind('dpClosed',function(e, selectedDates){
			var d = selectedDates[0];
			if (d) {
				d = new Date(d);
				$('#Level4EndDate').dpSetStartDate(d.addDays(0).asString());
			}});
	$('#Level4EndDate').bind('dpClosed',function(e, selectedDates){
			var d = selectedDates[0];
			if (d) {
				d = new Date(d);
				$('#Level4StartDate').dpSetEndDate(d.addDays(0).asString());
			}});
			
	$('#Level5StartDate').bind('dpClosed',function(e, selectedDates){
			var d = selectedDates[0];
			if (d) {
				d = new Date(d);
				$('#Level5EndDate').dpSetStartDate(d.addDays(0).asString());
			}});
	$('#Level5EndDate').bind('dpClosed',function(e, selectedDates){
			var d = selectedDates[0];
			if (d) {
				d = new Date(d);
				$('#Level5StartDate').dpSetEndDate(d.addDays(0).asString());
			}});
			
	$('#Level6StartDate').bind('dpClosed',function(e, selectedDates){
			var d = selectedDates[0];
			if (d) {
				d = new Date(d);
				$('#Level6EndDate').dpSetStartDate(d.addDays(0).asString());
			}});
	$('#Level6EndDate').bind('dpClosed',function(e, selectedDates){
			var d = selectedDates[0];
			if (d) {
				d = new Date(d);
				$('#Level6StartDate').dpSetEndDate(d.addDays(0).asString());
			}});
	
	
});



$('.clock').clockpick({
starthour :0,
endhour : 23,
showminutes : true,
minutedivisions:4,
layout:'vertical'
}
);


}
);
