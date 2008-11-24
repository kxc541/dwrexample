<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<div id="SearchWidget" class="Widget">
	<div class="WidgetContent" style="background: #bbb;">
		<html:form method="get" action="/search">
			<table>
				<tr>
					<td><html:text property="search" styleClass="text" value="Search" onfocus="if(this.value=='Search'){this.value='';}" onblur="if(this.value==''){this.value='Search';}" /></td>
					<td>&nbsp;<html:submit property="operation" styleClass="image" value=" " /></td>
				</tr>
			</table>
		</html:form>
	</div>
</div> <!-- end SearchWidget -->