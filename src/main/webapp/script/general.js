$(function() {

	$(".app-menu").menu();
	$(".app-button").button();
	$(".app-buttonset").buttonset();
	$("table.app-stripe>tbody>tr:even").addClass("app-even");
	$("table.app-stripe>tbody>tr:odd").addClass("app-odd");

	$(".app-pager-link").each(function(index) {
		var current = $(".app-current", this).attr("title");
		var pages = $(".app-page[title != '" + current + "']", this);
		pages.wrap("<a href='#'></a>").click(function() {
			var form = $(".app-pager-form");
			$("input[name='no']", form).val(this.title - 1);
			form.submit();
			return false;
		});
	});

});
