var xmpHttp;
var nextPage = 0;
var prevPage = 0;
var pageStartsAt = 0;
var maximumPage = 0;
var searchStr = '';
var pageSize = 5;
var comboName = "";

//For pagination of people searchDIV

function retrivePeoplePage(pageNo, maxPage, searchString, allPlayers){

	searchStr = searchString
	maximumPage = maxPage;
	if(pageNo <= maxPage - 1) {
		prevPage = pageNo - 1;
		nextPage = pageNo + 1;
	}

	if(prevPage == -1) prevPage = 0;
	if(nextPage == maxPage) nextPage = maxPage - 1;
	pageStartsAt = pageNo * pageSize;

	var url = 'ajax/getSerachPeopleResults.do?peoplePageStartsAt='+pageStartsAt+'&searchString='+searchStr+'&nextPeoplePage='+nextPage+'&prevPeoplePage='+prevPage+'&maxPeoplePages='+maxPage+'&allPlayers='+allPlayers+'&showAllPeople=false';
	xmlHttp = GetXmlHttpObject(fillPeopleSearchDiv);
	xmlHttp.open('GET',url,true);
	xmlHttp.send(null)
}

// For Displaying all the people (View all people) Starts....

function retriveAllPeople(searchString, allPlayers){
	searchStr = searchString
	var url = 'ajax/getSerachPeopleResults.do?searchString='+searchStr+'&allPlayers='+allPlayers+'&showAllPeople=true';
	xmlHttp = GetXmlHttpObject(fillPeopleSearchDiv);
	xmlHttp.open('GET',url,true);
	xmlHttp.send(null)
}
// Ends.....
function fillPeopleSearchDiv(){

	var searchDiv = document.getElementById('searchPeopleDiv');

	if(xmlHttp.readyState == 4 || xmlHttp.readyState == 'complete'){
		searchDiv.innerHTML = xmlHttp.responseText;
	}
}

//For pagination of games searchDIV

function retriveGamePage(pageNo, maxPage, searchString, allGames){
	searchStr = searchString
	maximumPage = maxPage;
	if(pageNo <= maxPage - 1) {
		prevPage = pageNo - 1;
		nextPage = pageNo + 1;
	}

	if(prevPage == -1) prevPage = 0;
	if(nextPage == maxPage) nextPage = maxPage - 1;

	pageStartsAt = pageNo * pageSize;

	var url = 'ajax/getSerachGameResults.do?gamePageStartsAt='+pageStartsAt+'&searchString='+searchStr+'&nextGamePage='+nextPage+'&prevGamePage='+prevPage+'&maxGamePages='+maxPage+'&allGames='+allGames+'&showAllGame=false';
	xmlHttp = GetXmlHttpObject(fillGameSearchDiv);
	xmlHttp.open('GET',url,true);
	xmlHttp.send(null)
}
 // For Displaying all the games (View all games) Starts....

function retriveAllGames(searchString, allGames){
	searchStr = searchString
	var url = 'ajax/getSerachGameResults.do?searchString='+searchStr+'&allGames='+allGames+'&showAllGame=true';
	xmlHttp = GetXmlHttpObject(fillGameSearchDiv);
	xmlHttp.open('GET',url,true);
	xmlHttp.send(null)
}
 // Ends.....

function fillGameSearchDiv(){
	var searchDiv = document.getElementById('searchGameDIV');
	if(xmlHttp.readyState == 4 || xmlHttp.readyState == 'complete'){
		searchDiv.innerHTML = xmlHttp.responseText;
	}
}
//For pagination of tournament searchDIV

function retriveTournamentPage(pageNo, maxPage, searchString, allTournaments, offset){
	searchStr = searchString
	maximumPage = maxPage;
	if(pageNo <= maxPage - 1) {
		prevPage = pageNo - 1;
		nextPage = pageNo + 1;
	}

	if(prevPage == -1) prevPage = 0;
	if(nextPage == maxPage) nextPage = maxPage - 1;

	pageStartsAt = pageNo * pageSize;

	var url = 'ajax/getSerachTournamentResults.do?tournamentPageStartsAt='+pageStartsAt+'&searchString='+searchStr+'&nextTournamentPage='+nextPage+'&prevTournamentPage='+prevPage+'&maxTournamentPages='+maxPage+'&allTournaments='+allTournaments+'&showAllTournament=false&userOffset='+offset;
	xmlHttp = GetXmlHttpObject(fillTournamentSearchDiv);
	xmlHttp.open('GET',url,true);
	xmlHttp.send(null)
}
 // For Displaying all the tournaments (View all tournament) Starts....

