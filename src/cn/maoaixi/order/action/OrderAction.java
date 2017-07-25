package cn.maoaixi.order.action;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.maoaixi.cart.service.CartItemService;
import cn.maoaixi.cart.vo.UserCart;
import cn.maoaixi.cart.vo.UserCartItem;
import cn.maoaixi.order.service.OrderService;
import cn.maoaixi.order.vo.Order;
import cn.maoaixi.order.vo.OrderItem;
import cn.maoaixi.user.vo.User;

@SuppressWarnings("serial")
public class OrderAction extends ActionSupport {
	/**************** 自动压栈区 **************/
	private Order order;
	public Order getOrder(){
		return order;
	}
	public void setOrder(Order order){
		this.order=order;
	}
	/**************** 自动压栈区end **************/

	/**************** 注入service区 **************/
	private OrderService orderService;

	public void setOrderService(OrderService orderService) {

		this.orderService = orderService;
	}
	private CartItemService cartItemService;
	public void setCartItemService(CartItemService cartItemService) {
		
		this.cartItemService = cartItemService;
	}

	/**************** 注入service区end **************/

	/**************** 特殊字段提供区 **************/
	private String addr;
	private String consignee;
	private String phone;
	public void setAddr(String addr) {
		
		this.addr = addr;
	}
	public void setConsignee(String consignee) {
		
		this.consignee = consignee;
	}
	public void setPhone(String phone) {
		
		this.phone = phone;
	}

	/**************** 特殊字段提供区end **************/

	
	/**************** action方法区 **************/
	public String addToOrder() {
		/* 该action完成把购物车中的信息添加到订单中,需要有购物车,购物项,订单项,订单对象 */
		// 获取当前用户
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("isExist");
		if (user == null) {
			// 判断是否登录了
			this.addActionMessage("请先登录");
			return "msg";
		}
		//在生成新的订单之前,验证是否支付
		order = orderService.findOrderByUid(user.getUid());
		if(order != null){
			if(order.getState() == 1){
				this.addActionMessage("你还有没支付的订单,先交钱再玩好吗??????");
				return "msg";
			}
		}
		
		
		
		
		// 获取order对象
		order = new Order();
		// 获取购物车对象
		List<UserCart> userCartList = cartItemService.findUserCart(user);
		UserCart userCart = userCartList.get(0);
		// 获取总计
		order.setTotal(userCart.getTotal());
		// 设置订单时间
		order.setOrdertime(new Date());
		// 设置订单状态
		order.setState(1);// 1:未支付;2:已支付,未发货;3:已发货;4:已收货
		// 设置地址,手机
		// 设置客户
		order.setUser(user);
		// 设置订单项
		Set<OrderItem> orderItems = order.getOrderItems();
		for (UserCartItem userCartItem : userCart.getUserCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(userCartItem.getCount());
			orderItem.setSubtotal(userCartItem.getSubtotal());
			orderItem.setProduct(userCartItem.getProduct());
			orderItem.setOrder(order);
			orderItems.add(orderItem);
			
		}
		order.setOrderItems(orderItems);
		orderService.saveOrder(order);
		order = orderService.findOrderByUid(user.getUid());
		cartItemService.clearUserCart(user.getUid());
		return "addToOrderSuccess";
	}
	//查询order
	public String findOrder(){
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("isExist");
		if (user == null) {
			// 判断是否登录了
			this.addActionMessage("请先登录");
			return "msg";
		}
		order = orderService.findOrderByUid(user.getUid());
		return "findOrderSuccess";
	}
	//删除订单
	public String deleteFromOrder(){
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("isExist");
		if (user == null) {
			// 判断是否登录了
			this.addActionMessage("请先登录");
			return "msg";
		}
		order = orderService.findOrderByUid(user.getUid());
		orderService.deleteFromOrder(order);
		
		
		return "deleteFromOrderSuccess";
	}
	//修改收货信息
	public String modifyOrderImformation(){
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("isExist");
		if (user == null) {
			// 判断是否登录了
			this.addActionMessage("请先登录");
			return "msg";
		}
		order = orderService.findOrderByUid(user.getUid());
		order.setOid(order.getOid());
		order.setAddr(addr);
		order.setConsignee(consignee);
		order.setPhone(phone);
		orderService.updateOrder(order);
		return NONE;
		
	}
	//提交订单
	public String submitOrder(){
		
		return "submitOrderSuccess";
	}
	/**************** action方法区end **************/

}
