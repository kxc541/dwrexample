<%@page import="com.g4g.utils.DataUtil"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">


<bean:define id="siteId" value="wg" type="java.lang.String" />
<logic:present name="siteId">
	<bean:define id="siteId" name="siteId" type="java.lang.String" />
</logic:present>

<html>
<head>
<link rel="stylesheet" href="<%= DataUtil.getBasePath(request) %>WebContent/<bean:write name="siteId"/>/css/Global.css" media="screen">

<style>
     
     .compose {
      margin:20px;
      padding:10px;
      border:1px solid #e8e5e0;
     }
     
     .compose h1 {
      font-size:12px;
      font-weight:bold;
      padding:10px;
      margin-bottom:18px;
     }
     
     .compose textarea {
      margin:5px 0;
      font-size:12px;
      z-index:300;
     }
     
     .compose .grid6 {
      margin-right:20px;
     }
     
     .compose .grid6 dt {
      line-height:18px;
      margin-bottom:5px;
     }
     
     img.gameImage {
      width:60px;
     }

     #ModalMessage {
      padding:10px;
      margin:0 auto;
      border:5px solid #666;
     }
     
     #ModalMessage .buttons a {
      color:#fff;
     }
          
     #ModalMessage textarea {
      height:140px;
      font-size:12px;
      padding:5px;
      borer:1px solid #666;
      z-index:500;
      font-family:verdana;
     }
     
     .MessageContainer {
      background:url(wg/css/images/bg/TournamentBG.jpg) top center no-repeat #d2d2d2;
      margin:5px 0;
      border:1px solid #e2e1dd;
     }
     
     .MessageContainer.Unread {}
     
     .MessageContainer a {color:#000;}
     
     .MessageContent .grid2 {
      width:100px;
     }
     
     .messageReply textarea {
      margin:10px 0;
     }
     
     .grid { float:left; }
     
     .gridRight {float:right;}
     
     .grid1 { width:60px; }
     .grid2 { width:80px; }
    </style>
    <script type="text/javascript">
    function clearaa(){
    	alert(self.parent.document.getElementById('yourMessageTemp').value);
    	self.parent.document.getElementById('yourMessageTemp').value = '';
    	self.parent.tb_remove(false);
    }
    
    </script>
</head>
<body>

 <div id="content">
<div id="ModalMessage" class="MessageContainer compose clearfix">
<img src="<%= DataUtil.getBasePath(request) %>WebContent/image.jsp" height="90" width="90">
    
    	<div class="buttons">
        	<a href="#" onclick="self.parent.tb_remove(false);" class="button orange inline" style="opacity:1.0;cursor:default;" >OK</a>
      	</div>
    </div>
    </div>
</body>
</html>