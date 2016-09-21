(function($) {
	$.fn.hideShowPlugin = function() {
		var dts = $(this).children('dt');
		dts.next().hide();
		dts.click(onClick);
		
		function onClick() {
			$(this).next().slideToggle('fast');
			return false;
		}
	};
	
	$.fn.addFieldPlugin = function() {
		$(this).click(onClick);

		function onClick() {
			$(".intermediate-stations").append("<tr><td><input class='input-search' name='s' required list='stations' placeholder='название станции' type='search'/></td>" +
					"<td><input class='input-small' name='d' type='search' value='30' placeholder='время в минутах'/></td></tr>");
			return false;
		}
	};
	
	
	$.fn.removeFieldPlugin = function() {
		$(this).click(onClick);

		function onClick() {
			$(".intermediate-stations").find("tbody > tr:last-child").remove();
			return false;
		}
	};
})(jQuery);