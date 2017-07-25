package cn.maoaixi.admin;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class AdministratorAction extends ActionSupport {
	/**************** 自动压栈区 **************/

	/**************** 自动压栈区end **************/

	/**************** 注入service区 **************/
	private AdministratorService adminService;
	public void setAdminService(AdministratorService adminService) {
		this.adminService = adminService;
	}
	/**************** 注入service区end **************/



	/**************** 特殊字段提供区 **************/
	private String adminname;
	private String adminpassword;

	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}

	public void setAdminpassword(String adminpassword) {
		this.adminpassword = adminpassword;
	}

	/**************** 特殊字段提供区end **************/

	/**************** action方法区 **************/
	public String login() {
		Administrator admin = adminService.findAdminByName(adminname);
		if(!admin.getAdminpassword().equals(adminpassword)){;
			this.addActionError("管理员密码错误!!!!!!");
			return "loginSuccess";
		}
		ServletActionContext.getRequest().getSession().setAttribute("adminUser", admin);
		return "loginSuccess";
	}
	/**************** action方法区end **************/

	/**************** 特殊方法区 **************/
	/**************** 特殊方法区end **************/
}
