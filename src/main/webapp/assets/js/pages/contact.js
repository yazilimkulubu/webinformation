var Contact = function () {

    return {
        
        //Map
        initMap: function () {
			var map;
			$(document).ready(function(){
			  map = new GMaps({
				div: '#map',
				lat: 38.672351,
				lng: 39.188844
			  });
			   var marker = map.addMarker({
		            lat: 38.672351,
					lng: 39.188844,
		            title: 'Loop, Inc.'
		        });
			});
        }

    };
}();