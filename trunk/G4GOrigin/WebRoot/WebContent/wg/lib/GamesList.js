//
//	GamesList.js - Games List Swisher Object
//
//	Object is initialized after document ready so that all markup is loaded.
//
var GamesList = {
	slideAmount: 	588,			// width of the view port	
	slideSize:	 	21,				// amount to move each time tick
	slideTimeOut:	30,				// wait time in ms before next move
	listId:			"GamesList",
	pageId:			"GamesListPage",
	navId:			"GamesListPageNav",
	leftStart:		"589px",
	rightStart:		"-589px",
	
	slideChunk:		0,				// current chunk left = -, right = +
	slideCount:		0,				// count for current slide
	curPage: 		0,
	newPage: 		0,
	aPages: 		null,			// array to hold page object references
	bSlideing:		false,			// semifore to keep from double clicking
	
	init:	function() {
		GamesList.aPages = $("."+GamesList.pageId).get();
		GamesList.curPage = $("#"+GamesList.listId).attr("curPage");
		
		if (isNaN(GamesList.curPage) || GamesList.curPage =="") {
			GamesList.curPage = 1;
			GamesList.setCurPage();
		}
		
		for (var i=0; i<GamesList.aPages.length; i++) {
			var pageNum = GamesList.aPages[i].id.replace(/GamesListPage/, "");
			if (pageNum != GamesList.curPage)
				$(GamesList.aPages[i]).css("left","589px");
		}
		$("#"+GamesList.navId+GamesList.curPage).addClass("on");
		$(".GamesListPage").css("display","block");
	
		//console.log.log("GamesList.init: "+GamesList.aPages.length+" pages initialized, curPage = "+GamesList.curPage);
	},
	
	jump:	function(toPage) {
		if (GamesList.sliding())
			return;
		
		GamesList.curPage = GamesList.getCurPage();
		toPage = toPage.id.replace(GamesList.navId, "");
		GamesList.newPage = toPage;
		
		if (GamesList.curPage == toPage) {
			GamesList.notSliding();
			return;
		}
		
		if (toPage > GamesList.curPage)
			GamesList.setupRight();
		else
			GamesList.setupLeft();
				
		//console.log.log("GamesList.jump: "+GamesList.curPage+":"+GamesList.newPage+":"+GamesList.slideCount+":"+GamesList.slideChunk);

		GamesList.slide();
	},
	
	left:	function() {
		if (GamesList.sliding())
			return;

		GamesList.curPage = GamesList.getCurPage();
		GamesList.newPage = (GamesList.curPage==1?GamesList.aPages.length:(parseInt(GamesList.curPage) - 1));

		GamesList.setupLeft();
				
		//console.log.log("GamesList.left: "+GamesList.curPage+":"+GamesList.newPage+":"+GamesList.slideCount+":"+GamesList.slideChunk);
		
		GamesList.slide();
	},
	
	right:	function() {
		if (GamesList.sliding())
			return;

		GamesList.curPage		= GamesList.getCurPage();
		GamesList.newPage		= (GamesList.curPage==GamesList.aPages.length?1:(parseInt(GamesList.curPage) + 1));		
		
		GamesList.setupRight();
				
		//console.log.log("GamesList.right: "+GamesList.curPage+":"+GamesList.newPage+":"+GamesList.slideCount+":"+GamesList.slideChunk);
		
		GamesList.slide();
	},
	
	slide:	function() {
		if ((GamesList.slideChunk < 0 && GamesList.slideCount <= 0) ||
			(GamesList.slideChunk > 0 && GamesList.slideCount >= GamesList.slideAmount)) {
			GamesList.updatePageNav();
			GamesList.notSliding();
			return;
		}

		var oCurPage 	= document.getElementById(GamesList.pageId+GamesList.curPage);
		var oNewPage 	= document.getElementById(GamesList.pageId+GamesList.newPage);

		var curPageLeft = parseInt($(oCurPage).css("left")) + GamesList.slideChunk;
		var newPageLeft = parseInt($(oNewPage).css("left")) + GamesList.slideChunk;
				
		$(oCurPage).css({
			left:	curPageLeft +"px"
		});
		$(oNewPage).css({
			left:	newPageLeft +"px"
		});
		
		GamesList.slideCount += GamesList.slideChunk;
		
		setTimeout(GamesList.slide, GamesList.slideTimeOut);

//		//console.log.log("GamesList.slide: slideCount: "+GamesList.slideCount);		
	},
	
	sliding:		function() {
		if (GamesList.bSliding) {
			//console.log.log("GamesList.sliding: blocked");
			return true;
		} else {
			GamesList.bSliding = true;
			//console.log.log("GamesList.sliding: not-blocked, setting semaphore.");
			return false;
		}
	},
	
	notSliding:		function() {
		if (GamesList.bSliding) {
			GamesList.bSliding = false;
			//console.log.log("GamesList.notSliding: un-setting semaphore.");
			return true;
		} else {
			//console.log.log("GamesList.notSliding: semaphore was not set.");
			return false;
		}
			
	},
	
	setupLeft:		function() {
		GamesList.slideCount	= 0,
		GamesList.slideChunk	= GamesList.slideSize;
		$("#"+GamesList.pageId+GamesList.newPage).css("left", GamesList.rightStart);
	},
	
	setupRight:		function() {
		GamesList.slideCount = GamesList.slideAmount;
		GamesList.slideChunk = - GamesList.slideSize;
		$("#"+GamesList.pageId+GamesList.newPage).css("left", GamesList.leftStart);		
	},
	
	updatePageNav:	function() {
		$("#"+GamesList.navId+GamesList.curPage).removeClass("on");
		$("#"+GamesList.navId+GamesList.newPage).addClass("on");
		GamesList.curPage = GamesList.newPage;
		GamesList.setCurPage();
		GamesList.newPage = 0;
	},
	
	getCurPage:	function() {
		GamesList.curPage = $("#"+GamesList.listId).attr("curPage");
		return GamesList.curPage;
	},
	
	setCurPage:	function() {
		$("#"+GamesList.listId).attr({
			curPage:	GamesList.curPage
		});
	}
};

$(document).ready(GamesList.init);