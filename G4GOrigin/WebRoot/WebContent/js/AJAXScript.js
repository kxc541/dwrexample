

var xmlHttp;
var searchStr = '';
var comboName = "";

function populate1Option(valuesequenceid , gameid , optionsequenceid){
	optionsequenceidnext = (1*optionsequenceid+1).toString();
	optionsequenceidprev = (1*optionsequenceid-1);
	try{
		// test for existence of next element so as save a server req.
		document.getElementById('gameOptionSelected'+optionsequenceidnext);
		
		if(document.getElementById('gameOptionSelected'+optionsequenceid).value==""){
		document.getElementById('gameOptionSelected'+(1*optionsequenceidnext)).value='';
		document.getElementById('gameOptionSelected'+(1*optionsequenceidnext+1)).value='';
			document.getElementById('gameOptionSelected'+(1*optionsequenceidnext)).disabled = true;
			document.getElementById('gameOptionSelected'+(1*optionsequenceidnext+1)).disabled = true;
		}else{
		// test over
		var url = 'ajax/getNextGameOptionsXML.do?valuesequenceid=' + valuesequenceid+'&gameid=' + gameid +'&optionsequenceid='+optionsequenceid ;
		while(optionsequenceidprev>0){
		url = url + "&valuesequenceid=" + document.getElementById('gameOptionSelected'+optionsequenceidprev).value;
		optionsequenceidprev--;
		}
		xmlHttp=GetXmlHttpObject(function(){populateNextOption(optionsequenceidnext,gameid)});
		xmlHttp.open('GET', url , true);
		xmlHttp.send(null);
		}
	}catch(exception){
		// element does not exists
	}
}

function populateNextOption( optionsequenceidnext , gameid){
	if (xmlHttp.readyState==4 || xmlHttp.readyState=='complete') {
    	var xmldoc = xmlHttp.responseXML.documentElement;
    	var size = xmldoc.getElementsByTagName('gameoption').length;
    	for(var i=0;i<size;i++){
    	var nextOption = document.getElementById('gameOptionSelected' + optionsequenceidnext);
		nextOption.disabled = false
		nextOption.options.length = 0;
    	var root = xmldoc.getElementsByTagName('gameoption').item(i);
  		//nextOption.options[0]=new Option('<Choose a game option>','');
  		for (var iNode = 0; iNode < root.childNodes.length; iNode++) {
  		          var node = root.childNodes.item(iNode);
                  nextOption.options[iNode] = new Option(getData(node, 'value'), getData(node, 'key'));
	    }
	    optionsequenceidnext= optionsequenceidnext*1 + 1;
    	}
  		
	    
	    /*if(nextOption.options.length==1 && (gameid==33 || gameid==19 ||gameid==39 )){
	    		var nextOptions = document.getElementById('gameOptionSelected' + (1*optionsequenceidnext+1));
	    	 	nextOptions.value=1;
	    		if(nextOptions.length==4){
	    		nextOptions.options.length=2;
	    		nextOptions.options[0]=null;
	    		}else nextOptions.options.length=1;
	    }*/
	    
	}
}

function retriveGameOptions(gameId){
	var url = 'ajax/getGameOptionsXML.do?gameId=' + gameId;
	xmlHttp=GetXmlHttpObject(createGameOptions);
	xmlHttp.open('GET', url , true);
	xmlHttp.send(null);
	
}

function createGameOptions(){
	var gameOptionDiv = document.getElementById('Gameoptions');
		gameOptionBody = "<table>";
		if (xmlHttp.readyState==4 || xmlHttp.readyState=='complete'){
	    	var xmldoc = xmlHttp.responseXML;
	  		var root = xmldoc.getElementsByTagName('Gameoptions').item(0);
	  		for (var iNode = 0; iNode < root.childNodes.length; iNode++) {
	  		       var node = root.childNodes.item(iNode);
	               // print node.tagName This is game option type
	               gameOptionBody = gameOptionBody +"<tr>";
	               gameOptionBody = gameOptionBody +"<td>";
	               var tagg = node.tagName.replace('88888888',' ');
	               gameOptionBody = gameOptionBody + tagg;
	               gameOptionBody = gameOptionBody +"</td>";
	               gameOptionBody = gameOptionBody +"<td>";
	               // for having select box
	               gameOptionBody = gameOptionBody +'<input type="hidden" name="gameOptions" value="'+node.tagName+'">';
	               gameOptionBody = gameOptionBody +"<select name='gameOptionSelected'>"; 
	               for (i = 0; i < node.childNodes.length; i=i+1) {
	                  var sibl1 = node.childNodes.item(i);	
	                  gameOptionBody = gameOptionBody +"<option value='"+ getData(sibl1,'valuesequenceid')+ "'>" +getData(sibl1,'labelvalue') +"</option>";
	               }
	               gameOptionBody = gameOptionBody +"</select>";
	               gameOptionBody = gameOptionBody +"</td>";
	               gameOptionBody = gameOptionBody +"</tr>";
	    }
        gameOptionBody= gameOptionBody+ "</table>";
        if(gameOptionBody == '<table></table>'){gameOptionBody = 'no game options available for this game'};
       	gameOptionDiv.innerHTML = gameOptionBody;
	}
	
	
}











function getData( myNode, myNodeName )
{
    var subNodes = myNode.childNodes;

    for (var j=0; j < subNodes.length; j=j+1 )
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
	var objXmlHttp=null;

	if (navigator.userAgent.indexOf('Opera')>=0)
	{
		alert('This example doesn\'t work in Opera'); 
		return ;
	}

	if (navigator.userAgent.indexOf('MSIE')>=0)
	{ 
		var strName='Msxml2.XMLHTTP';
		if (navigator.appVersion.indexOf('MSIE 5.5')>=0)
		{
			strName='Microsoft.XMLHTTP';
		} 
		try
		{ 
			objXmlHttp=new ActiveXObject(strName);
			objXmlHttp.onreadystatechange=handler ;
			return objXmlHttp;
		} 
		catch(e)
		{ 
			alert('Error. Scripting for ActiveX might be disabled '); 
			return ;
		} 
	} 
	
	if (navigator.userAgent.indexOf('Mozilla')>=0)
	{
		objXmlHttp=new XMLHttpRequest();
		objXmlHttp.onload=handler;
		objXmlHttp.onerror=handler ;
		return objXmlHttp;
	}
} 
