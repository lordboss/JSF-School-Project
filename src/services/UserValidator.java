package services;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import model.User;
import factory.UserFactory;

/**
 * @author Karl Merkli
 */
public class UserValidator extends Validator{

	public User User;
	
	protected List<String> fError;
	
	/**
	 * http://stackoverflow.com/a/153751
	 */
	private final Pattern rfc2822;
	
	public UserValidator(User aUser){
		this.User = aUser;
		this.fError = new ArrayList<String>();
		this.rfc2822 = Pattern.compile(
				"^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$"
		);
	}
	
	@Override
	public boolean passes() {
		this.fError.clear();
		
		// Name
		if (this.User.name.isEmpty()){
			this.fError.add("Name must not be empty.");
		}
		
		// Username
		if (this.User.username.isEmpty()){
			this.fError.add("Username must not be empty.");
		} else{
			List<User> lList = new UserFactory().all();
			
			for (User lUser : lList) {
				if  ( (lUser.username.equalsIgnoreCase(this.User.username)) && (lUser.id != this.User.id) ){
					this.fError.add("Username already taken.");
					break;
				}
			}
			
		}
		
		// Email
		if (this.User.email.isEmpty()){
			this.fError.add("Email must not be empty.");
		} else {
			if (!this.rfc2822.matcher(this.User.email).matches()) {
				this.fError.add("Email must be valid.");
			}
			
			List<User> lList = new UserFactory().all();
			for (User lUser : lList) {
				if  ( (lUser.email.equalsIgnoreCase(this.User.email)) && (lUser.id != this.User.id) ){
					this.fError.add("Email already used.");
					break;
				}
			}
		}
		
		// Password
		if (this.User.password.isEmpty()){
			this.fError.add("Password must not be empty.");
		}
		
		return this.fError.isEmpty();
	}

	@Override
	public List<String> error() {
		return this.fError;
	}
}
