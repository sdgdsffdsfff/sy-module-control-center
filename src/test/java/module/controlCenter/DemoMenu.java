package module.controlCenter;

import sy.module.core.mvc.ModuleBeanFactory;
import sy.module.core.mvc.annotation.ModuleAction;
import sy.module.core.mvc.annotation.ModuleActionHttpMethod;
import sy.module.core.mvc.annotation.ModuleController;
import sy.module.core.mvc.annotation.ModuleFollowContainerInit;

@ModuleController(namespace="/module/controlCenter")
@ModuleFollowContainerInit
public class DemoMenu {
	
	static {
		new ControlCenterMenu("测试页面", "").appendSubMenu(new ControlCenterMenu("测试页面", "controlCenter/demoPage.do")).appenToRoot();
	}
	
	@ModuleAction(url="demoPage", method=ModuleActionHttpMethod.GET)
	public String demoPage() {
		ModuleBeanFactory.getBean(DemoMenu.class);
		return "这是测试页面";
	}
	
}
