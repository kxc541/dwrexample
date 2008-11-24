<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
  <style>
  td{
  padding: 6px 11px;
  border-bottom: 1px solid navy;
  vertical-align: top;
  }
  
  tr.over td{
		background: silver;
	}
	
	tr.alt td{
		background: olive;
	}
  </style>
  
  
  
  
  <style>
.wrap0, .wrap1, .wrap2, .wrap3 {
  display:inline-table;
  /* \*/display:block;/**/
  }
.wrap0 {
  float:left;
  background:url(shadow.gif) right bottom no-repeat;
  }
.wrap1 {
  background:url(shadow180.gif) no-repeat;
  }
.wrap2 {
  background:url(corner_bl.gif) -18px 100% no-repeat;
  }
.wrap3 {
padding:10px 14px 14px 10px;
  background:url(corner_tr.gif) 100% -18px no-repeat;
  }
  body { background: #fff;}
</style>

  
  
  
  
  
  
  <script type="text/javascript" src="jquery-1.2.3.js">Generic</script>
    
  <script type="text/javascript">
  
  $(document).ready(function(){
  	$(".mytable tr:even").addClass("over");
  	
  	$(".mytable tr:even").mouseover(function(){
	  	$(this).addClass("alt");}).mouseout(function(){
	  		$(this).removeClass("alt");
	 });
  
  
  	$("img.dropshadow").wrap("<div class='wrap1'><div class='wrap2'>" +
	"<div class='wrap3'></div></div></div>");
	});
  
  
  	$("a.link").mouseover(function(){
  	window.status=this.title; return true;
  	}).mouseout(function(){
  	window.status='done';return true;
  	});
  	});
 
  </script>
  
  </head>
  
  <body>
  
  <table class="mytable">
  	<tr>
  	<th>Head of table</th></tr>
  	
  	<tr>
  		<td>
  		This is first row
  		</td>
  	</tr>
  	<tr>
  		<td>
  		This is first row
  		</td>
  	</tr>
  	<tr>
  		<td>
  		This is first row
  		</td>
  	</tr>
  	<tr>
  		<td>
  		This is first row
  		</td>
  	</tr>
  	<tr>
  		<td>
  		This is first row
  		</td>
  	</tr>
  	
  	
  
  
  </table>
  
    
    <p>
    
    <a href="http://www.google.com" title="google" class="link">Google </a>
    </p>
    
    <img src="burnout3.png" class="dropshadow"/>
    
  </body>
</html>
