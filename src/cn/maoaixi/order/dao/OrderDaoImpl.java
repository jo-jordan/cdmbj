package cn.maoaixi.order.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.maoaixi.order.vo.Order;
import cn.maoaixi.order.vo.OrderItem;

public class OrderDaoImpl extends HibernateDaoSupport implements OrderDao {

	@Override
	public void saveOrder(Order order) {
		this.getHibernateTemplate().save(order);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Order findOrderByUid(Integer uid) {
		List<Order> list = (List<Order>) this.getHibernateTemplate().find("from Order where uid = ?", uid);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public OrderItem findOrderItemByOid(Integer oid) {
		List<OrderItem> list = (List<OrderItem>) this.getHibernateTemplate().find("from OrderItem where oid = ?", oid);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void deleteFromOrder(Order order) {
		this.getHibernateTemplate().delete(order);
	}

	@Override
	public void updateOrder(Order order) {
		this.getHibernateTemplate().update(order);
	}

}
