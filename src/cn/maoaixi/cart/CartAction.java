package cn.maoaixi.cart;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.maoaixi.cart.service.CartItemService;
import cn.maoaixi.cart.vo.UserCart;
import cn.maoaixi.cart.vo.UserCartItem;
import cn.maoaixi.product.service.ProductService;
import cn.maoaixi.product.vo.Product;
import cn.maoaixi.user.vo.User;

@SuppressWarnings("serial")
public class CartAction extends ActionSupport {
	/**************** 自动压栈区 **************/
	// 注册UserCart
	private List<UserCart> userCartList;

	public List<UserCart> getUserCartList() {

		return userCartList;
	}

	public void setUserCartList(List<UserCart> userCartList) {

		this.userCartList = userCartList;
	}

	private UserCart userCart;
	private UserCartItem userCartItem;

	public UserCart getUserCart() {

		return userCart;
	}

	public void setUserCart(UserCart userCart) {

		this.userCart = userCart;
	}

	public UserCartItem getUserCartItem() {

		return userCartItem;
	}

	public void setUserCartItem(UserCartItem userCartItem) {

		this.userCartItem = userCartItem;
	}

	/**************** 自动压栈区end **************/

	/**************** 注入service区 **************/
	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	private CartItemService cartItemService;

	public void setCartItemService(CartItemService cartItemService) {
		this.cartItemService = cartItemService;
	}

	/**************** 注入service区end **************/

	/**************** 特殊字段提供区 **************/
	// 提供pid字段
	private Integer pid;

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	// 提供count字段
	private Integer count;

	public void setCount(Integer count) {
		this.count = count;
	}

	/**************** 特殊字段提供区end **************/

	/**************** action方法区 **************/
	public String addToCart() {
		// 根据pid查询那件商品
		Product product = productService.findProductByPid(pid);

		// 进行登录判断,如果登录了,会存进数据库,如果没有登录,下次登录购物车会消失
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("isExist");
		if (user != null) {
			// 从数据库中获取到userCartList
			userCartList = cartItemService.findUserCart(user);
			// 设置user

			// UserCart userCart;
			// UserCartItem userCartItem;
			if (!userCartList.isEmpty()) {
				// 首先判断用户购物车的空否若不空,则获取到用户购物项
				userCart = userCartList.get(0);
				userCart.setUser(user);
				userCartItem = cartItemService.findUserCartItemByPidAndUcid(pid,userCart.getUcid());
				Integer uccid = null;

				if (userCartItem != null) {
					// 用户购物项只需要做update
					uccid = userCartItem.getUciid();
					userCart.setTotal(userCart.getTotal() + product.getShop_price() * count);
					userCartItem = cartItemService.getUserCartItemById(uccid);
					userCartItem.setUciid(uccid);
					userCartItem.setSubtotal(userCartItem.getSubtotal() + count * product.getShop_price());
					userCartItem.setCount(userCartItem.getCount() + count);
					cartItemService.updateUserCartItem(userCartItem);
				} else {
					userCartItem = new UserCartItem();
					userCart.setTotal(userCart.getTotal() + product.getShop_price() * count);
					userCartItem.setCount(count);
					userCartItem.setProduct(product);
					userCartItem.setSubtotal(count * product.getShop_price());
					userCartItem.setUserCart(userCart);
					cartItemService.saveUserCartItem(userCartItem);
				}
			} else {
				userCart = new UserCart();
				userCart.setUser(user);
				userCartItem = new UserCartItem();
				// 给usercart设置总计
				userCart.setTotal(userCart.getTotal() + product.getShop_price() * count);
				// 设置购物项
				userCartItem.setCount(count);
				userCartItem.setProduct(product);
				userCartItem.setSubtotal(count * product.getShop_price());
				userCartItem.setUserCart(userCart);
				cartItemService.saveUserCart(userCart);
				cartItemService.saveUserCartItem(userCartItem);
			}

			// 最后查找一遍
			cartItemService.updateUserCart(userCart);
			userCartList = cartItemService.findUserCart(user);
		} else {
			// 创建购物项
			CartItem cartItem = new CartItem();
			// 获取count
			cartItem.setCount(count);
			cartItem.setProduct(product);
			// 创建购物车
			HttpServletRequest reqeust = ServletActionContext.getRequest();
			Cart cart = getCart(reqeust);
			// 将购物项添加到购物车中
			cart.addProductToCart(cartItem);
		}

		return "addToCartSuccess";
	}

	public String deleteFromCart() {

		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("isExist");
		if (user != null) {
			userCartList = cartItemService.findUserCart(user);
			userCart = userCartList.get(0);
			userCartItem = cartItemService.findUserCartItemByPid(pid);
			cartItemService.deleteByPid(pid);
			userCart.setTotal(userCart.getTotal() - userCartItem.getSubtotal());
			cartItemService.updateUserCart(userCart);
			userCartList = cartItemService.findUserCart(user);
		}
		// 从session里面获取cart
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if (cart != null) {
			cart.deleteProductFromCart(pid);
		}
		return "deleteFromCartSuccess";
	}

	public String clear() {
		Cart cart = getCart(ServletActionContext.getRequest());
		if (cart != null) {
			cart.clearCart();
		}
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("isExist");
		if (user != null) {
			cartItemService.clearUserCart(user.getUid());
		}
		return "clearSuccess";
	}

	public String goCart() {
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("isExist");
		if (user != null) {
			userCartList = cartItemService.findUserCart(user);
		}
		return "goCartSuccess";
	}

	// 从用户购物车里获取购物项
	public String findCartItem() {
		return "findCartItemSuccess";
	}
	
	//修改购物车中商品数量
	public String changeProductCount(){
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("isExist");
		if(user != null){
			userCartList = cartItemService.findUserCart(user);
			userCart = userCartList.get(0);
			userCartItem = cartItemService.findUserCartItemByPid(pid);
			
			userCart.setUcid(userCart.getUcid());
			userCart.setTotal(userCart.getTotal() - userCartItem.getSubtotal() + count * userCartItem.getProduct().getShop_price());
			userCartItem.setCount(count);
			userCartItem.setSubtotal(count * userCartItem.getProduct().getShop_price());
			userCartItem.setUciid(userCartItem.getUciid());
			
			cartItemService.updateUserCartItem(userCartItem);
			cartItemService.updateUserCart(userCart);
			userCartList = cartItemService.findUserCart(user);
		}else{
			Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
			for(CartItem cartItem : cart.getCartItem()){
				if(cartItem.getProduct().getPid() == pid){
					cart.deleteProductFromCart(pid);
					CartItem ci = new CartItem();
					ci.setCount(count);
					ci.setProduct(cartItem.getProduct());
					cart.addProductToCart(ci);
				}
			}
		}
		return "changeProductCountSuccess";
	}
	

	/**************** action方法区end **************/

	/**************** 特殊方法区 **************/
	public Cart getCart(HttpServletRequest request) {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		return cart;
	}
	/**************** 特殊方法区end **************/

}
