

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<bean:define id="siteId" value="wg" type="java.lang.String" />
<logic:present name="siteId" >
<bean:define id="siteId" name="siteId" type="java.lang.String"/>
</logic:present>

<div id="contentNoSidebar" class="soon">

<br><br><br>

	<div align="center">
	<table width="575">
		<tr valign="top">
			<td>
				<p style="text-align: justify">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean consectetuer. Etiam venenatis. Sed ultricies, pede sit amet aliquet lobortis, nisi ante sagittis sapien, in rhoncus lectus mauris quis massa. Integer porttitor, mi sit amet viverra faucibus, urna libero viverra nibh, sed dictum nisi mi et diam. Nulla nunc eros, convallis sed, varius ac, commodo et, magna. Proin vel risus. Vestibulum eu urna. Maecenas lobortis, pede ac dictum pulvinar, nibh ante vestibulum tortor, eget fermentum urna ipsum ac neque. Nam urna nulla, mollis blandit, pretium id, tristique vitae, neque. Etiam id tellus. Sed pharetra enim non nisl.</p>
			</td>
			<td><img src="WebContent/<bean:write name="siteId"/>/css/images/ads/TournamentsPage.png" width="178" height="289" alt="Coming Soon" style="padding-left: 75px;"></td>
		</tr>
	</table>
	</div>

</div>