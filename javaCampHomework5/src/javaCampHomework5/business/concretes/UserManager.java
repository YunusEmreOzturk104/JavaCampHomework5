package javaCampHomework5.business.concretes;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;


import java.util.regex.Matcher;


import javaCampHomework5.business.abstracts.UserService;
import javaCampHomework5.core.AuthService;
import javaCampHomework5.core.BusinessRules;
import javaCampHomework5.dataAccess.abstracts.UserDao;
import javaCampHomework5.entities.concretes.User;

public class UserManager implements UserService {
	UserDao _userDao;
	AuthService _authService;
	public UserManager(UserDao _userDao,AuthService _authService) {
		super();
		this._userDao = _userDao;
		this._authService = _authService;
	}
	private boolean verifyByPasswordLenght(User user){

		   if(user.getPassword().length() < 6  ) { 
			   System.out.println(" password en az 6 karakter olmalidir. ");
			   return false; 
		   } 
		   return true;
	}

	@Override
	public void registerWithGoogle(User user) {
		boolean validation = BusinessRules.Run(checkIfUserExist(user)); 	 
		   if (!validation) return; 
		user.setActive(false);  
	
		System.out.println(" kullan�c� ba�ar�yla eklendi");
		_userDao.add(user); 
		_authService.register(user);
		
	}

	@Override
	public void loginWithGoogle(String email, String password) {
		var result = _userDao.getByEmailAndPassword(email, password); 
		if(result ==null) {
			System.out.println("giris basarisiz.!!!!!!!!!!!!!!!!!!!!!!!");
			return;
		}else if(!isActive(email)) {
			System.out.println("giris basarisiz. cunku kullan�c� aktif de�il giri� yapmadan �nec kullan�c�y� aktifle�tirin.");
			return;
		}
		else {
			_authService.login(email,password);// bunu d��ardan sistem eklemeye bildi�imizi s�mile ettik altaki kod ger�ekten database ekliyor.
			System.out.println(result.getFirstName() + " kullan�c� ba�ar�yla giri� yapt�");
		}  
		
	}
	private boolean checkIfUserExist(User user) { 
		User tempUser = _userDao.getByEmail(user.getEmail()); 
		if(tempUser == null){
		 return true;  
		}
		System.out.println(" BU MA��LLE DAHA �NCEDEN KAYIT YAPILDI.");
		return false; 
	}

	@Override
	public void register(User user) {
		boolean validation = BusinessRules.Run(verifyByEmail(user),verifyByNameAndSurname(user),
				   verifyByPasswordLenght(user),checkIfUserExist(user)); 	 
		   if (!validation) return;  
		   user.setActive(false);
		   _userDao.add(user); 
		   System.out.println(" kullan�c� ba�ar�yla eklendi");
		   System.out.println(" kullan�c� say�s� = " + _userDao.getUserCount()); 
		   confirmationTransaction(user); 
		
	}
	private boolean verifyByEmail(User user) { 

		 String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		 Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE); 
		 Matcher matcher = pattern.matcher(user.getEmail());
	     boolean matchFound = matcher.find(); 
	     
	     if(matchFound) { 
		   return true;
	     } 
	     else {  
		  System.out.println("LUTFEN GECERLI BIR MAIL GIRINIZ");
		  return false;
	     }  
	}
    private boolean verifyByNameAndSurname(User user) { 
		
		if(user.getFirstName().length()<=2 && user.getLastName().length()<=2) {
			   System.out.println(" ad ve soyad en az 2 karakter olmal�d�r. ");
			   return false;
		} 
		return true;
		
	}
	private void confirmationTransaction(User user) {
		boolean verify = sendVerificationCode();
		   if (verify) {
			   user.setActive(true);
			  System.out.println("kullan�c� emaili onaylad�");
		   }
		   else {
			  System.out.println("kullan�c� emaili onaylamad� kullan�c� hala pasif konumda");
		   }
		
	}
	private boolean sendVerificationCode() {
		 boolean whileControl = true;
		 boolean verificationCode = false; 
		 Scanner scanner = new Scanner(System.in);
		 while(whileControl==true) {
			 System.out.println("�uan onay kodunu g�r�yorsunuz onaylamak i�in 1 e iptal i�in 0 bas�n�z.");
			 int number = scanner.nextInt();
			 if(number == 1) { //  
				 whileControl =false;
				 verificationCode = true;
			 }else if(number == 0 ) {
				 whileControl =false;
				 verificationCode = false; 
			 }else {
				 System.out.println("GECERSIZ BIR NUMARA GIRDINIZ");
			 }
		 }
		 
		 
		 
		 return verificationCode; 
	}

	@Override
	public void login(String email, String password) {
		var result = _userDao.getByEmailAndPassword(email, password); 
		if(result ==null) {
			System.out.println("giris basarisiz.!!!!!!!!!!!!!!!!!!!!!!!");
			return;
		}else if(!isActive(email)) {
			System.out.println("giris basarisiz. cunku kullan�c� aktif de�il giri� yapmadan �nec kullan�c�y� aktifle�tirin.");
			return;
		}
		else {
			System.out.println(result.getFirstName() + " kullan�c� ba�ar�yla giri� yapt�");
		}  
		
	}

	@Override
	public List<User> getAll() {
		System.out.println("getall methodu �al��t�");
		return _userDao.getAll();
		
	}

	@Override
	public User getById(int id) {
		return _userDao.getById(id);
		
	}

	@Override
	public boolean isActive(String email) {
		User user = _userDao.getByEmail(email);
		return user.isActive();
	}

	@Override
	public void activateUser(String email) {
		if(isActive(email)) {
			System.out.println("kullan�c� zaten aktif");
			return;
		}
		else {
			User user = _userDao.getByEmail(email);
			user.setActive(true);
			System.out.println("kullan�c� aktif hale getirildi.");
			return;
		}
		
	}
	

}
