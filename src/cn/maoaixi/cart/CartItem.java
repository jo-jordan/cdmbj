package cn.maoaixi.cart;

import cn.maoaixi.product.vo.Product;

public class CartItem {
	// 引用商品实体
	private Product product;
	// 商品数量
	private Integer count;
	// 商品小计
	@SuppressWarnings("unused")
	private Double subtotal = 0d;
	

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
		return count * product.getShop_price();
	}
}
