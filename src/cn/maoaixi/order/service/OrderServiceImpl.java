package cn.maoaixi.order.service;

import org.springframework.transaction.annotation.Transactional;

import cn.maoaixi.order.dao.OrderDao;
import cn.maoaixi.order.vo.Order;
import cn.maoaixi.order.vo.OrderItem;
@Transactional
public class OrderServiceImpl implements OrderService {
	private OrderDao orderDao;

	public void setOrderDao(OrderDao orderDao) {
	
		this.orderDao = orderDao;
	}

	@Override
	public void saveOrder(Order order) {
		orderDao.saveOrder(order);
	}

	@Override
	public Order findOrderByUid(Integer uid) {
			
		return orderDao.findOrderByUid(uid);
	}

	@Override
	public OrderItem findOrderItemByOid(Integer oid) {
			
		return orderDao.findOrderItemByOid(oid);
	}

	@Override
	public void deleteFromOrder(Order order) {
		orderDao.deleteFromOrder(order);
	}

	@Override
	public void updateOrder(Order order) {
		orderDao.updateOrder(order);
	}
	
	
}
