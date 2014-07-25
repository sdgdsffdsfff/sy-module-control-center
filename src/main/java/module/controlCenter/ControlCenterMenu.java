package module.controlCenter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Element;

import sy.module.core.moduleconfig.ModuleConfig;
import sy.module.core.mvc.annotation.ModuleFollowContainerInit;
import sy.module.core.util.XmlHelper;

/**
 * 控制中心菜单
 * 
 * 菜单创建方式
 * 
 * ControlCenterMenu parentMenu = new ControlCenterMenu("title", "")
 * 			.appendSubMenu(new ControlCenterMenu("sub1", "http://www.baidu.com"))
 * 			.appendSubMenu(new ControlCenterMenu("sub2", "http://www.baidu.com"))
 * 			.appenToRoot();
 * 
 * @author 石莹 @ caituo
 *
 */
@ModuleFollowContainerInit
public class ControlCenterMenu {
	private static final Log log = LogFactory.getLog(ControlCenterMenu.class);
	
	private static List<ControlCenterMenu> rootMenus = new ArrayList<ControlCenterMenu>();
	
	public ControlCenterMenu() {
		
		new ControlCenterMenu("首页", "controlCenter/control_index.do").appenToRoot();
		
		/**
		 <sy>
		  <controlCenter>
		  	<menu name="" >
			    <item>
			      <name>webapp/module/core</name>
			      <url>../../module/core</url>
			    </item>
		  	</menu>
		  </controlCenter>
		</sy>
		 */
		for (File jar : ModuleConfig.getModuleConfigs().keySet()) {
			try {
				Element conf = ModuleConfig.getModuleConfigs().get(jar);
				for (Element cc : XmlHelper.getElementsByName(conf, "controlCenter")) {
					for (Element cm : XmlHelper.getElementsByName(cc, "menu")) {
						String name = cm.getAttribute("name");
						ControlCenterMenu ccm = new ControlCenterMenu(name, "");
						for (Element item : XmlHelper.getElementsByName(cm, "item")) {
							ccm.appendSubMenu(new ControlCenterMenu(
									XmlHelper.getElementByName(item, "display").getTextContent().trim(), 
									XmlHelper.getElementByName(item, "url").getTextContent().trim()));
						}
						ccm.appenToRoot();
					}
				}
			} catch (Exception e) {
				log.warn("scan package ["+jar+"] faild.", e);
			}
		}
//		new ControlCenterMenu("测试子菜单", "")
//			.appendSubMenu(new ControlCenterMenu("sub1", "http://www.baidu.com"))
//			.appendSubMenu(new ControlCenterMenu("sub2", "http://www.baidu.com"))
//			.appenToRoot();
	}
	
	/**
	 * 获得根菜单
	 * @return 根菜单列表
	 */
	public static List<ControlCenterMenu> getRootMenus() {
		return rootMenus;
	}
	
	/**
	 * 追加子菜单
	 * @param subMenu
	 * @return 返回自身
	 */
	public ControlCenterMenu appendSubMenu(ControlCenterMenu subMenu) {
		this.subMenu.add(subMenu);
		return this;
	}
	
	/**
	 * 当前菜单追加到根菜单
	 */
	public ControlCenterMenu appenToRoot() {
		rootMenus.add(this);
		return this;
	}
	
	public ControlCenterMenu(String title, String linkUrl) {
		this.title = title;
		this.linkUrl = linkUrl;
		this.linkType = "control-center";	// 在控制中心打开
		this.subMenu = new ArrayList<ControlCenterMenu>();
	}
	
	/**
	 * 菜单标题
	 */
	private String title;
	
	/**
	 * 链接地址
	 */
	private String linkUrl;
	
	/**
	 * 链接类型，标记链接打开的方式
	 */
	private String linkType;
	
	/**
	 * 子菜单
	 */
	private List<ControlCenterMenu> subMenu;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getLinkType() {
		return linkType;
	}

	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}

	public List<ControlCenterMenu> getSubMenu() {
		return subMenu;
	}

	public void setSubMenu(List<ControlCenterMenu> subMenu) {
		this.subMenu = subMenu;
	}
	
	
	

}
