package beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import model.User;
import services.Auth;
import factory.UserFactory;

@ManagedBean
@SessionScoped
public class LoginBean {
	
	protected String username;
	protected String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String login(){
		if (Auth.isLoggedIn()){
			return "/index.xhtml";
		} else {
			return "null";
		}
	}
	
	public String logout(){
		Auth.LogOut();
		return "/login.xhtml";
	}

	public void validate(ComponentSystemEvent event) {
		Auth.LogOut();
		
		FacesContext lFacesContext = FacesContext.getCurrentInstance();

		UIComponent lComponents = event.getComponent();

		// get password
		UIInput lPassword = (UIInput) lComponents.findComponent("password");
		String lPasswordString = lPassword.getLocalValue() == null ? "" : lPassword.getLocalValue().toString();
		
		// get username
		UIInput lUsername = (UIInput) lComponents.findComponent("username");
		String lUsernameString = lUsername.getLocalValue() == null ? "" : lUsername.getLocalValue().toString();
		String lUsernameId = lUsername.getClientId();
		
		// Let required="true" do its job.
		if (lUsernameString.isEmpty() || lPasswordString.isEmpty()) {
			return;
		}
		  
		UserFactory lFactory = new UserFactory();
		List<User> lUser = lFactory.where("username", "=", lUsernameString);
		
		if (lUser.size() == 0 || !Auth.LogIn(lUser.get(0), lPasswordString) ) {
			FacesMessage msg = new FacesMessage("Your password is invalid.");
			lFacesContext.addMessage(lUsernameId, msg);
			lFacesContext.renderResponse();
		}
	}
}
