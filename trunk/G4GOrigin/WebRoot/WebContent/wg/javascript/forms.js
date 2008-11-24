		function switchTab(side, onid, offid)
		{
			document.getElementById( side + '-tabcontent' + onid ).style.display = 'block';
			document.getElementById( side + '-tabcontent' + offid ).style.display = 'none';
			document.getElementById( side + '-tab' + onid ).className = 'active';
			document.getElementById( side + '-tab' + offid ).className = '';
		}
		
		var opened;
		var opener;
		
		function toggleLayer(refr, target)
		{
			if(document.getElementById( target ).style.display != 'block')
			{
				if(opened && opened != target)
				{
					opener.parentNode.className = 'btn-topbar';
					document.getElementById( opened ).style.display = 'none';
				}
				refr.parentNode.className = 'btn-topbar active';
				document.getElementById( target ).style.display = 'block';
				opener = refr;
				opened = target;
			}
			else
			{
				refr.parentNode.className = 'btn-topbar';
				document.getElementById( target ).style.display = 'none';
			}
		}
		
		var previous;
		
		function toggleElmGroup(caller, prefix, cur, canclose, icon)
		{
			if( icon == 'indicator' && !previous)
			{
				var alist = document.getElementsByTagName( 'h5' );
				for( var k = 0; k < alist.length; k++ )
				{
					if( alist[k].className == 'collapse-rarrow' )
						alist[k].className = '';
				}
			}

			var count = 1;
			while( document.getElementById( prefix + count ) )
				count++;
				
			if( document.getElementById( prefix + cur ).style.display == 'block' && canclose == true )
				var closecur = true;
			else
				var closecur = false;

			for( var i = 1; i < count; i++ )
				document.getElementById( prefix + i ).style.display = 'none';
	
			if( closecur == false )
				document.getElementById( prefix + cur ).style.display = 'block';
				
			if( icon )
			{
				if( previous && caller != previous )
					icon == 'onoff'? previous.className = 'collapse-plus': previous.className = '';
				previous = caller;
				
				if( caller.className == 'collapse-plus' || caller.className == 'collapse-rarrow' )
					icon == 'onoff'? caller.className = 'collapse-minus': previous.className = 'collapse-rarrow';
				else
					icon == 'onoff'? caller.className = 'collapse-plus': previous.className = 'collapse-rarrow';
			}
		}
		
		function linkblur() {
			var links = document.getElementsByTagName( 'a' );
			if( links.length > 0 ) {
				for( var i = 0; i < links.length; i++ ) {
					links[ i ].onfocus = function() {
						this.blur();
					}
				}
			}
		}

		onload = linkblur;
		