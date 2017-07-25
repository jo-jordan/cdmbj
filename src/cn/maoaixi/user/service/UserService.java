package cn.maoaixi.user.service;

import java.util.List;

import cn.maoaixi.user.vo.User;

public interface UserService {

	public void register(User user);

	public User findByUserName(String username);

	public User findByUserEmail(String email);

	public User findByUserPhone(String phone);

	public User findByUserCode(String code);

	public void update(User isExsit);

	public List<User> findAllUser();

	public void deleteUserByUid(Integer uid);

	public User findUserByUid(Integer uid);

}
