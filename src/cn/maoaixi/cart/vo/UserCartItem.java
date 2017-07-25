package cn.maoaixi.cart.vo;

import cn.maoaixi.product.vo.Product;

public class UserCartItem {
	private Integer uciid;
	private Product product;
	private Integer count;
	private Double subtotal = 0d;
	private UserCart userCart;
	public Integer getUciid() {
	
		return uciid;
	}
	public void setUciid(Integer uciid) {
	
		this.uciid = uciid;
	}
	public Product getProduct() {
	
		return product;
	}
	public void setProduct(Product product) {
	
		this.product = product;
	}
	public Integer getCount() {
	
		return count;
	}
	public void setCount(Integer count) {
	
		this.count = count;
	}
	public Double getSubtotal() {
	
		return subtotal;
	}
	public void setSubtotal(Double subtotal) {
	
		this.subtotal = subtotal;
	}
	public UserCart getUserCart() {
	
		return userCart;
	}
	public void setUserCart(UserCart userCart) {
	
		this.userCart = userCart;
	}
	
}
