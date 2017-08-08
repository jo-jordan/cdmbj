package cn.maoaixi.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import cn.maoaixi.admin.Administrator;

@SuppressWarnings("serial")
public class AdminInterceptor extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		Administrator  adminUser = (Administrator) ServletActionContext.getRequest().getSession().getAttribute("adminUser");
		if(adminUser != null){
			return actionInvocation.invoke();
		}else{
			ActionSupport action = (ActionSupport) actionInvocation.getAction();
			action.addActionError("管理员尚未登录.");
			return Action.LOGIN;
		}
	}

}
