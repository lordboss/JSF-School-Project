package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vendor.MySql;

/**
 * @author Karl Merkli
 */
public abstract class Model implements Cloneable{
	
	protected String fTableName;
	protected Connection fConnection;
	
	/**
	 * Every model has an id
	 */
	public int id = 0;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Model(){
		this.fConnection = MySql.getMySqlManager().getConnection();
	}
	
	/**
	 * Returns the tablename
	 * Doesnt need to get overwritten
	 * @return
	 */
	public final String getTable(){
		return this.fTableName;
	}
	
	@Override
	public Object clone(){
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			System.out.println(e.getMessage());
		}
		
		return null;
	}
	
	/**
	 * Save model
	 */
	public final void save(){
		if (!this.beforeSave()) return;
		
		if (this.exists()){
			this.updateAction();
		}
		else{
			this.saveAction();
		}
		
		this.afterSave();
	}

	/**
	 * Action to save model
	 */
	protected abstract void saveAction();
	
	/**
	 * Action to update model
	 */
	protected abstract void updateAction();
	
	/**
	 * Does current row already exists
	 * @return
	 */
	public boolean exists() {
		try {
			PreparedStatement lStatement = fConnection.prepareStatement("select id from " + this.fTableName + " where id = ?");
			lStatement.setInt(1, this.id);
			ResultSet lResult = lStatement.executeQuery();
			return lResult.first();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}	
		
		return false;
	}
	
	/**
	 * Delete current row
	 */
	public boolean delete() {	
		try {
			PreparedStatement lStatement = fConnection.prepareStatement("delete from " + this.fTableName + " where id = ?");
			lStatement.setInt(1, this.id);
			lStatement.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}	
	}
	
	/**
	 * Get c-c relationship
	 * 
	 * @param aTableName
	 * @param aId
	 * @return ResultSet
	 */
	protected ResultSet hasOne(String aTableName, int aId){
		try {
			PreparedStatement lStatement = fConnection.prepareStatement("select * from " + aTableName + " where id = ?");
			lStatement.setInt(1, aId);
			ResultSet lResult = lStatement.executeQuery();
			
			return lResult;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}	
		
		return null;
	}

	/**
	 * Filter before save
	 * @return
	 */
	protected boolean beforeSave(){
		return true;
	}

	/**
	 * Filter after save
	 */
	protected void afterSave(){
		
	}
}
