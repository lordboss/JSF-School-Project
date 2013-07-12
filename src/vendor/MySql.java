package vendor;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author M. Marti
 */
public class MySql {

	protected static MySql dbm;
	protected Connection db  = new DatabaseConnection().getConnection();
	
	public static MySql getMySqlManager(){
		if (dbm == null)
			dbm = new MySql();
		return dbm;	
	}
	
	public Connection getConnection(){
		return this.db;
	}
	  
	@Override
	public void finalize() throws SQLException {
		if (db == null) return;
		
		db.close();
		db = null;
		dbm = null;
	} 
}
