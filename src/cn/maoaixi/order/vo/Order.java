package cn.maoaixi.order.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import cn.maoaixi.user.vo.User;

public class Order {
	private Integer oid;
	private Double total = 0d;
	private Date ordertime;
	private Integer state;
	private String addr;
	private String consignee;
	private User user;
	private String phone;
	private Set<OrderItem> orderItems = new HashSet<OrderItem>();
	public Integer getOid() {
	
		return oid;
	}
	public void setOid(Integer oid) {
	
		this.oid = oid;
	}
	public Double getTotal() {
	
		return total;
	}
	public void setTotal(Double total) {
	
		this.total = total;
	}
	public Date getOrdertime() {
	
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
	
		this.ordertime = ordertime;
	}
	public Integer getState() {
	
		return state;
	}
	public void setState(Integer state) {
	
		this.state = state;
	}
	public String getAddr() {
	
		return addr;
	}
	public void setAddr(String addr) {
	
		this.addr = addr;
	}
	public String getConsignee() {
	
		return consignee;
	}
	public void setConsignee(String consignee) {
	
		this.consignee = consignee;
	}
	public User getUser() {
	
		return user;
	}
	public void setUser(User user) {
	
		this.user = user;
	}
	public String getPhone() {
	
		return phone;
	}
	public void setPhone(String phone) {
	
		this.phone = phone;
	}
	public Set<OrderItem> getOrderItems() {
	
		return orderItems;
	}
	public void setOrderItems(Set<OrderItem> orderItems) {
	
		this.orderItems = orderItems;
	}

}
