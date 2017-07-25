package cn.maoaixi.cart.vo;

import java.util.HashSet;
import java.util.Set;

import cn.maoaixi.user.vo.User;

public class UserCart {
	private Integer ucid;
	private Double total = 0d;
	private User user;
	private Set<UserCartItem> userCartItems = new HashSet<UserCartItem>();

	public Integer getUcid() {

		return ucid;
	}

	public void setUcid(Integer ucid) {

		this.ucid = ucid;
	}

	public Double getTotal() {

		return total;
	}

	public void setTotal(Double total) {

		this.total = total;
	}

	public User getUser() {

		return user;
	}

	public void setUser(User user) {

		this.user = user;
	}

	public Set<UserCartItem> getUserCartItems() {
	
		return userCartItems;
	}

	public void setUserCartItems(Set<UserCartItem> userCartItems) {
	
		this.userCartItems = userCartItems;
	}

	

}
