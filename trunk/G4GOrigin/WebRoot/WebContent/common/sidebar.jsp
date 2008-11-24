<%@page import="com.g4g.utils.G4GConstants"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<div id="sidebar" class="info">
	<ul>
		<li>
			<html:link forward="<%=G4GConstants.TERMS %>">Terms &amp; Conditions</html:link>
		</li>
		<li>
			<html:link forward="<%=G4GConstants.PRIVACY %>">Privacy Policy</html:link>
		</li>
		<li>
			<html:link forward="<%=G4GConstants.ABOUT %>">About World Gaming</html:link>
		</li>
		<li>
			<html:link forward="<%=G4GConstants.MANAGEMENT %>">Management Team</html:link>
		</li>
		<li>
			<html:link forward="<%=G4GConstants.PRESS %>">Press</html:link>
		</li>
		<li>
			<html:link forward="<%=G4GConstants.ADVERTISE %>">Advertise</html:link>
		</li>
		<li>
			<html:link forward="<%=G4GConstants.RESPONSIBLEGAMING %>">Responsible Gaming</html:link>
		</li>
		<li>
			<html:link forward="<%=G4GConstants.CONTACT %>">Contact</html:link>
		</li>
	</ul>
</div>