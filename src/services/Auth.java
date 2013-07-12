package services;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.User;

/**
 * @author Karl Merkli
 */

@ManagedBean
@SessionScoped
public class Auth {
	protected static boolean fLoggedIn = false;
	
	protected static User fUser;
	
	public static boolean isLoggedIn(){
		return Auth.fLoggedIn;
	}
	
	public static User user(){
		return Auth.fUser;
	}
	
	/**
	 * @author http://stackoverflow.com/questions/415953/generate-md5-hash-in-java
	 */
	public static String MD5(String aStr){
		MessageDigest lMD5;
		byte[] lMD5Byte;;
		BigInteger lMD5Int;
		
		try {
			lMD5 = MessageDigest.getInstance("MD5");
			
			lMD5Byte = aStr.getBytes(); 
			lMD5.update(lMD5Byte, 0, lMD5Byte.length);
		    lMD5Int = new BigInteger(1, lMD5.digest());
		    
			return String.format("%1$032X", lMD5Int);
		} catch (NoSuchAlgorithmException e) {
			System.out.println(e.getMessage());
		}
		
		return "";
	}
	
	public static boolean LogIn(User aUser, String aPassword){
		
		if ( aUser.password.equalsIgnoreCase(Auth.MD5(aPassword))) {
			Auth.fLoggedIn = true;
			Auth.fUser = aUser;
		}
		
		return Auth.fLoggedIn;
	}
	
	public static void LogOut(){
		Auth.fLoggedIn = false;
		Auth.fUser = null;
	}
}
