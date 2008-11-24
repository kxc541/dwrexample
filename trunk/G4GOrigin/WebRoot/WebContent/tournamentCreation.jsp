<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
 
 <head>
  <meta content="text/html; charset=ISO-8859-1" http-equiv="content-type">
  <title>Tournament Creation Window</title>
        <script type="text/javascript" src="WebContent/js/mathcontext.js"></script>
        <script type="text/javascript" src="WebContent/js/bigdecimal.js"></script>
  <script type="text/javascript" src="WebContent/js/tournamentCreation.js"></script>
  
  <script type="text/javascript"  src="WebContent/js/jquery-1.2.3.js"></script>
  <script type="text/javascript"  src="WebContent/js/jquery.dimensions.js"></script>
  <script type="text/javascript"  src="WebContent/js/jquery.clockpick.1.2.2.js"></script>
  <script type="text/javascript"  src="WebContent/js/jquery.date_input.js"></script>
  <script type="text/javascript"  src="WebContent/js/date.js"></script>
  <script type="text/javascript"  src="WebContent/js/jquery.bgiframe.js"></script>
  <script type="text/javascript"  src="WebContent/js/jquery-1.datePicker.js"></script>
  <script type="text/javascript"  src="WebContent/js/tournamentCreationAjax.js"></script>
  <script type="text/javascript"  src="WebContent/js/AJAXScript.js"></script>

  <link rel="stylesheet" type="text/css" media="screen" href="WebContent/commoncss/clockpick.css">
  <link rel="stylesheet" type="text/css" media="screen" href="WebContent/commoncss/date_input.css">
  <link rel="stylesheet" type="text/css" media="screen" href="WebContent/commoncss/datePicker.css">
  <link rel="stylesheet" type="text/css" media="screen" href="WebContent/commoncss/demo.css">
 </head>
 
 <body onLoad="toggleRows('levelsTable', 0)">
 
 <html:form action="/tournamentCreation" styleId="theForm" method="get" onsubmit="return validateRemainingPot()">
  <div>
   <html:errors/>
  </div>
   <bean:define id="theForm" name="theForm" scope="request"
     type="com.g4g.forms.TournamentCreationForm" />
   <bean:define id="gameList" name="theForm" property="gameList" type="java.util.List"/>
   <table>
    <tbody>
     <tr>
      <td>Game</td>
      <td>
       <html:select property="tournamentGame" onchange="recalculate (theForm);retriveGameOptions(this.value)">
        <html:options property="key" labelProperty="description" collection="gameList" />
       </html:select>
      </td>
     </tr>
     <tr>
      <td>Number Of Levels</td>
      <td>
       <html:select property="tournamentLevels" onchange="recalculate (theForm)" styleId="tournamentLevels">
         <html:option value="-1">--- Please Select a number ---</html:option>
         <html:option value="1">1 Level (Head to head Match)</html:option>
         <html:option value="2">2 Level (4 Players)</html:option>
         <html:option value="3">3 Level (8 Players)</html:option>
         <html:option value="4">4 Level (16 Players)</html:option>
         <html:option value="5">5 Level (32 Players)</html:option>
         <html:option value="6">6 Level (64 Players)</html:option>
       </html:select>
      </td>
     </tr>
     <tr>
      <td>Entry Fee</td>
      <td>
       <html:text property="entryfee" styleId="entryFee" onchange="recalculate (theForm)" onkeypress="javascript: return checkTBKeyPress(this,'integer',10,0, event)"/>
      </td>
     </tr>
     <tr>
      <td>House Hold Per Player</td>
      <td>
       <html:text property="tournamentFee" styleId="tournamentFee" onchange="recalculate (theForm)"/>
      </td>
     </tr>
     <tr>
      <td>Total Pot</td>
      <td>
       <input type="hidden" name="totalpot" id="totalpotforvalidation">
       <div id="totalPot"/>
      </td>
     </tr>
     <tr>
      <td>Total Hold</td>
      <td>
       <div id="totalHold"/>
      </td>
     </tr>
     <tr>
      <td>Remaining Pot</td>
      <td>
       <div id="remainingPot"/>
      </td>
     </tr>
     <tr>
      <td>Remaining Pot Per Player</td>
      <td>
       <div id="remainingPotPerPlayer"/>
      </td>
     </tr>
    </tbody>
   </table>
   <br>
   <br>
   <br>
    <table border="1">
    <tbody>
     <tr>
      <td>
      <div id="Gameoptions">
       Game Options Go Here. To set its style see AJAXScript.js createGameOptions method.
      </div>
      </td>
     </tr>
    </tbody>
    </table>
   <br>
   <br>
   <br>
   <table border="1" id="levelsTable">
    <tbody>
     <tr>
      <th>Level</th>
      <th>Winners</th>
      <th>Loosers</th>
      <th>Winning %</th>
      <th>Loosing %</th>
      <th>Winning Total amt.</th>
      <th>Loosing Total amt.</th>      
      <th></th>
      <th>Total amt.</th>
      <th>Start Date</th>
      <th>Start Time</th>
      <th>End Date</th>
      <th>End Time</th>
     </tr>
     <tr>
      <td>0</td>
      <td><div id="Level0Users"/></td>
      <td><div id="Level0Users"/></td>      
      <td><div id="Level0Amount"/></td>
      <td><div id="Level0PayoutPercent"/></td>
      <td><div id="Level0PayoutAmount"/></td>
      <td><div id="Level0TotalPercent"/></td>
      <td><div id="Level0TotalAmount"/></td>
      <td><input type="hidden" value="" id="Level0StartDate"  onchange="recalculate (theForm)">&nbsp;</td>
      <td><input type="hidden" value="" id="Level0StartTime" onchange="recalculate (theForm)">&nbsp;</td>
      <td><input type="hidden" value="" id="Level0EndDate" onchange="recalculate (theForm)">&nbsp;</td>
      <td><input type="hidden" value="" id="Level0EndTime" onchange="recalculate (theForm)">&nbsp;</td>
     </tr>
     <tr>
      <td>1</td>
      <td><div id="Level1Users"/></td>
      <td><div id="Level1Loosers"/></td>
      <td><input id="Level1Percent" onchange="recalculate (theForm)" onkeypress="javascript: return checkTBKeyPress(this,'integer',10,0, event)"></td>
      <td><input id="Level1Amount" onchange="recalculate (theForm)" onkeypress="javascript: return checkTBKeyPress(this,'integer',10,0, event)"></td>
      <td><div id="Level1PayoutPercent"/></td>
      <td><div id="Level1PayoutAmount"/></td>
      <td><div id="Level1TotalPercent"/></td>
      <td><div id="Level1TotalAmount"/></td>
      <td><input name="level1StartDate" id="Level1StartDate" onchange="recalculate (theForm)" class="date-pick" readonly="readonly"></td>
      <td><input name="level1StartTime" id="Level1StartTime" onchange="recalculate (theForm)" class="clock" readonly="readonly"></td>
      <td><input name="level1EndDate"id="Level1EndDate" onchange="recalculate (theForm)" class="date-pick" readonly="readonly"></td>
      <td><input name="level1EndTime"id="Level1EndTime" onchange="recalculate (theForm)" class="clock" readonly="readonly"></td>
     </tr>
     <tr>
      <td>2</td>
      <td><div id="Level2Users"/></td>
      <td><div id="Level2Loosers"/></td>
      <td><input id="Level2Percent" onchange="recalculate (theForm)" onkeypress="javascript: return checkTBKeyPress(this,'integer',10,0, event)"></td>
      <td><input id="Level2Amount" onchange="recalculate (theForm)" onkeypress="javascript: return checkTBKeyPress(this,'integer',10,0, event)"></td>
      <td><div id="Level2PayoutPercent"/></td>
      <td><div id="Level2PayoutAmount"/></td>
      <td><div id="Level2TotalPercent"/></td>
      <td><div id="Level2TotalAmount"/></td>
      <td><input name="level2StartDate" id="Level2StartDate"  class="date-pick" readonly="readonly"></td>
      <td><input name="level2StartTime" id="Level2StartTime" onchange="recalculate (theForm)" class="clock" readonly="readonly"></td>
      <td><input name="level2EndDate" id="Level2EndDate" onchange="recalculate (theForm)" class="date-pick" readonly="readonly"></td>
      <td><input name="level2EndTime" id="Level2EndTime" onchange="recalculate (theForm)" class="clock" readonly="readonly"></td>
     </tr>
     <tr>
      <td>3</td>
      <td><div id="Level3Users"/></td>
      <td><div id="Level3Loosers"/></td>
      <td><input id="Level3Percent" onchange="recalculate (theForm)" onkeypress="javascript: return checkTBKeyPress(this,'integer',10,0, event)"></td>
      <td><input id="Level3Amount" onchange="recalculate (theForm)" onkeypress="javascript: return checkTBKeyPress(this,'integer',10,0, event)"></td>
      <td><div id="Level3PayoutPercent"/></td>
      <td><div id="Level3PayoutAmount"/></td>
      <td><div id="Level3TotalPercent"/></td>
      <td><div id="Level3TotalAmount"/></td>
      <td><input name="level3StartDate"  id="Level3StartDate" onchange="recalculate (theForm)" class="date-pick" readonly="readonly"></td>
      <td><input name="level3StartTime" id="Level3StartTime" onchange="recalculate (theForm)" class="clock" readonly="readonly"></td>
      <td><input name="level3EndDate" id="Level3EndDate" onchange="recalculate (theForm)" class="date-pick" readonly="readonly"></td>
      <td><input name="level3EndTime" id="Level3EndTime" onchange="recalculate (theForm)" class="clock" readonly="readonly"></td>
     </tr>
     <tr>
      <td>4</td>
      <td><div id="Level4Users"/></td>
      <td><div id="Level4Loosers"/></td>
      <td><input id="Level4Percent" onchange="recalculate (theForm)" onkeypress="javascript: return checkTBKeyPress(this,'integer',10,0, event)"></td>
      <td><input id="Level4Amount" onchange="recalculate (theForm)" onkeypress="javascript: return checkTBKeyPress(this,'integer',10,0, event)"></td>
      <td><div id="Level4PayoutPercent"/></td>
      <td><div id="Level4PayoutAmount"/></td>
      <td><div id="Level4TotalPercent"/></td>
      <td><div id="Level4TotalAmount"/></td>
      <td><input name="level4StartDate" id="Level4StartDate" onchange="recalculate (theForm)" class="date-pick" readonly="readonly"></td>
      <td><input name="level4StartTime" id="Level4StartTime" onchange="recalculate (theForm)" class="clock" readonly="readonly"></td>
      <td><input name="level4EndDate" id="Level4EndDate" onchange="recalculate (theForm)" class="date-pick" readonly="readonly"></td>
      <td><input name="level4EndTime" id="Level4EndTime" onchange="recalculate (theForm)" class="clock" readonly="readonly"></td>
     </tr>
     <tr>
      <td>5</td>
      <td><div id="Level5Users"/></td>
      <td><div id="Level5Loosers"/></td>
      <td><input id="Level5Percent" onchange="recalculate (theForm)" onkeypress="javascript: return checkTBKeyPress(this,'integer',10,0, event)"></td>
      <td><input id="Level5Amount" onchange="recalculate (theForm)" onkeypress="javascript: return checkTBKeyPress(this,'integer',10,0, event)"></td>
      <td><div id="Level5PayoutPercent"/></td>
      <td><div id="Level5PayoutAmount"/></td>
      <td><div id="Level5TotalPercent"/></td>
      <td><div id="Level5TotalAmount"/></td>
      <td><input name="level5StartDate" id="Level5StartDate" onchange="recalculate (theForm)" class="date-pick" readonly="readonly"></td>
      <td><input name="level5StartTime" id="Level5StartTime" onchange="recalculate (theForm)" class="clock" readonly="readonly"></td>
      <td><input name="level5EndDate" id="Level5EndDate" onchange="recalculate (theForm)" class="date-pick" readonly="readonly"></td>
      <td><input name="level5EndTime" id="Level5EndTime" onchange="recalculate (theForm)" class="clock" readonly="readonly"></td>
     </tr>
     <tr>
      <td>6</td>
      <td><div id="Level6Users"/></td>
      <td><div id="Level6Loosers"/></td>
      <td><input id="Level6Percent" onchange="recalculate (theForm)" onkeypress="javascript: return checkTBKeyPress(this,'integer',10,0, event)"></td>
      <td><input id="Level6Amount" onchange="recalculate (theForm)" onkeypress="javascript: return checkTBKeyPress(this,'integer',10,0, event)"></td>
      <td><div id="Level6PayoutPercent"/></td>
      <td><div id="Level6PayoutAmount"/></td>
      <td><div id="Level6TotalPercent"/></td>
      <td><div id="Level6TotalAmount"/></td>
      <td><input name="level6StartDate" id="Level6StartDate" onchange="recalculate (theForm)" class="date-pick" readonly="readonly"></td>
      <td><input name="level6StartTime" id="Level6StartTime" onchange="recalculate (theForm)" class="clock" readonly="readonly"></td>
      <td><input name="level6EndDate" id="Level6EndDate" onchange="recalculate (theForm)" class="date-pick" readonly="readonly"></td>
      <td><input name="level6EndTime" id="Level6EndTime" onchange="recalculate (theForm)" class="clock" readonly="readonly"></td>
     </tr>
    </tbody>
   </table>
   <br/>
   
   
   
   <html:submit property="operation" value="Go"  />
   <html:hidden property="levels"/>  
   <html:hidden property="houseFeePerPlayer"/>
   <html:hidden property="level1Amount" styleId="hfLevel1Amount"/>
   <input type="hidden" id="hfLevel1StartDate" />   
   <input type="hidden" id="hfLevel1StartTime" />   
   <input type="hidden" id="hfLevel1EndDate" />   
   <input type="hidden" id="hfLevel1EndTime" />   
   <input type="hidden" id="hfLevel2Amount" name="level2Amount" value=""/>  
   <input type="hidden" id="hfLevel2StartDate"/>   
   <input type="hidden" id="hfLevel2StartTime"/>   
   <input type="hidden" id="hfLevel2EndDate" />   
   <input type="hidden" id="hfLevel2EndTime" />   
   <input type="hidden" id="hfLevel3Amount" name="level3Amount" value=""/>  
   <input type="hidden" id="hfLevel3StartDate" />   
   <input type="hidden" id="hfLevel3StartTime" />   
   <input type="hidden" id="hfLevel3EndDate" />   
   <input type="hidden" id="hfLevel3EndTime" />   
   <input type="hidden" id="hfLevel4Amount" name="level4Amount" value=""/>  
   <input type="hidden" id="hfLevel4StartDate" />   
   <input type="hidden" id="hfLevel4StartTime" />   
   <input type="hidden" id="hfLevel4EndDate" />   
   <input type="hidden" id="hfLevel4EndTime" />   
   <input type="hidden" id="hfLevel5Amount" name="level5Amount" value=""/>  
   <input type="hidden" id="hfLevel5StartDate" />   
   <input type="hidden" id="hfLevel5StartTime" />   
   <input type="hidden" id="hfLevel5EndDate" />   
   <input type="hidden" id="hfLevel5EndTime" />   
   <input type="hidden" id="hfLevel6Amount" name="level6Amount" value=""/>  
   <input type="hidden" id="hfLevel6StartDate" />   
   <input type="hidden" id="hfLevel6StartTime" />   
   <input type="hidden" id="hfLevel6EndDate" />   
   <input type="hidden" id="hfLevel6EndTime" />   
  </html:form>
 </body>