package cz.uhk.restaurace.dao;

import java.util.List;
import cz.uhk.restaurace.model.User;

public interface UserDao {
	public void addUser(User user);
	public void updateUser(User user);
	public List<User> listUser();
	public User getUserById(String username);
	public void removeUser(int id);
}
