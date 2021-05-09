package javaCampHomework5.core;

import javaCampHomework5.entities.concretes.User;

public class JGoogleAuthManagerAdapter implements AuthService {

	@Override
	public void register(User user) {
		JGoogleAuthManagerAdapter jGoogleAuthManagerAdapter= new JGoogleAuthManagerAdapter();
		jGoogleAuthManagerAdapter.register(user);
		
	}

	@Override
	public void login(String email, String password) {
		JGoogleAuthManagerAdapter jGoogleAuthManagerAdapter= new JGoogleAuthManagerAdapter();
		jGoogleAuthManagerAdapter.login(email, password);
		
	}

}
