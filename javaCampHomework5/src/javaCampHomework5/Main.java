package javaCampHomework5;
import java.util.Scanner;

import javaCampHomework5.business.abstracts.UserService;
import javaCampHomework5.business.concretes.UserManager;
import javaCampHomework5.core.JGoogleAuthManagerAdapter;
import javaCampHomework5.dataAccess.concretes.InMemoryUserDao;
import javaCampHomework5.entities.concretes.User;


public class Main {
	public static UserService userManager2 = new UserManager(new InMemoryUserDao(),new JGoogleAuthManagerAdapter());
	public static void main(String[] args) {
		String firstName = "furkan";
		String lastName = "ozcan";
		String email = "facarr00@gmai.com";
		String password = "165465546";
		User user = new User(1,firstName,lastName,email,password);
		
		
		while(true) {
			
			 Scanner scanner = new Scanner(System.in);
			 System.out.println("email ile kayýt yapmak için 0 e basýnýz");
			 System.out.println("google ile kayýt yapmak için 1 e basýnýz");
			 System.out.println("email ile giriþ yapmak için 2 e basýnýz");
			 System.out.println("google ile giriþ yapmak için 3 e basýnýz");
			 System.out.println("kullanici aktifleþtir icin 4 e basýnýz"); 
			 int enteredNumber = scanner.nextInt();
			 
			  
			 
			switch(enteredNumber){
				case 0: 
					registerWithEmail();
					break;
				case 1: 
					registerWithGoogle();
					 
					break;
				case 2:
					loginWithEmail();
					 
					break;
				case 3: 
					loginWithGoogle();
					break;
			
				case 4: 
					 activateUser();
					break;
				default :
					System.out.println(" gecerli bir sayýý giriniiz");
			}
		}
	}
	private static void registerWithGoogle() {
		Scanner scanner = new Scanner(System.in);
		String firstName ;
		String lastName ;
		String email ;
		String password ;
		System.out.println("enter your name");
		firstName = scanner.next();
		System.out.println("enter your surname");
		lastName = scanner.next();
		System.out.println("enter your email");
		email = scanner.next();
		System.out.println("enter your password");
		password = scanner.next();
		User user = new User(1,firstName,lastName,email,password);
		userManager2.registerWithGoogle(user);
	}

	private static void loginWithGoogle() {
		Scanner scanner = new Scanner(System.in); 
		String email ;
		String password ;  
		 System.out.println("enter your email");
		 email = scanner.next();
		 System.out.println("enter your password");
		 password = scanner.next();
		 userManager2.loginWithGoogle(email,password);
		
		
	}

	public static void loginWithEmail() {
		Scanner scanner = new Scanner(System.in); 
		String email ;
		String password ;  
		 System.out.println("enter your email");
		 email = scanner.next();
		 System.out.println("enter your password");
		 password = scanner.next();
		 userManager2.login(email,password);
	}
	public static void registerWithEmail() {
		Scanner scanner = new Scanner(System.in);
		String firstName ;
		String lastName ;
		String email ;
		String password ;
		System.out.println("enter your name");
		firstName = scanner.next();
		System.out.println("enter your surname");
		lastName = scanner.next();
		System.out.println("enter your email");
		email = scanner.next();
		System.out.println("enter your password");
		password = scanner.next();
		User user = new User(1,firstName,lastName,email,password);
		userManager2.register(user);
	}
	
	public static void activateUser() {
		Scanner scanner = new Scanner(System.in);
		String email ;
		System.out.println("enter your email");
		email = scanner.next();
		userManager2.activateUser(email); 
	}
}
	

