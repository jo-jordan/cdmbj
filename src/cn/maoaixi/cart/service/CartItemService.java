package cn.maoaixi.cart.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.maoaixi.cart.dao.CartItemDao;
import cn.maoaixi.cart.vo.UserCart;
import cn.maoaixi.cart.vo.UserCartItem;
import cn.maoaixi.user.vo.User;
@Transactional
public class CartItemService {
	private CartItemDao cartItemDao;
	

	public void setCartItemDao(CartItemDao cartItemDao) {
	
		this.cartItemDao = cartItemDao;
	}



	public void deleteByPid(Integer pid) {
		cartItemDao.deleteByPid(pid);
	}


	public void clearUserCart(Integer uid) {
		cartItemDao.clearUserCart(uid);
	}


	public List<UserCart> findUserCart(User user) {
			
		return cartItemDao.findUserCart(user);
	}




	public void saveOrUpdateCartItem(UserCartItem userCartItem) {
		cartItemDao.saveOrUpdateCartItem(userCartItem);
	}


	public UserCartItem findUserCartItemByPid(Integer pid) {
			
		return cartItemDao.findUserCartItemByPid(pid);
	}


	public UserCartItem getUserCartItemById(Integer uciid) {
			
		return cartItemDao.getUserCartItemById(uciid);
	}



	public void updateUserCartItem(UserCartItem userCartItem) {
		cartItemDao.updateUserCartItem(userCartItem);
	}



	public void saveUserCartItem(UserCartItem userCartItem) {
		cartItemDao.saveUserCartItem(userCartItem);
	}



	public void updateUserCart(UserCart userCart) {
		cartItemDao.updateUserCart(userCart);
	}



	public void saveUserCart(UserCart userCart) {
		cartItemDao.saveUserCart(userCart);
	}




	public UserCartItem findUserCartItemByPidAndUcid(Integer pid, Integer ucid) {
			
		return cartItemDao.findUserCartItemByPidAndUcid(pid,ucid);
	}




}
