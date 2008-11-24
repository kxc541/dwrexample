<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<bean:define id="siteId" value="wg" type="java.lang.String"/>
 <logic:present name="siteId" >
  <bean:define id="siteId" name="siteId" type="java.lang.String"/>
</logic:present>

		<div class="Format1">
			<div id="MyPicture">
				<img src="WebContent/<bean:write name="siteId"/>/css/images/pictures/user_picture1_full.jpg">
			</div>

			<div class="Picture grid">
				<a href="#" class="thumb landscape"><img src="WebContent/<bean:write name="siteId"/>/css/images/pictures/user_picture1_thumb.jpg"></a>
				<em><a href="#">Remove</a></em>
			</div>

			<div class="Picture grid">
				<a href="#" class="thumb portrait"><img src="WebContent/<bean:write name="siteId"/>/css/images/pictures/user_picture2_thumb.jpg"></a>
				<em><a href="#">Remove</a></em>
			</div>
		</div>

		<div class="Format1">
			<h2>Upload a new picture</h2>
			<input type="file">
		</div>