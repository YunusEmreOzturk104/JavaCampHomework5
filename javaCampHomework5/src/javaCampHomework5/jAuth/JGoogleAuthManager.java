package javaCampHomework5.jAuth;

public class JGoogleAuthManager {

	public void register(User user) { 
		System.out.println(user.getFirstName() + " jgoogle auth manager ile kay�t olundu."  );
		
		
		
	}
 
	public void login(String email, String password) {
		System.out.println(email + " jgoogle auth manager ile giri� yap�ld�");
	}
}
