package factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import vendor.MySql;

/**
 * @author Karl Merkli
 */
public abstract class Factory {

	protected Connection fConnection;
	protected String fTableName;
	
	public Factory(){
		this.fConnection = MySql.getMySqlManager().getConnection();
	}
	
	public void destroy(int aId) {
		try {
			PreparedStatement lStatement = fConnection.prepareStatement("delete from " + this.fTableName + " where id = ?");
			lStatement.setInt(1, aId);
			lStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
