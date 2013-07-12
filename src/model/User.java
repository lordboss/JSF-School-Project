package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Karl Merkli
 */
public class User extends Model {
	
	public String name;
	public String username;
	public String email;
	public String password;
	public int role_id = 0;
	
	
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

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public User() {
		 this.fTableName = "users";
	}
	
	@Override
	public String toString(){
		return this.username;
	}
	
	@Override
	protected void saveAction() {
		String lSql = "";
		
		try {
			lSql = "insert into " + this.fTableName + "(name, username, email, password, role_id) VALUES(?,?,?,?,?)";
			PreparedStatement lStatement = fConnection.prepareStatement(lSql);
			lStatement.setString(1, this.name);
			lStatement.setString(2, this.username);
			lStatement.setString(3, this.email);
			lStatement.setString(4, this.password);
			lStatement.setInt(5, this.role_id);
			lStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	protected void updateAction() {
		String lSql = "";
		
		try {
			lSql = "update " + this.fTableName + " set name = ?, username = ?, email = ?, password = ?, role_id = ? where id = ?";
			PreparedStatement lStatement = fConnection.prepareStatement(lSql);
			lStatement.setString(1, this.name);
			lStatement.setString(2, this.username);
			lStatement.setString(3, this.email);
			lStatement.setString(4, this.password);
			lStatement.setInt(5, this.role_id);
			lStatement.setInt(6, this.id);
			lStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Get relationship
	 * @return
	 */
	public Role role(){
		try {
			ResultSet lResult = this.hasOne("roles", this.role_id);
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
}