function retriveAllTournament(searchString, allTournaments, offset){

	searchStr = searchString
	var url = 'ajax/getSerachTournamentResults.do?searchString='+searchStr+'&allTournaments='+allTournaments+'&showAllTournament=true&userOffset='+offset;
	xmlHttp = GetXmlHttpObject(fillTournamentSearchDiv);
	xmlHttp.open('GET',url,true);
	xmlHttp.send(null)
}
 // Ends.....

function fillTournamentSearchDiv(){
	var searchDiv = document.getElementById('searchTournamentDIV');
	if(xmlHttp.readyState == 4 || xmlHttp.readyState == 'complete'){
		searchDiv.innerHTML = xmlHttp.responseText;
	}
}

//For pagination of challenge searchDIV

function retriveChallengePage(pageNo, maxPage, searchString, allOpenChallenges, offset){

	searchStr = searchString
	maximumPage = maxPage;
	if(pageNo <= maxPage - 1) {
		prevPage = pageNo - 1;
		nextPage = pageNo + 1;
	}

	if(prevPage == -1) prevPage = 0;
	if(nextPage == maxPage) nextPage = maxPage - 1;

	pageStartsAt = pageNo * pageSize;


	var url = 'ajax/getSerachChallengeResults.do?openChallengePageStartsAt='+pageStartsAt+'&searchString='+searchStr+'&nextOpenChallengePage='+nextPage+'&prevOpenChallengePage='+prevPage+'&maxOpenChallengePages='+maxPage+'&allOpenChallenges='+allOpenChallenges+'&showAllOpenChallenge=false&userOffset='+offset;
	xmlHttp = GetXmlHttpObject(fillChallengeSearchDiv);
	xmlHttp.open('GET',url,true);
	xmlHttp.send(null)
}
// For Displaying all the open challenges (View all challenge) Starts....

function retriveAllChallenges(searchString, allOpenChallenges, offset){
	searchStr = searchString
	var url = 'ajax/getSerachChallengeResults.do?searchString='+searchStr+'&allOpenChallenges='+allOpenChallenges+'&showAllOpenChallenge=true&userOffset='+offset;
	xmlHttp = GetXmlHttpObject(fillChallengeSearchDiv);
	xmlHttp.open('GET',url,true);
	xmlHttp.send(null)
}
 // Ends.....
function fillChallengeSearchDiv(){
	var searchDiv = document.getElementById('searchChallengeDIV');
	if(xmlHttp.readyState == 4 || xmlHttp.readyState == 'complete'){
		searchDiv.innerHTML = xmlHttp.responseText;
	}
}

function viewAllPastTournament(){
	var url = 'ajax/getAllPastTournaments.do';
	xmlHttp=GetXmlHttpObject(fillAllPastTournament)
	xmlHttp.open('GET', url , true)
	xmlHttp.send(null)
}

