package cz.uhk.restaurace.service.impl;

import java.util.List;
import java.util.Set;

import cz.uhk.restaurace.model.Role;
import cz.uhk.restaurace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cz.uhk.restaurace.dao.UserDao;
import cz.uhk.restaurace.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	@Transactional
	public void addUser(User user) {
		userDao.addUser(user);

	}

	@Override
	@Transactional
	public void updateUser(User user) {
		userDao.updateUser(user);

	}

	@Override
	@Transactional(readOnly = true)
	public List<User> listUser() {
		return userDao.listUser();
	}

	@Override
	@Transactional(readOnly = true)
	public User getUserById(String username) {
		return userDao.getUserById(username);
	}

	@Override
	@Transactional
	public void removeUser(int id) {
		userDao.removeUser(id);

	}

	@Override
	public List<User> getUsersByRole(Role.RoleType role) {
		return userDao.getUsersByRole(role);
	}
}
