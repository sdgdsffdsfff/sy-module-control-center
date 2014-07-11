package module.controlCenter;

import java.util.ArrayList;
import java.util.List;

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
public class ControlCenterMenu {
	
	private static List<ControlCenterMenu> rootMenus = new ArrayList<ControlCenterMenu>();
	
	static {
		new ControlCenterMenu("首页", "controlCenter/control_index.do").appenToRoot();
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
