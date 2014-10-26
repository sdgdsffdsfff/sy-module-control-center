
/**
 * 提供页面的控制，菜单加载功能
 */
$(document).ready(function() {
	
	/**
	 * 标记当前ajax加载是否处于运行状态
	 */
	var ajax_load_running = false;
	
	/**
	 * 使用ajax加载页面
	 */
	var loadPage = function(url, callback) {
		if (ajax_load_running) {
			return;
		}
		ajax_load_running = true;
		$(".control_center_main_body").showLoading();
		$.ajax({
			url : url,
			type : 'get',
			dataType : 'html',
			success : function(html) {
				$(".control_center_main_body").empty().append(html);
				ajax_load_running = false;
				$(".control_center_main_body").hideLoading();
				if (callback && typeof(callback) == "function") {
					callback();
				}
			}, 
			error : function() {
				alert("加载失败。");
				ajax_load_running = false;
				$(".control_center_main_body").hideLoading();
			}
		});
	};
	
	
	// 注册菜单点击
	$(".control_center_menu").click(function() {
		$(this).parents(".navbar").find(".active").removeClass("active");
		$(this).parents("li").addClass("active");
		var linkurl = $(this).attr("linkurl");
		var linktype = $(this).attr("linktype");
		if (linktype == "control-center") {
			loadPage(linkurl);
			$(this).parents(".dropdown").removeClass("open");
			return false;
		} else {
			// 默认行为 
			return true;
		}
	});
	
	// 加载首页
	var isAutoLoad = ($(".control_center_main_area").attr("auto_load") != "false");
	var def_open = "controlCenter/control_index.do";
	if (window.location.href.indexOf("#") != -1) {
		def_open = window.location.href.substring(window.location.href.indexOf("#")+1, window.location.href.length);
	}
	if (isAutoLoad) {
		$('.control_center_menu[linkurl="'+def_open+'"]').click();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
});
