package module.controlCenter;

import javax.servlet.http.HttpServletRequest;

import sy.module.core.mvc.annotation.ModuleAction;
import sy.module.core.mvc.annotation.ModuleController;

@ModuleController(namespace="module/controlCenter/")
public class ControlCenterController {
	
	@ModuleAction(url="index")
	public String index(HttpServletRequest request) {
		// 写入后台菜单
		request.setAttribute("menus", ControlCenterMenu.getRootMenus());
		return "freemarker:/module/controlCenter/view/index";
	}

	
	@ModuleAction(url="content_index")
	public String content_index() {
		return "freemarker:/module/controlCenter/view/content_index";
	}
	
}
