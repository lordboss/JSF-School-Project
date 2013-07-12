package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Karl Merkli
 */
public class Role extends Model{
	
	public String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Role() {
		this.fTableName = "roles";
	}
	
	@Override
	public String toString(){
		return this.name;
	}
	
	@Override
	protected void saveAction() {
		String lSql = "";
		
		try {
			lSql = "insert into " + this.fTableName + "(name) VALUES(?)";
			PreparedStatement lStatement = fConnection.prepareStatement(lSql);
			lStatement.setString(1, this.name);
			lStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	protected void updateAction() {
		String lSql = "";
		
		try {
			lSql = "update " + this.fTableName + " set name = ? where id = ?";
			PreparedStatement lStatement = fConnection.prepareStatement(lSql);
			lStatement.setString(1, this.name);
			lStatement.setInt(2, this.id);
			lStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
