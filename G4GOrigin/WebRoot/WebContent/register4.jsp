
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<bean:define id="siteId" value="wg" type="java.lang.String"/>
 <logic:present name="siteId" >
  <bean:define id="siteId" name="siteId" type="java.lang.String"/>
</logic:present>
<div id="pageTitle">
	<img src="WebContent/<bean:write name="siteId"/>/css/images/banners/banners-registration.jpg" width="920" height="41" alt="Registration">
</div>

<div id="sidebar" class="registration">
	<div id="sidebarStepsTitle">
		<h3>4 Easy Steps!</h3>
	</div>
	<div id="sidebarSteps">
		<p class="on">Enter Your Login Info</p>
		<p class="on">Pick Your Games</p>
		<p class="on">Review</p>
		<p class="on">Get Started!</p>
	</div>
	<div id="sidebarCallOut">
		<h3>Get in the Game!</h3>
		<p>Ten years ago a crack commando unit was sent to prison by a military court for a crime they didn't commit. These men promptly escaped from a maximum security stockade to the Los Angeles underground. Today, still wanted by the government, they survive as soldiers of fortune. If you have a problem and no one else can help, and if you can find them, maybe you can hire the A-team.</p>
	</div>
	
	<p class="note"><b>*Residents of the following states are not eligible to compete in any cash tournaments on the site:</b><br>
	Arizona, Arkansas, Connecticut, Delaware, Florida, Illinois, Iowa, Louisiana, Montana, South Carolina, South Dakota, Tennessee, and Vermont.</p>
	
	<p class="note">Please <a href="#">here</a> for more information</p>
</div>

<div id="content" class="registration">

<table cellpadding="0" cellspacing="0" border="0" width="100%">
<tr><td>
	<h1>You've Successfully Registered!</h1>
	<p>Great Job! We hope you enjoy WorldGaming.</p>
	<p>&nbsp;</p>
	<h2>Add Money to your account</h2>
	<img src="WebContent/<bean:write name="siteId"/>/css/images/icons/money.png" style="margin: 0px 60px 0px 60px; float: right;">
	<p>Before you can really get going, you need to add an account using our secure credit infrastructure. You know what they say... &quot;it takes money to make money!&quot;</p>

	<p><img src="WebContent/<bean:write name="siteId"/>/css/images/icons/more.png"> <html:link action="displayCashier.do">Go to the Cashier Page</html:link></p>

</td></tr>
<tr><td>

	<h2>Check out your homepage</h2>

	<img src="WebContent/<bean:write name="siteId"/>/css/images/icons/homepage.png" style="margin: 0px 60px 0px 60px; float: right;">
	<p>Not ready to add money yet? Go ahead and check out your homepage. You can always add an account by visiting the Cashier Page; just click &quot;Cashier Page&quot; available on all pages in the site at any time.</p>

	<p><img src="WebContent/<bean:write name="siteId"/>/css/images/icons/more.png"> <html:link action="displayProfile.do">Go to your Home Page</html:link></p>
	
</td></tr>
</table>

</div>