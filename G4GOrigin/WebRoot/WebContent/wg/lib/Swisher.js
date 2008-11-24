//
//	Swisher.js - generic swisher object
//
function Swisher(swisherId) {
	this.length 		= 31;
	//
	//	swisher attributes
	//
	this.instance		= ++SwisherClass.instances;
	SwisherClass.aSwishers[this.instance] = this;
	
	this.swisherId		= swisherId;
	this.pageId 		= this.swisherId+"Page";
	this.navId 			= this.pageId+"Nav";
	this.size 			= parseInt($("."+this.swisherId+"PageContainer").css("width"));
	this.leftStart 		= parseInt(this.size) + 1 +"px";
	this.rightStart 	= "-"+this.leftStart;
	this.slideAmount	= parseInt(this.size);
	this.slideSize		= 21;
	this.slideTimeout	= 30;
	this.slideChunk 	= 0;
	this.slideCount 	= 0;
	this.curPage		= $("#"+this.swisherId).attr("curPage");
	this.newPage		= 0;
	this.aPages			= $("."+this.pageId).get();
	this.bSliding		= false;
	this.rePageId		= new RegExp(this.pageId);
	//
	//	swisher methods
	//
	this.debug			= SwisherClass.debug;
	this.log			= SwisherClass.log;
	this.jump			= SwisherClass.jump;
	this.left			= SwisherClass.left;
	this.right			= SwisherClass.right;
	this.slide			= SwisherClass.slide;
	this.sliding		= SwisherClass.sliding;
	this.notSliding		= SwisherClass.notSliding;
	this.setupLeft		= SwisherClass.setupLeft;
	this.setupRight		= SwisherClass.setupRight;
	this.updatePageNav	= SwisherClass.updatePageNav;
	this.getCurPage		= SwisherClass.getCurPage;
	this.setCurPage		= SwisherClass.setCurPage;
	//
	// debug
	//

	//
	// setup DOM object for swishing
	//
	for (var i=0; i<this.aPages.length; i++) {
		var pageNum = this.aPages[i].id.replace(this.rePageId, "");
		
		if (pageNum != this.curPage) {
			$(this.aPages[i]).css("left", this.leftStart);
			
		}
	}
	$("#"+this.navId+this.curPage).addClass("on");
	$("."+this.pageId).css("display", "block");
	//console.log("Swisher("+this.swisherId+"): "+this.aPages.length+" pages initialized, curPage = "+this.curPage);
}

var SwisherClass = {
	aSwishers:		new Array(0),
	instances:		0,
	
	debug:			function() {
		//console.log("Swisher("+this.swisherId+").debug: "+this.slide);
	},
	
	log:			function(msg) {
		//console.log("Swisher("+this.swisherId+")"+msg);
	},
	
	jump:			function(toPage) {
		if (this.sliding())
			return;
		
		this.curPage = this.getCurPage();
		toPage = toPage.id.replace(this.navId, "");
		this.newPage = toPage;
		
		if (this.curPage == toPage) {
			this.notSliding();
			return;
		}
		
		if (toPage > this.curPage)
			this.setupRight();
		else
			this.setupLeft();
				
		//console.log("Swisher("+this.swisherId+").jump: "+this.curPage+":"+this.newPage+":"+this.slideCount+":"+this.slideChunk);

		this.slide(this);
	},
	
	left:			function() {
		if (this.sliding())
			return;

		this.curPage = this.getCurPage();
		this.newPage = (this.curPage==1?this.aPages.length:(parseInt(this.curPage) - 1));

		this.setupLeft();
				
		//console.log("Swisher("+this.swisherId+").left: "+this.curPage+":"+this.newPage+":"+this.slideCount+":"+this.slideChunk);
		
		this.slide(this);		
	},
	
	right:			function() {
		if (this.sliding())
			return;
			
		this.curPage		= this.getCurPage();
		this.newPage		= (this.curPage==this.aPages.length?1:(parseInt(this.curPage) + 1));		
		
		this.setupRight();
				
		//console.log("Swisher("+this.swisherId+").right: "+this.curPage+":"+this.newPage+":"+this.slideCount+":"+this.slideChunk);
		
		this.slide(this);		
	},
	
	slide:			function(myself) {
		if ((myself.slideChunk < 0 && myself.slideCount <= 0) ||
			(myself.slideChunk > 0 && myself.slideCount >= myself.slideAmount)) {
			myself.updatePageNav();
			myself.notSliding();
			return;
		}

		var oCurPage 	= document.getElementById(myself.pageId+myself.curPage);
		var oNewPage 	= document.getElementById(myself.pageId+myself.newPage);

		var curPageLeft = parseInt($(oCurPage).css("left")) + myself.slideChunk;
		var newPageLeft = parseInt($(oNewPage).css("left")) + myself.slideChunk;

		$(oCurPage).css({
			left:	curPageLeft +"px"
		});
		$(oNewPage).css({
			left:	newPageLeft +"px"
		});
		
//		myself.slideCount += parseInt(myself.slideChunk);
		
		// safari fix
		myself.slideCount = parseInt(myself.slideCount) + parseInt(myself.slideChunk);
		
		setTimeout(function() {myself.slide(myself)}, myself.slideTimeout);

		//console.log("Swisher("+myself.swisherId+").slide: slideCount: "+myself.slideCount);				
	},
	
	sliding:		function() {
		if (this.bSliding) {
			//console.log("Swisher("+this.swisherId+").sliding: blocked");
			return true;
		} else {
			this.bSliding = true;
			//console.log("Swisher("+this.swisherId+").sliding: not-blocked, setting semaphore.");
			return false;
		}		
	},
	
	notSliding:		function() {
		if (this.bSliding) {
			this.bSliding = false;
			//console.log("Swisher("+this.swisherId+").notSliding: un-setting semaphore.");
			return true;
		} else {
			//console.log("Swisher("+this.swisherId+").notSliding: semaphore was not set.");
			return false;
		}		
	},
	
	setupLeft:		function() {
		this.slideCount	= 0,
		this.slideChunk	= this.slideSize;
		$("#"+this.pageId+this.newPage).css("left", this.rightStart);		
	},
	
	setupRight:		function() {
		this.slideCount = this.slideAmount;
		this.slideChunk = - this.slideSize;
		$("#"+this.pageId+this.newPage).css("left", this.leftStart);				
	},
	
	updatePageNav:	function() {
		$("#"+this.navId+this.curPage).removeClass("on");
		$("#"+this.navId+this.newPage).addClass("on");
		this.curPage = this.newPage;
		this.setCurPage();
		this.newPage = 0;		
	},
	
	getCurPage:		function() {
		this.curPage = $("#"+this.swisherId).attr("curPage");
		return this.curPage;		
	},
	
	setCurPage:		function() {
		$("#"+this.swisherId).attr({
			curPage:	this.curPage
		});		
	}
};

//console.log("Swisher.js loaded.");