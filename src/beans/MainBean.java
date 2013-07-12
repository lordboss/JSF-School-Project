package beans;

import javax.faces.bean.ManagedBean;

import services.Auth;

@ManagedBean
public class MainBean {
	protected String username;
	
	public String getUsername() {
		return username;
	}

	public MainBean() {
		if (Auth.isLoggedIn()){
			this.username = Auth.user().name;
		} else {
			this.username = "Guest";
		}
	}
}
