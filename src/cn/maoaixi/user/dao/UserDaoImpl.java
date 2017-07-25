package cn.maoaixi.user.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.maoaixi.user.vo.User;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	@Override
	public void save(User user) {
		this.getHibernateTemplate().save(user);
	}

	@Override
	public User findByUserName(String username) {
		List<?> list = this.getHibernateTemplate().find("from User where username = ?", username);
		if(list.size() != 0){
			System.out.println((User) list.get(0));
			return (User) list.get(0);
		}
		return null;
	}

	@Override
	public User findByUserEmail(String email) {
		List<?> list = this.getHibernateTemplate().find("from User where email = ?", email);
		if(list.size() != 0){
			System.out.println((User) list.get(0));
			return (User) list.get(0);
		}
		return null;
	}

	@Override
	public User findByUserPhone(String phone) {
		List<?> list = this.getHibernateTemplate().find("from User where phone = ?", phone);
		if(list.size() != 0){
			System.out.println((User) list.get(0));
			return (User) list.get(0);
		}
			
		return null;
	}

	@Override
	public User findByUserCode(String code) {
		List<?> list = this.getHibernateTemplate().find("from User where code = ?", code);
		if(list.size() != 0){
			return (User) list.get(0);
		}
		return null;
	}

	@Override
	public void update(User isExsit) {
		this.getHibernateTemplate().update(isExsit);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllUser() {
		return (List<User>) this.getHibernateTemplate().find("from User");
	}

	@Override
	public void deleteUserByUid(Integer uid) {
		User user = this.getHibernateTemplate().get(User.class, uid);
		this.getHibernateTemplate().delete(user);
	}

	@Override
	public User findUserByUid(Integer uid) {
		return this.getHibernateTemplate().get(User.class, uid);
	}
}
