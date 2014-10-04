$(function() {

	$(".app-pager-link").each(function(index) {
		var current = $(".app-page-current", this).attr("title");
		$("li", this).each(function() {
			if (this.title == current) {
				if ($(this).hasClass("edge")) {
					$(this).addClass("disabled");
				} else {
					$(this).addClass("active");
				}
				$("a", this).click(function() {
					return false;
				})
			} else {
				var pageNo = this.title - 1;
				$("a", this).click(function() {
					var form = $(".app-pager-form");
					$("input.app-page-no", form).val(pageNo);
					form.submit();
					return false;
				});
			}
		})
	});

});
