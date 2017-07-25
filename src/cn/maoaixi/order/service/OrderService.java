package cn.maoaixi.order.service;

import cn.maoaixi.order.vo.Order;
import cn.maoaixi.order.vo.OrderItem;

public interface OrderService {

	public void saveOrder(Order order);

	public Order findOrderByUid(Integer uid);

	public OrderItem findOrderItemByOid(Integer oid);

	public void deleteFromOrder(Order order);

	public void updateOrder(Order order);

}
