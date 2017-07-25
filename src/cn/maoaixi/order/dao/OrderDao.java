package cn.maoaixi.order.dao;

import cn.maoaixi.order.vo.Order;
import cn.maoaixi.order.vo.OrderItem;

public interface OrderDao {

	public void saveOrder(Order order);

	public Order findOrderByUid(Integer uid);

	public OrderItem findOrderItemByOid(Integer oid);

	public void deleteFromOrder(Order order);

	public void updateOrder(Order order);

}
