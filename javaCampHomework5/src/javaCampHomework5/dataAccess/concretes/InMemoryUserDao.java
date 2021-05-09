package javaCampHomework5.dataAccess.concretes;

import java.util.ArrayList;
import java.util.List;

import javaCampHomework5.dataAccess.abstracts.UserDao;
import javaCampHomework5.entities.concretes.User;

public class InMemoryUserDao implements UserDao {
	List<User> _user;//sahte veritabaný
	public InMemoryUserDao() {
		this._user= new ArrayList<User>();
	}
	@Override
	public void add(User user) {
		_user.add(user);
		System.out.println(user.getFirstName()+"kullanýcýsý eklendi");
	}

	@Override
	public void update(User user) {	
		System.out.println(user.getFirstName()+"kullanýcýsý güncellendi");
	}

	@Override
	public void delete(User user) {
		_user.remove(user);
		System.out.println(user.getFirstName()+"kullanýcýsý silindi");
		
	}

	@Override
	public List<User> getAll() {
		return _user;
	}

	@Override
	public User getById(int id) {
		for(int i=0;i<_user.size();i++) {
			if(id==_user.get(i).getId()) {
				return _user.get(i);
			}
		}
		return null;
	}

	@Override
	public User getByEmail(String email) {
		for(int i=0;i<_user.size();i++) {
			if(email.equals(_user.get(i).getEmail())) {
				return _user.get(i);
			}
		}
		return null;
	}

	@Override
	public User getByEmailAndPassword(String email, String password) {
		for(int i = 0; i<_user.size();i++) {
			if(email.equals(_user.get(i).getEmail()) && password.equals(_user.get(i).getPassword())) {
				return _user.get(i);
			}
		}
		return null;
	}

}
