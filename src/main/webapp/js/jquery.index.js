(function($) {
	$.fn.hideShowPlugin = function() {
		var dts = $(this).children('dt');
		dts.click(onClick);
		dts.each(reset);
		$(this).children('dd:last-child').show();
		
		function onClick() {
			$(this).next().slideToggle('fast');
			return false;
		}
		
		function reset() {
			$(this).next().hide();
		}		
	};
	
	$.fn.addFieldPlugin = function() {
		$(this).click(onClick);

		function onClick() {
			$(".intermediate-stations").append("<tr><td><input class='input-search' name='s' required list='stations' placeholder='название станции' type='search'/></td>" +
					"<td><input class='input-small' name='d' type='search' placeholder='время в минутах'/></td></tr>");
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