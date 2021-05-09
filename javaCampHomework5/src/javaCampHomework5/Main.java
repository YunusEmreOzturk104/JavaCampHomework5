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
			 System.out.println("email ile kay�t yapmak i�in 0 e bas�n�z");
			 System.out.println("google ile kay�t yapmak i�in 1 e bas�n�z");
			 System.out.println("email ile giri� yapmak i�in 2 e bas�n�z");
			 System.out.println("google ile giri� yapmak i�in 3 e bas�n�z");
			 System.out.println("kullanici aktifle�tir icin 4 e bas�n�z"); 
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
					System.out.println(" gecerli bir say�� giriniiz");
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
	

