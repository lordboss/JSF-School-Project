package beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import model.Role;
import factory.RoleFactory;

@ManagedBean
@ViewScoped
public class RoleBean {

	public List<Role> getAllRoles(){
		RoleFactory lFactory = new RoleFactory();
		return lFactory.all();
	}
	
	public Role getRole(int aId){
		RoleFactory lFactory = new RoleFactory();
		return lFactory.find(aId);
	}
}
