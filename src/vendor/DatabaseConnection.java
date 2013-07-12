package vendor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Karl Merkli
 * Nach Tutorial auf MySQL Webseite
 */
public class DatabaseConnection {
	
	protected Connection fConnection;
	
    public DatabaseConnection(){
	    try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			System.out.println(e1.getMessage());
		}

	    String serverName = "localhost";
	    String mydatabase = "Blog";
	    String url = "jdbc:mysql://" + serverName + "/" + mydatabase; 

	    String username = "root";
	    String password = "";
    	
    	try {
    		this.fConnection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
    }
    
    public Connection getConnection(){
    	return this.fConnection;
    }
}

