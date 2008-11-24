<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    	<script type='text/javascript' src='dwr/interface/CarsFacade.js'> </script>
		<script type="text/javascript" src="dwr/engine.js"></script>
		<script type="text/javascript" src="dwr/util.js"></script>

		<script type="text/javascript">

  function populateList(map){
	var list = map["list"];
	var totalSize = map["totalSize"];
	var listHTML = "";
	for (var i in list){
		listHTML += "<tr><td>";
		listHTML += list[i]["brand"];
		listHTML += "</td><td>";

		listHTML += "<td>";
		listHTML += list[i]["model"];
		listHTML += "</td><td>";


		listHTML += "<td>";
		listHTML += list[i]["serialNo"];
		listHTML += "</td>";

		listHTML += "</tr>";
	}
	$("#carList-items").html(listHTML);

	// Render the paging footer with links to each list page
	pages = (totalSize / rowsPerPage);
	if (totalSize % rowsPerPage > 1){
		pages++;
	}

	pagingHtml = "<strong>Page:</strong> ";

	for (i=1; i<=pages; i++){
		if (currentPage == i){
			pagingHtml += "<strong>"+i+"</strong>&nbsp;";
		} else {
			pagingHtml += "<a href='javascript:goToPage("+i+");'>"+i+"</a>&nbsp;";
		}
	}

	$("#paging").html(pagingHtml);
}



function goToPage(newPage){
   currentPage = newPage;
   CarsFacade.goToPage(newPage,populateList);
}

$(document).ready({
	goToPage(1);
});

function sort(attribute){
	alert(attribute);
    CarsFacade.sort(attribute, currentPage ,populateList);
}

</script>

  <script type="text/javascript" src="js/jquery-1.2.1.js"></script><script
			type="text/javascript" src="js/jquery.js"></script>
	</head>

  <body>
   <table id="carList">
   <thead>
      <tr>
         <th><a href="javascript:sort('brand')">Brand</a></th>
         <th><a href="javascript:sort('model')">Model</a></th>
         <th><a href="javascript:sort('serialNo')">Serial number</a></th>
      </tr>
   </thead>
   <tbody id="carList-items">
   </tbody>
</table>
<p id="paging"></p>


  </body>

</html>
