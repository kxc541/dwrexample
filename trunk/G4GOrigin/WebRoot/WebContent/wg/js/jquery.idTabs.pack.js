/* idTabs ~ Sean Catchpole - Version 1.0 */
// eval(function(p,a,c,k,e,r){e=function(c){return(c<a?'':e(parseInt(c/a)))+((c=c%a)>35?String.fromCharCode(c+29):c.toString(36))};if(!''.replace(/^/,String)){while(c--)r[e(c)]=k[c]||e(c);k=[function(e){return r[e]}];e=function(){return'\\w+'};c=1};while(c--)if(k[c])p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c]);return p}('(7($){$.F.q=7(){4 s={"b":B,"3":L,"5":B};o(4 i=0;i<t.8;++i){4 n={},a=t[i];M(m a){f"I":$.w(n,a);l;f"v":f"u":n.b=a;l;f"C":n["3"]=a;l;f"7":n.5=a;l};$.w(s,n)}4 j=2;4 e=$("a[@d^=\'#\']",2).5(7(){9($("a.h",j)[0]==2)3 s["3"];4 r="#"+2.d.A(\'#\')[1];4 g=[];4 c=[];$("a",j).z(7(){9(2.d.K(/#/)){g[g.8]=2;c[c.8]="#"+2.d.A(\'#\')[1]}});9(s.5&&!s.5(r,c,j))3 s["3"];o(i y g)$(g[i]).x("h");o(i y c)$(c[i]).J();$(2).H("h");$(r).G();3 s["3"]});4 6;9(m s.b=="v"&&(6=e.k(":E("+s.b+")")).8)6.5();p 9(m s.b=="u"&&(6=e.k("[@d=\'#"+s.b+"\']")).8)6.5();p 9((6=e.k(".h")).8)6.x("h").5();p e.k(":D").5();3 2};$(7(){$(".q").z(7(){$(2).q()})})})(N)',50,50,'||this|return|var|click|test|function|length|if||start|idList|href|list|case|aList|selected||self|filter|break|typeof||for|else|idTabs|id||arguments|string|number|extend|removeClass|in|each|split|null|boolean|first|eq|fn|show|addClass|object|hide|match|false|switch|jQuery'.split('|'),0,{}))

/* idTabs ~ Sean Catchpole - Version 1.0 */ 
 
/* Options (in any order): 
 
 start (number|string) 
    Index number of default tab. ex: idTabs(0) 
    String of id of default tab. ex: idTabs("#tab1") 
    default: class "selected" or index 0 
 
 return (boolean) 
    True - Url will change. ex: idTabs(true) 
    False - Url will not change. ex: idTabs(false) 
    default: false 
 
 click (function) 
    Function will be called when a tab is clicked. ex: idTabs(foo) 
    If the function returns true, idTabs will show/hide content (as usual). 
    If the function returns false, idTabs will not take any action. 
    The function is passed three variables: 
      The id of the element to be shown 
      an array of all id's that can be shown 
      and the element containing the tabs 
*/ 
(function($){ 
  $.fn.idTabs = function(){ 
    //Defaults 
    var s = { "start":null, 
              "return":false, 
              "click":null }; 
 
    //Loop Arguments matching options 
    for(var i=0; i<arguments.length; ++i) { 
      var n = {}, a=arguments[i]; 
      switch(typeof a){ 
        case "object": $.extend(n,a); break; 
        case "number": 
        case "string": n.start = a; break; 
        case "boolean": n["return"] = a; break; 
        case "function": n.click = a; break; 
      }; $.extend(s,n); 
    } 
     
    //Setup Tabs 
    var self = this; //Save scope 
    var list = $("a[@href^='#']",this).click(function(){ 
      if($("a.selected",self)[0]==this) 
        return s["return"]; //return if already selected 
      var id = "#"+this.href.split('#')[1]; 
      var aList = []; //save tabs 
      var idList = []; //save possible elements 
      $("a",self).each(function(){ 
        if(this.href.match(/#/)) { 
          aList[aList.length]=this; 
          idList[idList.length]="#"+this.href.split('#')[1]; 
        } 
      }); 
      if(s.click && !s.click(id,idList,self)) return s["return"]; 
      //Clear tabs, and hide all 
      for(i in aList) $(aList[i]).removeClass("selected"); 
      for(i in idList) $(idList[i]).hide(); 
      //Select clicked tab and show content 
      $(this).addClass("selected"); 
      $(id).show(); 
      return s["return"]; //Option for changing url 
    }); 
 
    //Select default tab 
    var test; 
    if(typeof s.start == "number" && (test=list.filter(":eq("+s.start+")")).length) 
      test.click(); //Select num tab 
    else if(typeof s.start == "string" && (test=list.filter("[@href='#"+s.start+"']")).length) 
      test.click(); //Select tab linking to id 
    else if((test=list.filter(".selected")).length) 
      test.removeClass("selected").click(); //Select tab with class 'selected' 
    else list.filter(":first").click(); //Select first tab 
 
    return this; //Chainable 
  }; 
  $(function(){ $(".idTabs").each(function(){ $(this).idTabs(); }); }); 
})(jQuery)