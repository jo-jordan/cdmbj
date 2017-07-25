package cn.maoaixi.user.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

import cn.maoaixi.cart.Cart;
import cn.maoaixi.cart.CartItem;
import cn.maoaixi.cart.service.CartItemService;
import cn.maoaixi.cart.vo.UserCart;
import cn.maoaixi.cart.vo.UserCartItem;
import cn.maoaixi.user.service.UserService;
import cn.maoaixi.user.vo.User;

@SuppressWarnings({ "serial", "rawtypes" })
public class UserAction extends ActionSupport implements ModelDriven {
	/**************** 自动压栈区 **************/
	// struts2模型驱动使用的类
	private User user = new User();

	@Override
	public Object getModel() {
		return user;
	}

	private List<User> userList;

	public List<User> getUserList() {
		return userList;
	}

	private List<UserCart> userCartList;

	public List<UserCart> getUserCartList() {

		return userCartList;
	}

	public void setUserCartList(List<UserCart> userCartList) {

		this.userCartList = userCartList;
	}

	/**************** 自动压栈区end **************/
	/**************** 注入service区 **************/
	// 注入service层
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	private CartItemService cartItemService;

	public void setCartItemService(CartItemService cartItemService) {
		this.cartItemService = cartItemService;
	}

	/**************** 注入service区end **************/

	/**************** 特殊字段提供区 **************/

	// 为验证码单独接收做准备
	private String vercode;

	public void setVercode(String vercode) {
		this.vercode = vercode;
	}

	// 记住用户名的flag
	private String isRememberUsername;

	public void setIsRememberUsername(String isRememberUsername) {

		this.isRememberUsername = isRememberUsername;
	}

	/**************** 特殊字段提供区end **************/

	/**************** action方法区 **************/
	/* 跳转到注册页面的action */
	public String goRegisterPage() {
		return "goRegisterPageSuccess";
	}

	/* 注册action */
	@InputConfig(resultName = "registerInput")
	public String register() {

//		String userEmail = user.getEmail();
//		String emailFormat = userEmail.split("@")[1];
		userService.register(user);
		this.addActionMessage("注册成功..去你的邮箱查看一波吧!");
		return "registerSuccess";

	}

	/* 检测用户名,邮箱,手机号是否已经被占用 */
	public String checkUserName() throws IOException {
		User isExist = userService.findByUserName(user.getUsername());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		if (isExist == null) {
			response.getWriter().print("<font color='green'>用户名可以使用</font>");
		} else {
			response.getWriter().print("<font color='red'>用户名:" + user.getUsername() + "只怕是被占用了</font>");
		}
		return NONE;
	}

	public String checkEmail() throws IOException {
		User isExist = userService.findByUserEmail(user.getEmail());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		if (isExist == null) {
			response.getWriter().print("<font color='green'>邮箱可以使用</font>");
		} else {
			response.getWriter().print("<font color='red'>邮箱:" + user.getEmail() + "只怕是被占用了</font>");
		}
		return NONE;
	}

	public String checkPhone() throws IOException {
		User isExist = userService.findByUserPhone(user.getPhone());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		if (isExist == null) {
			response.getWriter().print("<font color='green'>手机号可以使用</font>");
		} else {
			response.getWriter().print("<font color='red'>手机号:" + user.getPhone() + "只怕是被占用了</font>");
		}
		return NONE;
	}

	/* 用户激活 */
	public String active() {
		// 先确定code的正确性
		User isExsit = userService.findByUserCode(user.getCode());
		// isExsit不为空就说明在数据库中有那个激活码,即可激活成功
		if (isExsit != null) {
			isExsit.setState(1);
			userService.update(isExsit);
			this.addActionMessage("激活成功!现在可以登录了!");
			return "activeSuccess";
		}
		this.addActionMessage("激活失败了,重新点击激活链接!");
		return "activeSuccess";
	}

