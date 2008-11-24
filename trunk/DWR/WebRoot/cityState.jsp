<%@ page language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>


<html:html locale="true">

<head>
	<title><bean:message key="welcome.title" /></title>

	<html:base />
	<script type='text/javascript' src='dwr/interface/CityStateDAO.js'></script>
	<script type='text/javascript' src='dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
</head>

<script type="text/javascript">
        // Calls java method to get Cities by State selected
        function onChangeState() {
            CityStateDAO.getCitiesByState($("stateList").value, populateCities);
        }
        // Fills in City values based on state choosen
        function populateCities(data) {
            DWRUtil.removeAllOptions("cityList");
            DWRUtil.addOptions("cityList", data, "id", "name" );
        }
    </script>

<body bgcolor="white">
	<h1>
		<bean:message key="welcome.list" />
	</h1>
	<br>
	State:
	<html:select onchange="onChangeState();" name="cityStateForm"
		property="state" styleId="stateList">
		<html:option value="">Choose A State</html:option>
		<html:optionsCollection name="stateList" label="acronym" value="id" />
	</html:select>

	&nbsp; City:
	<html:select name="cityStateForm" property="city" styleId="cityList">
		<html:option value="">&nbsp;&nbsp;&nbsp;&nbsp;</html:option>
	</html:select>

</body>
</html:html>
