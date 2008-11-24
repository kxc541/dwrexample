function popUp(URL) {
    window.open(URL, "PopUp", "toolbar=0,scrollbars=yes,location=0,statusbar=0,menubar=0,resizable=yes,width=500,height=500");
}
    
function popUp(URL, target, height, width) {
    window.open(URL, target, "toolbar=0,scrollbars=0,location=0,statusbar=0,menubar=0,resizable=0,width="+width+",height="+height);
}
    
function popUp(URL, target, height, width, sbflag) {
    window.open(URL, target, "toolbar=0,scrollbars="+sbflag+",location=0,statusbar=0,menubar=0,resizable=0,width="+width+",height="+height);
}
    
function popUpSeatingChart(URL) {
    window.open(URL, "SeatingChart", "toolbar=0,scrollbars=0,location=0,statusbar=0,menubar=0,resizable=0,width=500,height=530");
}
    