	/* 验证码校验,基于ajax */
	public String checkVerCode() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		// 先获取session中的vercode
		String verCodeFromSession = (String) request.getSession().getAttribute("verCode");
		System.out.println("校验时session中的验证码: " + verCodeFromSession + " ,从表单获取的验证码: " + vercode);

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		if (verCodeFromSession.toUpperCase().equals(vercode.toUpperCase())) {
			response.getWriter().print("<font color='green'>验证码正确.</font>");
		} else {
			response.getWriter().print("<font color='red'>验证码错误,刷新后重新输入.</font>");
		}
		return NONE;
	}

	/* 登录,跳转到登录界面 */
	public String goLoginPage() {
		return "goLoginPageSuccess";
	}

	/* 记住密码 */
	public void rememberUsername() {
		Cookie cookie = null;
		System.out.println(isRememberUsername);
		if (isRememberUsername == "true") {
			cookie = new Cookie("isRememberUsername", user.getUsername());
			cookie.setMaxAge(24 * 60 * 60 * 7);// 7 days
			ServletActionContext.getResponse().addCookie(cookie);
		} else {
			cookie = ServletActionContext.getRequest().getCookies()[0];
			cookie.setMaxAge(0);
			ServletActionContext.getResponse().addCookie(cookie);
		}
	}

	/* 登录,登录的校验 */
	@InputConfig(resultName = "loginInput")
	public String login() {
		// 拿着前台传递过来的用户名去数据库匹配
		User isExist = userService.findByUserName(user.getUsername());
		// 首先判断是否注册
		if (isExist != null) {
			// 然后判断是否激活
			if (isExist.getState() == 1) {
				// 通过该用户名拿到密码
				if (isExist.getPassword().equals(user.getPassword())) {
					// this.addActionMessage("登录成功");
					HttpServletRequest request = ServletActionContext.getRequest();
					request.getSession().setAttribute("isExist", isExist);
					// 获取cart对象
					Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
					if (cart != null) {
						userCartList = cartItemService.findUserCart(isExist);

						// 如果cart不为空
						UserCart userCart;
						UserCartItem userCartItem;
						for (CartItem cartItem : cart.getCartItem()) {
							if (!userCartList.isEmpty()) {
								userCart = userCartList.get(0);
								userCart.setUcid(userCart.getUcid());
								userCart.setTotal(userCart.getTotal() + cart.getTotal());
								userCartItem = cartItemService.findUserCartItemByPid(cartItem.getProduct().getPid());
								userCartItem.setCount(cartItem.getCount() + userCartItem.getCount());
								userCartItem.setSubtotal(cartItem.getSubtotal() + userCartItem.getSubtotal());
								userCartItem.setUciid(userCartItem.getUciid());
								cartItemService.updateUserCartItem(userCartItem);
								cartItemService.updateUserCart(userCart);
							} else {
								userCart = new UserCart();
								userCart.setTotal(cart.getTotal());
								userCart.setUser(isExist);
								userCartItem = new UserCartItem();
								userCartItem.setSubtotal(cartItem.getSubtotal());
								userCartItem.setCount(cartItem.getCount());
								userCartItem.setProduct(cartItem.getProduct());
								userCartItem.setUserCart(userCart);
								cartItemService.saveUserCart(userCart);
								cartItemService.saveUserCartItem(userCartItem);
							}
						}
						cart.clearCart();

					}
					userCartList = cartItemService.findUserCart(isExist);
					return "loginSuccess";
				} else {
					this.addFieldError("password", "密码不正确.");
					return "loginInput";
				}
			} else {
				this.addFieldError("username", "用户: " + user.getUsername() + " 尚未激活");
				return "loginInput";
			}
		} else {
			// 没有注册直接返回
			this.addFieldError("username", "用户: " + user.getUsername() + " 尚未注册");
			return "loginInput";
		}
	}

	/* 退出登录 */
	public String quit() {
		ServletActionContext.getRequest().getSession().invalidate();
		return "quitSuccess";
	}

	// 用户管理:查询用户
	public String manage() {
		userList = userService.findAllUser();
		return "findAllUserSuccess";
	}

	// 用户管理:删除用户
	public String deleteUserByUid() {
		userService.deleteUserByUid(user.getUid());
		return "deleteUserByUidSuccess";
	}

	// 用户管理:修改用户
	public String modifyUser() {
		User _user = userService.findUserByUid(user.getUid());
		_user.setUid(user.getUid());
		if (user.getEmail() != null) {
			_user.setEmail(user.getEmail());
		}
		if (user.getPhone() != null) {
			_user.setPhone(user.getPhone());
		}
		if (user.getAddr() != null) {
			_user.setAddr(user.getAddr());
		}
		if (user.getSex() != null) {
			_user.setSex(user.getSex());
		}
		if (user.getState() != null) {
			_user.setState(user.getState());
		}
		userService.update(_user);

		return "modifyUserSuccess";
	}
	/**************** action方法区end **************/

	/**************** 特殊方法区 **************/
	/**************** 特殊方法区end **************/

}
