package module.controlCenter;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sy.module.core.config.PropertiesLoader;
import sy.module.core.mvc.ModuleCoreFilter;
import sy.module.core.mvc.annotation.ModuleAction;
import sy.module.core.mvc.annotation.ModuleActionParmar;
import sy.module.core.mvc.annotation.ModuleController;
import sy.module.core.mvc.annotation.ModuleFilter;
import sy.module.core.util.string.StringLoadUtil;

@ModuleController(namespace="module/controlCenter/")
public class ControlCenterController {
	private static final Log log = LogFactory.getLog(ControlCenterController.class);
	
	private static final String session_key = "module_controlCenter_login";
	
	
	@ModuleFilter(url = "^/module/.*")
	public boolean filter(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (request.getSession().getAttribute(session_key) != null) {
			return true;
		}
		String relative = ModuleCoreFilter.getRequestContext().getRequestRelativelyUrl();
		String checkStr = relative.subSequence("/module/".length(), relative.length()) + ".do";
		if (checkStr.equals("controlCenter/index.do")) {
			log.warn("未知用户访问控制中心，跳转到登录页");
			response.sendRedirect(ModuleCoreFilter.getRequestContext().basePath + "module/controlCenter/login.do");
			return false;
		}
		for (ControlCenterMenu ccm : ControlCenterMenu.getRootMenus()) {
			if (checkStr.equals(ccm.getLinkUrl())) {
				log.warn("未知用户访问控制中心，跳转到登录页");
				response.sendRedirect(ModuleCoreFilter.getRequestContext().basePath + "module/controlCenter/login.do");
				return false;
			}
			for (ControlCenterMenu ccms : ccm.getSubMenu()) {
				if (checkStr.equals(ccms.getLinkUrl())) {
					log.warn("未知用户访问控制中心，跳转到登录页");
					response.sendRedirect(ModuleCoreFilter.getRequestContext().basePath + "module/controlCenter/login.do");
					return false;
				}
			}
		}
		return true;
	}
	

	@ModuleAction(url="login")
	public String login(HttpServletRequest request) {
		String username = PropertiesLoader.getInstance().getConfig("module.controlCenter.login.username");
		String password = PropertiesLoader.getInstance().getConfig("module.controlCenter.login.password");
		if (username == null || username.equals("") || password == null || password.equals("")) {
			log.warn("尚未配置控制中心登录参数, [module.controlCenter.login.username, module.controlCenter.login.password]");
			request.setAttribute("loginLoad_errorMessage", "尚未配置登录参数，请联系管理员。");
		}
		return "freemarker:/module/controlCenter/view/login";
	}
	

	@ModuleAction(url="dologin")
	public String dologin(
			HttpServletRequest request,
			HttpServletResponse response,
			@ModuleActionParmar(name = "username") String username, 
			@ModuleActionParmar(name = "password") String password) {
		String cusername = PropertiesLoader.getInstance().getConfig("module.controlCenter.login.username");
		String cpassword = PropertiesLoader.getInstance().getConfig("module.controlCenter.login.password");
		if (cusername == null || cusername.equals("") || cpassword == null || cpassword.equals("")) {
			log.warn("尚未配置控制中心登录参数, [module.controlCenter.login.username, module.controlCenter.login.password]");
			request.setAttribute("loginLoad_errorMessage", "尚未配置登录参数，请联系管理员。");
			return "freemarker:/module/controlCenter/view/login";
		}
		if (cusername.equals(username) && cpassword.equals(password)) {
			request.getSession().setAttribute(session_key, true);
			return "redirect:" + ModuleCoreFilter.getRequestContext().basePath 
					+ "module/controlCenter/index.do";
		} else {
			log.info("用户名或密码错误");
			request.setAttribute("errorMessage", "用户名或密码错误");
			return "freemarker:/module/controlCenter/view/login";
		}
		
	}
	
	
	@ModuleAction(url="index")
	public String index(HttpServletRequest request, @ModuleActionParmar(name="target") String target) {
		// 写入后台菜单
		request.setAttribute("menus", ControlCenterMenu.getRootMenus());
		// 测试地址
		if (target != null && !target.equals("")) {
			try {
				request.setAttribute("target", target);
				request.setAttribute(
						"testTarget", 
						StringLoadUtil.loadAsStringUrl(
								target.startsWith("http://") 
								? 
									target 
								: 
									(ModuleCoreFilter.getRequestContext().basePath + target)));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "freemarker:/module/controlCenter/view/index";
	}

	
	@ModuleAction(url="control_index")
	public String control_index() {
		return "freemarker:/module/controlCenter/view/control_index";
	}
	
}
