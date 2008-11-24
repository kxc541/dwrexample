$(document).ready(function(){    
	//called when key is pressed in textbox
	$(".IntegerValue").keypress(function (e)  
	{ 
	  	//if the letter is not digit then display error and don't type anything
	  	if( e.which!=9 && e.which!=8 && e.which!=0 && (e.which<48 || e.which>57))
	  	{
	    	return false;
      	}	
	});
	
	$(".AlphaValue").keypress(function (e)  
	{ 
	  	//if the letter is not digit then display error and don't type anything
	  	if( e.which!=9 && e.which!=8 && e.which!=0 && ((e.which<65 || e.which>90) && (e.which<97 || e.which>122)))
	  	{
	    	return false;
      	}	
	});
});