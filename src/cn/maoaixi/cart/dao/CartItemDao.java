package cn.maoaixi.cart.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.maoaixi.cart.vo.UserCart;
import cn.maoaixi.cart.vo.UserCartItem;
import cn.maoaixi.user.vo.User;

public class CartItemDao extends HibernateDaoSupport {

	/**
	 * 删除购物项,持久-->瞬时
	 * @param uciid
	 */
	public void deleteByPid(Integer pid) {
		UserCartItem userCartItem = findUserCartItemByPid(pid);

		this.getHibernateTemplate().delete(userCartItem);
	}

	/**
	 * 删除购物车,持久-->瞬时
	 */
	@SuppressWarnings("unchecked")
	public void clearUserCart(Integer uid) {
		List<UserCart> list = (List<UserCart>) this.getHibernateTemplate().find("from UserCart where uid = ?",uid);
		for (UserCart uc : list) {
			this.getHibernateTemplate().delete(uc);
		}
	}

	/**
	 * 获取购物车,脱管-->持久
	 * @param user
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<UserCart> findUserCart(User user) {
		List<UserCart> list = (List<UserCart>) this.getHibernateTemplate().find("from UserCart where uid=?",
				user.getUid());
		return list;
	}

	/**
	 * 脱管-->持久
	 * @param userCartItem
	 */
	public void saveOrUpdateCartItem(UserCartItem userCartItem) {
		this.getHibernateTemplate().saveOrUpdate(userCartItem);
	}

	@SuppressWarnings("unchecked")
	public UserCartItem findUserCartItemByPid(Integer pid) {
		List<UserCartItem> list = (List<UserCartItem>) this.getHibernateTemplate().find("from UserCartItem where pid=?",pid);
		if(!list.isEmpty()){
			return (UserCartItem) list.get(0);
		}else{
			return null;
		}
	}


	public UserCartItem getUserCartItemById(Integer uciid) {
		return this.getHibernateTemplate().get(UserCartItem.class, uciid);
	}

	public void updateUserCartItem(UserCartItem userCartItem) {
		this.getHibernateTemplate().update(userCartItem);
	}

	public void saveUserCartItem(UserCartItem userCartItem) {
		this.getHibernateTemplate().save(userCartItem);
	}

	public void updateUserCart(UserCart userCart) {
		this.getHibernateTemplate().update(userCart);
	}

	public void saveUserCart(UserCart userCart) {
		this.getHibernateTemplate().save(userCart);
	}

	@SuppressWarnings("unchecked")
	public UserCartItem findUserCartItemByPidAndUcid(Integer pid, Integer ucid) {
		String hql = "from UserCartItem u where u.product.pid=? and u.userCart.ucid=?"; 
		List<UserCartItem> list = (List<UserCartItem>) this.getHibernateTemplate().find(hql, pid,ucid);
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}
	
	

}
