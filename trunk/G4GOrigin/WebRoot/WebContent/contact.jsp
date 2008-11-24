<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<title>Contact World Gaming</title>
		<jsp:include page="common/HeadTagNew.jsp" />
	</head>
	<body>

		<div id="page">

			<logic:present name="userDTO" property="playerDTO">
				<jsp:include page="common/HeaderLoginNew.jsp" />
				<jsp:include page="common/GlobalNavigationLogin.jsp" />
			</logic:present>

			<logic:notPresent name="userDTO" property="playerDTO">
				<jsp:include page="common/HeaderNew.jsp" />
				<jsp:include page="common/GlobalNavigation.jsp" />
			</logic:notPresent>


			<div id="content" class="info">

				<h1>
					Contact Us
				</h1>

			</div>

			<jsp:include page="common/sidebar.jsp" />

			<jsp:include page="common/FooterNavNew.jsp" />

		</div>

	</body>
</html>