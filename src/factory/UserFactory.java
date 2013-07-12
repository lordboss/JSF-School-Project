package factory;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;

/**
 * @author Karl Merkli
 */
public class UserFactory extends Factory implements ModelFactory<User>{

	public UserFactory() {
		this.fTableName = new User().getTable();
	}
	
	@Override
	public List<User> all() {
		ArrayList<User> lList = new ArrayList<User>();
		
		try {
			PreparedStatement lStatement = fConnection.prepareStatement("select * from " + this.fTableName);
			ResultSet lResult = lStatement.executeQuery();
			
			while (lResult.next()) {
				User lUser = new User();
				lUser.id = lResult.getInt("id");
				lUser.name = lResult.getString("name");
				lUser.username = lResult.getString("username");
				lUser.email = lResult.getString("email");
				lUser.password = lResult.getString("password");
				lUser.role_id = lResult.getInt("role_id");
				
				lList.add(lUser);
			}
			
			return lList;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}	
		
		return null;
	}

	@Override
	public User find(int aId) {
		try {
			PreparedStatement lStatement = fConnection.prepareStatement("select * from " + this.fTableName + " where id = ?");
			lStatement.setInt(1, aId);
			ResultSet lResult = lStatement.executeQuery();
			lResult.first();
			
			User lUser = new User();
			lUser.id = lResult.getInt("id");
			lUser.name = lResult.getString("name");
			lUser.username = lResult.getString("username");
			lUser.email = lResult.getString("email");
			lUser.password = lResult.getString("password");
			lUser.role_id = lResult.getInt("role_id");
			
			return lUser;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}	
		
		return null;
	}

	@Override
	public List<User> where(String aCol, String aOperator, String aValue) {
		ArrayList<User> lList = new ArrayList<User>();
		
		try {
			PreparedStatement lStatement = fConnection.prepareStatement("select * from " + this.fTableName + " where " + aCol + " " + aOperator + " ?");
			lStatement.setString(1, aValue);
			ResultSet lResult = lStatement.executeQuery();
			
			while (lResult.next()) {
				User lUser = new User();
				lUser.id = lResult.getInt("id");
				lUser.name = lResult.getString("name");
				lUser.username = lResult.getString("username");
				lUser.email = lResult.getString("email");
				lUser.password = lResult.getString("password");
				lUser.role_id = lResult.getInt("role_id");
				
				lList.add(lUser);
			}
			
			return lList;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}	
		
		return null;
	}
}
