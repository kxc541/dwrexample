/**
 * Star Rating - jQuery plugin
 *
 * Copyright (c) 2006 Wil Stuckey
 *
 * Dual licensed under the MIT and GPL licenses:
 *   http://www.opensource.org/licenses/mit-license.php
 *   http://www.gnu.org/licenses/gpl.html
 *
 */

/**
 * Create a degradeable star rating interface out of a simple form structure.
 * Returns a modified jQuery object containing the new interface.
 *   
 * @example jQuery('form.rating').rating();
 * @cat plugin
 * @type jQuery 
 *
 */
(function($){ //create local scope
    /**
     * Takes the form element, builds the rating interface and attaches the proper events.
     * @param {Object} $obj
     */
    var buildRating = function($obj){
        var $obj = buildInterface($obj),
            averageIndex = $obj.averageRating[0],
            averagePercent = $obj.averageRating[1],
            $stars = $($obj.children('.star')),
            $cancel = $($obj.end().children('.cancel'));
        $obj.end();
        
        // hover events.
        // and focus events added
        $stars
            .mouseover(function(){
                event.drain();
                event.fill(this);
            })
            .mouseout(function(){
                event.drain();
                event.reset();
            })
            .focus(function(){
                event.drain();
                event.fill(this)
            })
            .blur(function(){
                event.drain();
                event.reset();
            });
        
        // cancel button events
        $cancel
            .mouseover(function(){
                event.drain();
                $(this).addClass('on')
            })
            .mouseout(function(){
                event.reset();
                $(this).removeClass('on')
            })
            .focus(function(){
                event.drain();
                $(this).addClass('on')
            })
            .blur(function(){
                event.reset();
                $(this).removeClass('on')
            });
        
        // click events.
        $cancel.click(function(){
            event.drain();
            averageIndex = 0;
            averagePercent = 0;
            $.post($obj.url, {
                "rating": $(this).children('a')[0].href.split('#')[1] 
            });
            return false;
        });
        $stars.click(function(){
            averageIndex = $stars.index(this) + 1;
            averagePercent = 0;
            $.post($obj.url, {
                "rating": $(this).children('a')[0].href.split('#')[1] 
            });
            return false;
        });
        
        var event = {
            fill: function(el){ // fill to the current mouse position.
                var index = $stars.index(el) + 1;
                $stars
                    .children('a').css('width', '100%').end()
                    .lt(index).addClass('hover').end();
            },
            drain: function() { // drain all the stars.
                $stars
					.filter('.on').removeClass('on').end()
					.filter('.hover').removeClass('hover').end();
            },
            reset: function(){ // Reset the stars to the default index.
                $stars.lt(averageIndex).addClass('on').end();
                var percent = (averagePercent) ? averagePercent * 10 : 0;
                if (percent > 0) {
                    $stars.eq(averageIndex).addClass('on').children('a').css('width', percent + "%").end().end()
                }  
            }
        }        
        event.reset();
        return $obj;
    }
    
    /**
     * Accepts jQuery object containing a form element.
     * Returns the proper div structure for the star interface.
     * 
     * @return jQuery
     * @param {Object} $form
     * 
     */
    var buildInterface = function($form){
        var $container = $(document.createElement('div')).attr({
            "title": $form.title(),
            "class": $form.attr('class')
        });
        $.extend($container, {
            averageRating: $.trim($container.title().split(':')[1]).split('.'),
            url: $form.attr('action')
        });
        var $optionGroup = $form.children('select').children('option');
        for (var i = 0, option; option = $optionGroup[i]; i++){
            var size = $optionGroup.size() - 1;
            if (option.value == "0") {
                $div = $('<div class="cancel"><a href="#0" title="Cancel Rating">Cancel Rating</a></div>');
            } else {
                $div = $('<div class="star"><a href="#' + option.value + '" title="Give it ' + option.value + '/'+ size +'">' + option.value + '</a></div>');
            }
            $container.append($div[0]);                    
        }
        $form.end().end().after($container).remove();
        return $container;
    }
    
    /**
     * Set up the plugin
     */
    $.fn.rating = function(){
        var stack = [];
        this.each(function(){
            var ret = buildRating($(this));
            stack = $.merge(ret, stack);
        });
        return $(stack);
    }
	// fix ie6 background flicker problem.
	if ($.browser.msie == true) {
		document.execCommand('BackgroundImageCache', false, true);
	}
})(jQuery)