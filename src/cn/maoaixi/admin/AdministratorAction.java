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
	//去登录界面
	public String goLogin(){
		return "goLoginSuccess";
	}
	//去管理页面
	public String goManagePage(){
		return "goManagePageSuccess";
	}
	//管理员登录
	public String login() {
		Administrator admin = adminService.findAdminByName(adminname);
		if(!admin.getAdminpassword().equals(adminpassword)){;
			this.addActionMessage("管理员密码错误!!!!!!");
			return "msg";
		}
		Administrator isExist = (Administrator) ServletActionContext.getRequest().getSession().getAttribute("adminUser");
		if(isExist != null){
			this.addActionMessage("您已登录,请勿重新登录!!!!!!");
			return "msg";
		}
		ServletActionContext.getRequest().getSession().setAttribute("adminUser", admin);
		return "loginSuccess";
	}
	//管理员退出登录
	public String quit(){
		ServletActionContext.getRequest().getSession().removeAttribute("adminUser");
		return "quitSuccess";
	}
	/**************** action方法区end **************/

	/**************** 特殊方法区 **************/
	/**************** 特殊方法区end **************/
}
