package factory;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Role;

/**
 * @author Karl Merkli
 */
public class RoleFactory extends Factory implements ModelFactory<Role>{
	
	public RoleFactory() {
		this.fTableName = new Role().getTable();
	}
	
	@Override
	public List<Role> all() {
		ArrayList<Role> lList = new ArrayList<Role>();
		
		try {
			PreparedStatement lStatement = fConnection.prepareStatement("select * from " + this.fTableName);
			ResultSet lResult = lStatement.executeQuery();
			
			while (lResult.next()) {
				Role lRole = new Role();
				lRole.id = lResult.getInt("id");
				lRole.name = lResult.getString("name");
				
				lList.add(lRole);
			}
			
			return lList;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}	
		
		return null;
	}

	@Override
	public Role find(int aId) {
		try {
			PreparedStatement lStatement = fConnection.prepareStatement("select * from " + this.fTableName + " where id = ?");
			lStatement.setInt(1, aId);
			ResultSet lResult = lStatement.executeQuery();
			lResult.first();
			
			Role lRole = new Role();
			lRole.id = lResult.getInt("id");
			lRole.name = lResult.getString("name");
			
			return lRole;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}	
		
		return null;
	}

	@Override
	public List<Role> where(String aCol, String aOperator, String aValue) {
		ArrayList<Role> lList = new ArrayList<Role>();
		
		try {
			PreparedStatement lStatement = fConnection.prepareStatement("select * from " + this.fTableName + " where " + aCol + " " + aOperator + " ?");
			lStatement.setString(1, aValue);
			ResultSet lResult = lStatement.executeQuery();
			
			while (lResult.next()) {
				Role lRole = new Role();
				lRole.id = lResult.getInt("id");
				lRole.name = lResult.getString("name");
				
				lList.add(lRole);
			}
			
			return lList;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}	
		
		return null;
	}
}
