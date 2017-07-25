package cn.maoaixi.user.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.maoaixi.user.dao.UserDao;
import cn.maoaixi.user.vo.User;
import cn.maoaixi.utils.MailUtils;
import cn.maoaixi.utils.UUIDUtils;
@Transactional
public class UserServiceImpl implements UserService {
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void register(User user) {
		user.setState(0);//0 未激活 1 已激活
		String code = UUIDUtils.getUUID()+UUIDUtils.getUUID();
		user.setCode(code);
		userDao.save(user);
		//发送邮件
		try {
			MailUtils.sendMail(user.getEmail(), code);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public User findByUserName(String username) {
		
		return userDao.findByUserName(username);
	}

	@Override
	public User findByUserEmail(String email) {
		
		return userDao.findByUserEmail(email);
	}

	@Override
	public User findByUserPhone(String phone) {
			
		return userDao.findByUserPhone(phone);
	}

	@Override
	public User findByUserCode(String code) {
			
		return userDao.findByUserCode(code);
	}

	@Override
	public void update(User isExsit) {
		userDao.update(isExsit);
	}

	@Override
	public List<User> findAllUser() {
		return userDao.findAllUser();
	}

	@Override
	public void deleteUserByUid(Integer uid) {
		userDao.deleteUserByUid(uid);
	}

	@Override
	public User findUserByUid(Integer uid) {
		return userDao.findUserByUid(uid);
	}

}
