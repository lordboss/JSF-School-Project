package lib;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import services.Auth;

/**
 * @author Karl Merkli
 */

@ManagedBean
@SessionScoped
public class Gravatar {
	
	public static String theGravatar(){
		String lEmail;
		
		if (Auth.user() == null){
			lEmail = "";
		} else {
			lEmail = Auth.user().email;
		}
		
		String lHash = Auth.MD5(lEmail.trim().toLowerCase()).toLowerCase();
		return "http://www.gravatar.com/avatar/" + lHash + "?s=28";
	}
}
