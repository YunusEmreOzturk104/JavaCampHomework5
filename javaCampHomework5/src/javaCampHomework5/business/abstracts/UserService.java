package javaCampHomework5.business.abstracts;

import java.util.List;

import javaCampHomework5.entities.concretes.User;

public interface UserService {
	void registerWithGoogle(User user);
	void loginWithGoogle(String email, String password);
	void register(User user);
	void login(String email, String password);
	List<User> getAll();
	User getById(int id); 
	boolean isActive(String email);
	void activateUser(String email);
}
