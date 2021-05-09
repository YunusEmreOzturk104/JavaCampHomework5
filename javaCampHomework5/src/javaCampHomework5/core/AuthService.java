package javaCampHomework5.core;

import javaCampHomework5.entities.concretes.User;

public interface AuthService {
	void register(User user);
	void login(String email, String password);
}
