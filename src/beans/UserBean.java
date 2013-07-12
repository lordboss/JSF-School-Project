package beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import model.User;
import services.Auth;
import services.UserValidator;
import factory.UserFactory;

@ManagedBean
@ViewScoped
public class UserBean {
	protected String name;
	protected String username;
	protected String email;
	protected String password;
	protected String role_id;
	
	/**
	 * Getter & Setter 
	 */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	
	//
	public String create() {
		return "../index.xhtml";
	}
	
	public String edit() {
		return "../index.xhtml";
	}
	
	public void validate(ComponentSystemEvent event) {
		FacesContext lFacesContext = FacesContext.getCurrentInstance();
		UIComponent lComponents = event.getComponent();

		// get password
		UIInput lPassword = (UIInput) lComponents.findComponent("password");
		String lPasswordString = lPassword.getLocalValue() == null ? "" : lPassword.getLocalValue().toString();
		
		// get name
		UIInput lName = (UIInput) lComponents.findComponent("name");
		String lNameString = lName.getLocalValue() == null ? "" : lName.getLocalValue().toString();
		
		// get username
		UIInput lUsername = (UIInput) lComponents.findComponent("username");
		String lUsernameString = lUsername.getLocalValue() == null ? "" : lUsername.getLocalValue().toString();
		String lUsernameId = lUsername.getClientId();
				
		// get email
		UIInput lEmail = (UIInput) lComponents.findComponent("email");
		String lEmailString = lEmail.getLocalValue() == null ? "" : lEmail.getLocalValue().toString();
				
		// get role_id
		UIInput lRoleId = (UIInput) lComponents.findComponent("role");
		String lRoleIdString = lRoleId.getLocalValue() == null ? "" : lRoleId.getLocalValue().toString();
		  
		User lUser = new User();
		lUser.name = lNameString;
		lUser.username = lUsernameString;
		lUser.email = lEmailString;
		lUser.password = Auth.MD5(lPasswordString);
		lUser.role_id = Integer.parseInt(lRoleIdString);
		
		UserValidator lValidator = new UserValidator(lUser);
		if (!lValidator.passes()) {
			for (String lMsg : lValidator.error()) {
				FacesMessage msg = new FacesMessage(lMsg);
				lFacesContext.addMessage(lUsernameId, msg);
			}
			lFacesContext.renderResponse();
		} else {
			lUser.save();
		}
	}
	
	/**
	 * Model 
	 */
	public List<User> getAllUsers(){
		UserFactory lFactory = new UserFactory();
		return lFactory.where("id", "<>", "1"); // dont give them the admin user
	}
}
