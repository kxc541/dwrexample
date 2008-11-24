<%@page import="com.g4g.utils.DataUtil"%>
<%@page import="com.g4g.utils.SessionUtil"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<bean:define id="screenName"  value="" type="java.lang.String"/>
<logic:present name="userDTO" property="playerDTO">
	<bean:define id="screenName"  name="userDTO" property="playerDTO.screenname" type="java.lang.String"/>	
</logic:present>

	<div id="div_chat" class="chat_main">
								
	</div><!-- div_chat -->
	
	<script type="text/javascript">
	function noenter(event) {
		if(event && event.keyCode == 13){
			  sendMessage('<bean:write name="screenName"/>')
  		} 
  	}
	</script>
	
	<table>
		<tr>
			<td><input type="text" id="text" name="text" style="width: 340px;" onkeypress="noenter(event);"  />&nbsp;&nbsp;</td>
			<td>
	<span id="btn_send_chat" class="buttons" style="opacity:1.0;cursor:default;">
			<button type="button" class="green" onclick="sendMessage('<bean:write name="screenName"/>')">send</button>
			
			 </span></td>
		</tr>
	</table>
							