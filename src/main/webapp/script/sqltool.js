$(function() {

	$(".pager-link").each(function(index) {
		var form = $(this).attr("data-form");
		var pno = $(this).attr("data-pno");
		var current = $(this).attr("data-current");
		$("li", this).each(function() {
			var pageNo = $("a", this).attr("title");
			if (pageNo == current) {
				if ($(this).hasClass("edge")) {
					$(this).addClass("disabled");
				} else {
					$(this).addClass("active");
				}
				$("a", this).removeAttr("href");
			} else {
				$("a", this).click(function(event) {
					event.preventDefault();
					$("input[name='" + pno + "']", $(form)).val(pageNo - 1);
					$(form).submit();
				});
			}
		})
	});

});
