package cn.maoaixi.cart;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart {
	//购物车使用map来存放
	private Map<Integer,CartItem> cart = new HashMap<Integer,CartItem>();
	//购物车总计
	private Double total = 0d;
	public Double getTotal(){
		return total;
	}
	
	//获取map中的购物项
	public Collection<CartItem> getCartItem(){
		return cart.values();
	}
	//购物车中提供三个方法
	//1,添加商品到购物车
	public void addProductToCart(CartItem cartItem){
		Integer pid = cartItem.getProduct().getPid();
		
		if(cart.containsKey(pid)){
			//存在有此pid就说明该商品已经存在
			CartItem _cartItem = cart.get(pid);
			_cartItem.setCount(cartItem.getCount()+_cartItem.getCount());
		}else{
			cart.put(pid, cartItem);
		}
		total += cartItem.getSubtotal();
	}
	//2,删除商品
	public void deleteProductFromCart(Integer pid){
		if(cart.containsKey(pid)){
			total -= cart.remove(pid).getSubtotal();
		}
	}
	//3,清空整个购物车
	public void clearCart(){
		cart.clear();
		total = 0d;
	}
}