function fillAllPastTournament(){
	var pastTournamentDIV = document.getElementById('pastTournamentDIV');
	if(xmlHttp.readyState == 4 || xmlHttp.readyState == 'complete'){
		var pastTournamentBody = "<table class=\"PastTournamentTable\"><thead><tr><th>Console</th><th>Game</th><th>Tournament</th><th>Date</th><th>Players</th><th>Winner</th><th><br><br></th></tr></thead>";
		var xmlDoc = xmlHttp.responseXML.documentElement;
		var root = xmlDoc.getElementsByTagName('pastTournament');
		//alert('tagname :: '+ xmlDoc.childNodes[0].childNodes.length)
		//alert(xmlDoc.childNodes.length)

		//Loop for all past tournaments
		for(var iNode = 0; iNode<root.length; iNode++){
			var node = root.item(iNode);

			pastTournamentBody = pastTournamentBody +"<tr><td>"+getData(node,'networkname')+"</td>";
			pastTournamentBody = pastTournamentBody +"<td>"+getData(node,'gamename')+"</td>";
			pastTournamentBody = pastTournamentBody +"<td>"+getData(node,'tournamentid')+"</td>";
			pastTournamentBody = pastTournamentBody +"<td>"+getData(node,'completiondate')+"</td>";
			pastTournamentBody = pastTournamentBody +"<td>";

			var totalNames = xmlDoc.childNodes[iNode].childNodes[4].childNodes.length;

			for(var indexNode = 0; indexNode<totalNames; indexNode++){
				//alert(xmlDoc.childNodes[iNode].childNodes[4].childNodes[indexNode].firstChild.text);
				//alert(xmlDoc.childNodes[iNode].childNodes[4].childNodes[indexNode].nodeValue)
				//alert(nodeName[indexNode].item(0).nodeValue);
				//var x = node.getElementsByTagName('player').childNodes[indexNode];
				//alert(iNode + ' ' + getData(x, 'name'));
				//var playernode = nodeName1.item(indexNode);
				pastTournamentBody = pastTournamentBody +xmlDoc.childNodes[iNode].childNodes[4].childNodes[indexNode].tagName+"<br>";
			}
			pastTournamentBody = pastTournamentBody +"</td>";
			pastTournamentBody = pastTournamentBody +"<td>"+getData(node,'winnername')+"</td>";
			pastTournamentBody = pastTournamentBody +"</tr>";
		}
		pastTournamentBody = pastTournamentBody +"<tbody></tbody></table>";
		pastTournamentDIV.innerHTML = pastTournamentBody;
	}
}

function retrieveSubNationCode(nationId, idName)
{
    //get the (form based) params to push up as part of the get request
    var url = 'ajax/getSubNationCode.do?nationCodeId=' + nationId
    comboName = idName;
	xmlHttp=GetXmlHttpObject(fillSubNationCombo)
	xmlHttp.open('GET', url , true)
	xmlHttp.send(null)
}

function fillSubNationCombo()
{
	document.getElementById(comboName).options.length = 0;
	var subCategory = document.getElementById(comboName);
	if (xmlHttp.readyState==4 || xmlHttp.readyState=='complete')
    {
    	var xmldoc = xmlHttp.responseXML.documentElement;
  		var root = xmldoc.getElementsByTagName('subnation');
   		//Process each of the elements
   		for (var iNode = 0; iNode < root.length; iNode++)
   		{
   			var node = root.item(iNode);
   			subCategory.options[iNode] = new Option(getData(node, 'name'), getData(node, 'id'));
        }

	}
}


function getData( myNode, myNodeName )
{
    var subNodes = myNode.childNodes;

    for ( j=0; j < subNodes.length; j++ )
    {
        var subNode = subNodes.item(j);

        if ( subNode.nodeName == myNodeName )
        {
            // this is cross browser ( ie and firefox ) compatible
            return subNode.childNodes.item(0).nodeValue;
        }
    }
    return "";
}

function GetXmlHttpObject(handler)
{
	var objXmlHttp=null

	if (navigator.userAgent.indexOf('Opera')>=0)
	{
		alert('This example doesn\'t work in Opera')
		return
	}

	if (navigator.userAgent.indexOf('MSIE')>=0)
	{
		var strName='Msxml2.XMLHTTP'
		if (navigator.appVersion.indexOf('MSIE 5.5')>=0)
		{
			strName='Microsoft.XMLHTTP'
		}
		try
		{
			objXmlHttp=new ActiveXObject(strName)
			objXmlHttp.onreadystatechange=handler
			return objXmlHttp
		}
		catch(e)
		{
			alert('Error. Scripting for ActiveX might be disabled')
			return
		}
	}

	if (navigator.userAgent.indexOf('Mozilla')>=0)
	{
		objXmlHttp=new XMLHttpRequest()
		objXmlHttp.onload=handler
		objXmlHttp.onerror=handler
		return objXmlHttp
	}
}
