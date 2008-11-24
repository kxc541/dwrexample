<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<bean:define id="siteId" value="wg" type="java.lang.String"/>
 <logic:present name="siteId" >
  <bean:define id="siteId" name="siteId" type="java.lang.String"/>
</logic:present>
		
		<link rel="stylesheet" href="WebContent/<bean:write name="siteId"/>/css/Global.css" media="screen">
		<link rel="stylesheet" href="WebContent/<bean:write name="siteId"/>/stylesheet.css" media="screen">
		<link rel="stylesheet" href="WebContent/<bean:write name="siteId"/>/css/thickbox.css" media="screen">
		<link rel="stylesheet" href="WebContent/<bean:write name="siteId"/>/css/cfab.css" media="screen">
		<script type="text/javascript" src="WebContent/<bean:write name="siteId"/>/lib/firebug/firebug.js">Generic</script>
		<script type="text/javascript" src="WebContent/<bean:write name="siteId"/>/js/jquery.js"> </script>
		<script type="text/javascript" src="WebContent/<bean:write name="siteId"/>/javascript/interface.js"> </script> <!-- jquery drag and drop http://interface.eyecon.ro/ -->
		<script type="text/javascript" src="WebContent/<bean:write name="siteId"/>/lib/thickbox.js"> </script> <!-- thickbox cfab version -->
		<script type="text/javascript" src="WebContent/<bean:write name="siteId"/>/lib/g4glib.js"> </script> <!-- new js lib  -->
	<!--ModalWrapper-->
		<div id="content">
			<!-- /cgi-bin/WebObjects/g4g.woa/wo/41.0.ModalWrapper.15.1 -->
	
				 <!-- <div class="MessageContainer clearfix active"> -->
				<!--
				background:#f2f1ef;
				-->
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
					.grid3 { width:100px; }
					.grid4 { width:125px; }
					.grid5 { width:140px; }
					.grid6 { width:170px; }
					.grid7 { width: 250px; }
					.grid8 { width: 315px; }
					.grid9 { width: 375px; }
					.grid10 { width:455px; }
				</style>
				
				<div id="ModalMessage" class="MessageContainer compose clearfix">
					Lorem ipsum dolor sit ameted ultnisi ante , mi sit amet viverra faucibus, urna libero viverra nibh, sed dictum nisi mi et diam. Nulla nunc eros, convallis sed, varius ac, commodo et, magna. Proin vel risus. Vestibulum eu urna. Maecenas lobortis, pede ac dictum pulvinar, nibh ante vestibulum tortor, eget fermentum urna ipsum ac neque. Nam urna nulla, mollis blandit, pretium id, tristique vitae, neque. Etiam id tellus. Sed pharetra enim non nisl.
					
					<div class="messageInfo grid grid8">
						<div class="messageReply textareaFull">							
							<div class="buttons">
								<a href="#" onclick="self.parent.tb_remove()" class="button green inline"  style="opacity:1.0;cursor:default;">cancel</a>
							</div>
						</div>
					</div>		
				</div><!-- MessageContainer -->
			
		</div>

